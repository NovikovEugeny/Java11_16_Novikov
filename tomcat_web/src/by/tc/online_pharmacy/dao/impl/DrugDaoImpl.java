package by.tc.online_pharmacy.dao.impl;

import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.dao.DrugDao;
import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;
import by.tc.online_pharmacy.dao.exception.DaoException;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Евгений on 17.02.2017.
 */
public class DrugDaoImpl implements DrugDao {

    private static final String url = "jdbc:mysql://localhost:3306/pharmacy2";
    private static final String userName = "root";
    private static final String password = "";

    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private List<Drug> drugs(String query) throws DaoException {
        List<Drug> drugs = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Drug drug = new Drug();
                drug.setId(resultSet.getInt("id"));
                drug.setName(resultSet.getString("name"));
                drug.setGroup(resultSet.getString("pharm_group"));
                drug.setForm(resultSet.getString("form"));
                drug.setDrugAmount(resultSet.getString("drug_amount"));
                drug.setActiveSubstances(resultSet.getString("active_substances"));
                drug.setCountry(resultSet.getString("country"));
                drug.setDispensing(resultSet.getString("dispensing"));
                drug.setPrice(resultSet.getDouble("price"));
                drug.setQuantity(resultSet.getInt("quantity"));

                drugs.add(drug);
            }
            return drugs;
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }


    @Override
    public List<Drug> takeDrugGroup(String group) throws DaoException {
        final String query = "SELECT * FROM drugs WHERE pharm_group = '" + group +
                "' AND is_active = 'yes'";

        return drugs(query);
    }

    @Override
    public List<Drug> takeDrugsByName(String name) throws DaoException {
        final String query = "SELECT * FROM drugs WHERE UPPER(name) like '%"
                + name + "%' AND is_active = 'yes'";

        return drugs(query);
    }


    @Override
    public String addDrugQuantity(int id, int quantity) throws DaoException {
        final String query = "UPDATE drugs SET quantity = quantity + ? " +
                "WHERE id = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        String response = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc){

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return response;
    }

    @Override
    public String addNewDrug(Drug drug) throws DaoException {

        final String testQuery = "SELECT id FROM drugs WHERE " +
                "name = ? AND form = ? AND drug_amount = ? AND " +
                "active_substances = ? AND country = ? AND " +
                "dispensing = ? AND price = ?";

        final String updateQuantity = "UPDATE drugs SET quantity = quantity + ?" +
                " WHERE id = ?";

        final String insertQuery = "INSERT INTO drugs(name, pharm_group, form, " +
                "drug_amount, active_substances, country, dispensing, price, " +
                "quantity, is_active) VALUES (?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String response = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            ps = connection.prepareStatement(testQuery);
            ps.setString(1, drug.getName());
            ps.setString(2, drug.getForm());
            ps.setString(3, drug.getDrugAmount());
            ps.setString(4, drug.getActiveSubstances());
            ps.setString(5, drug.getCountry());
            ps.setString(6, drug.getDispensing());
            ps.setDouble(7, drug.getPrice());
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");

                ps = connection.prepareStatement(updateQuantity);
                ps.setInt(1, drug.getQuantity());
                ps.setInt(2, id);
                ps.executeUpdate();
            } else {
                ps = connection.prepareStatement(insertQuery);
                ps.setString(1, drug.getName());
                ps.setString(2, drug.getGroup());
                ps.setString(3, drug.getForm());
                ps.setString(4, drug.getDrugAmount());
                ps.setString(5, drug.getActiveSubstances());
                ps.setString(6, drug.getCountry());
                ps.setString(7, drug.getDispensing());
                ps.setDouble(8, drug.getPrice());
                ps.setInt(9, drug.getQuantity());
                ps.setString(10, "yes");
                ps.executeUpdate();
            }
            response = "drug successfully added";
        } catch (SQLException | InterruptedException exc){
            response = "An error occurred while adding";
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return response;
    }


    @Override
    public String removeDrug(int id) throws DaoException {
        final String query = "UPDATE drugs SET is_active = 'no' WHERE id = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        String response = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

            response = "ok";
        } catch (SQLException | InterruptedException exc) {
            response = "bad";
            throw new DaoException();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return response;
    }


    @Override
    public String toOrder(Order order) throws DaoException {

        final String insertQuery = "INSERT INTO orders(client_id, drug_id, " +
                "quantity, cost, request_date, status) VALUES (?,?,?,?,NOW(),?)";

        final String updateBalance = "UPDATE accounts SET balance = balance - ? WHERE " +
                "client_id = ?";

        final String updateDrug = "UPDATE drugs SET quantity = quantity - ? " +
                "WHERE id = ?";

        final String takeBalance = "SELECT balance FROM accounts WHERE client_id = ?";

        final String takeMaxQuantity = "SELECT quantity FROM drugs WHERE id = ?";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String response = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(takeBalance);
            ps.setInt(1, order.getClientId());
            resultSet = ps.executeQuery();
            resultSet.next();
            double balance = resultSet.getDouble("balance");

            if (balance < order.getCost()) {
                throw new DaoException("is not enough money");
            }

            ps = connection.prepareStatement(takeMaxQuantity);
            ps.setInt(1, order.getDrugId());
            resultSet = ps.executeQuery();
            resultSet.next();
            int quantity = resultSet.getInt("quantity");

            if (quantity < order.getQuantity()) {
                throw new DaoException("Unacceptable quantity of drug");
            }

            ps = connection.prepareStatement(insertQuery);
            ps.setInt(1, order.getClientId());
            ps.setInt(2, order.getDrugId());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, order.getCost());
            ps.setString(5, order.getStatus());
            ps.executeUpdate();

            ps = connection.prepareStatement(updateBalance);
            ps.setDouble(1, order.getCost());
            ps.setInt(2, order.getClientId());
            ps.executeUpdate();

            ps = connection.prepareStatement(updateDrug);
            ps.setInt(1, order.getQuantity());
            ps.setInt(2, order.getDrugId());
            ps.executeUpdate();

            response = "ok";
        } catch (SQLException | InterruptedException exc) {
            response = "bad";
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return response;
    }


    @Override
    public Date confirmRecipe(Recipe2 recipe2) throws DaoException {
        final String query = "SELECT end_date FROM recipes WHERE code = ? AND " +
                "drug_id = ? AND quantity = ?";

        Date response = null;

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
             connection = ConnectionPool.getInstance().takeConnection();

             ps = connection.prepareStatement(query);
             ps.setString(1, recipe2.getRecipeCode());
             ps.setInt(2, recipe2.getDrugId());
             ps.setInt(3, recipe2.getQuantity());
             resultSet = ps.executeQuery();

             if (resultSet.next()) {
                 response = resultSet.getTimestamp("end_date");
             }
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return response;
    }


    @Override
    public List<OrderDescription> showOrderList() throws DaoException {
        List<OrderDescription> orderList = new ArrayList<>();

        final String selectQuery = "SELECT d.name, d.pharm_group, d.form, d.drug_amount, d.active_substances, d.country, o.quantity, o.request_date, o.id AS `order_id`, (SELECT mobile_phone FROM users WHERE id = o.client_id) AS `client_mobile` FROM drugs d INNER JOIN orders o ON d.id = o.drug_id WHERE o.status = 'new'";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
               OrderDescription orderDescription = new OrderDescription();
               orderDescription.setOrderId(resultSet.getInt("order_id"));
               orderDescription.setRequestDate(resultSet.getTimestamp("request_date"));
               orderDescription.setClientMobile(resultSet.getString("client_mobile"));
               orderDescription.setDrugName(resultSet.getString("name"));
               orderDescription.setPharmacologicalGroup(resultSet.getString("pharm_group"));
               orderDescription.setDrugForm(resultSet.getString("form"));
               orderDescription.setDrugAmount(resultSet.getString("drug_amount"));
               orderDescription.setActiveSubstances(resultSet.getString("active_substances"));
               orderDescription.setProductingCountry(resultSet.getString("country"));
               orderDescription.setQuantity(resultSet.getInt("quantity"));

               orderList.add(orderDescription);
            }

            return orderList;
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }


    @Override
    public String send(int orderId, int pharmacistId) throws DaoException {

        final String updateOrder = "UPDATE orders SET pharmacist_id = ?, " +
                "status = 'sent', request_date = request_date, " +
                "response_date = NOW() WHERE id = ?";

        String response = null;

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(updateOrder);
            ps.setInt(1, pharmacistId);
            ps.setInt(2, orderId);
            ps.executeUpdate();

            response = "ok";
        } catch (SQLException | InterruptedException exc) {
            response = "error";
            throw new DaoException(exc);
        }
        return response;
    }

    @Override
    public String sendFeedback(int recipeId, String feedback) throws DaoException {
        final String updateRecipe = "UPDATE recipes SET feedback = ?, " +
                "request_date = request_date WHERE id = ?";

        String response = null;

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            ps = connection.prepareStatement(updateRecipe);
            ps.setString(1, feedback);
            ps.setInt(2, recipeId);
            ps.executeUpdate();

            response = "ok";
        } catch (SQLException exc) {
            response = "fail";
            throw new DaoException(exc);
        }
        return response;
    }

}
