package by.tc.online_pharmacy.dao.impl;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.Order;
import by.tc.online_pharmacy.bean.Recipe;
import by.tc.online_pharmacy.dao.DrugDao;
import by.tc.online_pharmacy.dao.exception.DaoException;

import java.sql.*;
import java.util.*;

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


    private List<Drug> drugs(String query)  throws DaoException {
        List<Drug> drugs = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Drug drug = new Drug();
                drug.setId(resultSet.getInt("id"));
                drug.setName(resultSet.getString("name"));
                drug.setGroup(resultSet.getString("group"));
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
        } catch (SQLException exc){
            throw new DaoException(exc);
        }
    }


    @Override
    public List<Drug> takeDrugGroup(String group) throws DaoException {
        final String query = "SELECT d.id, d.name, pg.name AS `group`, d.form," +
                " d.drug_amount, d.active_substances, d.country, d.dispensing," +
                " d.price, d.quantity FROM drugs d INNER JOIN " +
                "pharmacological_groups pg ON d.group_id = pg.id WHERE " +
                "pg.name = '" + group + "' AND d.is_active = 'yes'";

        return drugs(query);
    }

    @Override
    public List<Drug> takeDrugsByName(String name) throws DaoException {
        final String query = "SELECT d.id, d.name, pg.name AS `group`, d.form," +
                " d.drug_amount, d.active_substances, d.country, d.dispensing," +
                " d.price, d.quantity FROM drugs d INNER JOIN pharmacological_groups" +
                " pg ON d.group_id = pg.id WHERE UPPER(d.name) like '%" + name +
                "%' AND d.is_active = 'yes'";

        return drugs(query);
    }


    @Override
    public String addDrug(Drug drug) throws DaoException {

        final String testQuery = "SELECT id, quantity FROM drugs WHERE " +
                "name = ? AND form = ? AND drug_amount = ? AND " +
                "active_substances = ? AND country = ? AND " +
                "dispensing = ? AND price = ?";

        final String updateQuantity = "UPDATE drugs SET quantity = ?" +
                " WHERE id = ?";

        final String groupQuery = "SELECT id FROM pharmacological_groups " +
                "WHERE name = '" + drug.getGroup() + "'";

        final String insertQuery = "INSERT INTO drugs(name, group_id, form, " +
                "drug_amount, active_substances, country, dispensing, price, " +
                "quantity, is_active) VALUES (?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;
        Statement statement = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String response = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
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
                int quantity = resultSet.getInt("quantity");
                quantity = quantity + drug.getQuantity();

                ps = connection.prepareStatement(updateQuantity);
                ps.setInt(1, quantity);
                ps.setInt(2, id);
                ps.executeUpdate();
            } else {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(groupQuery);
                resultSet.next();
                int groupId = resultSet.getInt("id");

                ps = connection.prepareStatement(insertQuery);
                ps.setString(1, drug.getName());
                ps.setInt(2, groupId);
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
        } catch (SQLException exc){
            response = "An error occurred while adding";
            throw new DaoException(exc);
        }
        return response;
    }


    @Override
    public String removeDrug(int id) throws DaoException {
        final String query = "UPDATE drugs SET is_active = 'no' WHERE id = " + id;

        Connection connection = null;
        Statement statement = null;

        String response = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            statement.executeUpdate(query);

            response = "ok";
        } catch (SQLException exc) {
            response = "noo";
            throw new DaoException();
        }
        return response;
    }


    @Override
    public String orderWithoutRecipe(Order order) throws DaoException {

        final String insertQuery = "INSERT INTO orders(client_id," +
                " drug_id, quantity, cost, delivery_address, request_date, status) VALUES " +
                "(?,?,?,?,?,NOW(),?)";

        final String takeMoney = "UPDATE accounts SET balance = balance - ? WHERE " +
                "client_id = ?";

        final String updateDrug = "UPDATE drugs SET quantity = quantity - ?" +
                " WHERE id = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        String response = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);

            ps = connection.prepareStatement(insertQuery);
            ps.setInt(1, order.getClientId());
            ps.setInt(2, order.getDrugId());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, order.getCost());
            ps.setString(5, order.getDeliveryAddress());
            ps.setString(6, order.getStatus());
            ps.executeUpdate();

            ps = connection.prepareStatement(takeMoney);
            ps.setDouble(1, order.getCost());
            ps.setInt(2, order.getClientId());
            ps.executeUpdate();

            ps = connection.prepareStatement(updateDrug);
            ps.setDouble(1, order.getQuantity());
            ps.setInt(2, order.getDrugId());
            ps.executeUpdate();

            response = "ok";
        } catch (SQLException exc){
            response = "error";
            throw new DaoException(exc);
        }
        return response;
    }

    @Override
    public String orderWithRecipe(Order order, Recipe recipe) throws DaoException {

        final String insertQuery = "INSERT INTO recipes" +
                "(client_id, drug_id, quantity, recipe_code, request_date, status)" +
                " VALUES (?,?,?,?, NOW(), ?)";

        Connection connection = null;
        PreparedStatement ps = null;

        String response = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            ps = connection.prepareStatement(insertQuery);
            ps.setInt(1, order.getClientId());
            ps.setInt(2, order.getDrugId());
            ps.setInt(3, recipe.getQuantity());
            ps.setString(4, recipe.getRecipeCode());
            ps.setString(5, recipe.getStatus());
            ps.executeUpdate();

            orderWithoutRecipe(order);

            response = "ok";
        } catch (SQLException exc){
            response = "error";
            throw new DaoException(exc);
        }
        return response;
    }


    @Override
    public Map<Order, Drug> showOrderList() throws DaoException {
        Map<Order, Drug> orderList = new LinkedHashMap<>();

        final String selectQuery = "SELECT d.id AS `drug_id`, d.name, pg.name AS `group`," +
                " d.form, d.drug_amount, d.active_substances, d.country, " +
                "o.quantity, o.request_date, o.id AS `order_id`, o.delivery_address FROM drugs d INNER JOIN " +
                "pharmacological_groups pg ON d.group_id = pg.id INNER JOIN " +
                "orders o ON d.id = o.drug_id WHERE o.status = 'new' " +
                "AND (d.dispensing = 'without prescription' OR (SELECT status " +
                "FROM recipes WHERE drug_id = o.drug_id AND client_id = o.client_id " +
                "AND quantity = o.quantity AND request_date = o.request_date) = 'approved')";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("order_id"));
                order.setDeliveryAddress(resultSet.getString("delivery_address"));
                order.setRequestDate(resultSet.getTimestamp("request_date"));

                Drug drug = new Drug();
                drug.setId(resultSet.getInt("drug_id"));
                drug.setName(resultSet.getString("name"));
                drug.setGroup(resultSet.getString("group"));
                drug.setForm(resultSet.getString("form"));
                drug.setDrugAmount(resultSet.getString("drug_amount"));
                drug.setActiveSubstances(resultSet.getString("active_substances"));
                drug.setCountry(resultSet.getString("country"));
                drug.setQuantity(resultSet.getInt("quantity"));

                orderList.put(order, drug);
            }

            return orderList;
        } catch (SQLException exc) {
            throw new DaoException(exc);
        }
    }

    @Override
    public Map<Recipe, Drug> showRecipeList() throws DaoException {
        Map<Recipe, Drug> recipeList = new LinkedHashMap<>();

        final String selectQuery = "SELECT d.name, pg.name " +
                "AS `group`, d.form, d.drug_amount, d.active_substances, " +
                "d.country, r.quantity, r.request_date, r.id AS `recipe_id`, " +
                "r.recipe_code FROM drugs d INNER JOIN pharmacological_groups pg " +
                "ON d.group_id = pg.id INNER JOIN recipes r ON d.id = r.drug_id " +
                "WHERE r.status = 'new'";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getInt("recipe_id"));
                recipe.setRecipeCode(resultSet.getString("recipe_code"));
                recipe.setRequestDate(resultSet.getTimestamp("request_date"));

                Drug drug = new Drug();
                drug.setName(resultSet.getString("name"));
                drug.setGroup(resultSet.getString("group"));
                drug.setForm(resultSet.getString("form"));
                drug.setDrugAmount(resultSet.getString("drug_amount"));
                drug.setActiveSubstances(resultSet.getString("active_substances"));
                drug.setCountry(resultSet.getString("country"));
                drug.setQuantity(resultSet.getInt("quantity"));

                recipeList.put(recipe, drug);
            }

            return recipeList;
        } catch (SQLException exc) {
            throw new DaoException(exc);
        }
    }


    @Override
    public String approve(int recipeId, int doctorId) throws DaoException {

        final String updateRecipe = "UPDATE recipes SET doctor_id = ?, status = " +
                "'approved', request_date = request_date, response_date = NOW()" +
                "WHERE id = ?";

        String response = null;

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);

            ps = connection.prepareStatement(updateRecipe);
            ps.setInt(1, doctorId);
            ps.setInt(2, recipeId);
            ps.executeUpdate();

            response = "ok";
        } catch (SQLException exc) {
            response = "fail";
            throw new DaoException(exc);
        }
        return response;
    }

    @Override
    public String deny(int recipeId, int doctorId) throws DaoException {

        final String updateRecipe = "UPDATE recipes SET doctor_id = ?, status = " +
                "'denied', request_date = request_date, response_date = NOW() " +
                "WHERE id = ?";

        final String selectData = "SELECT client_id, drug_id, quantity, " +
                "request_date FROM recipes WHERE id = " + recipeId;

        final String selectOrderId = "SELECT id, cost FROM orders WHERE client_id = ? AND drug_id = ? " +
                "AND quantity = ? AND request_date = ?";

        final String updateOrders = "UPDATE orders SET status = " +
                "'canceled', request_date = request_date WHERE id = ?";

        final String returnMoney = "UPDATE accounts SET balance = balance + ? WHERE " +
                "client_id = ?";

        final String returnDrugs = "UPDATE drugs SET quantity = quantity + ? WHERE " +
                "id = ?";

        String response = null;

        Connection connection = null;
        PreparedStatement ps = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);

            ps = connection.prepareStatement(updateRecipe);
            ps.setInt(1, doctorId);
            ps.setInt(2, recipeId);
            ps.executeUpdate();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectData);
            resultSet.next();
            int client_id = resultSet.getInt("client_id");
            int drug_id = resultSet.getInt("drug_id");
            int quantity = resultSet.getInt("quantity");
            Timestamp request_date = resultSet.getTimestamp("request_date");

            ps = connection.prepareStatement(selectOrderId);
            ps.setInt(1, client_id);
            ps.setInt(2, drug_id);
            ps.setInt(3, quantity);
            ps.setTimestamp(4, request_date);
            resultSet = ps.executeQuery();
            resultSet.next();
            int orderId = resultSet.getInt("id");
            double cost = resultSet.getDouble("cost");

            ps = connection.prepareStatement(updateOrders);
            ps.setInt(1, orderId);
            ps.executeUpdate();

            ps = connection.prepareStatement(returnMoney);
            ps.setDouble(1, cost);
            ps.setInt(2, client_id);
            ps.executeUpdate();

            ps = connection.prepareStatement(returnDrugs);
            ps.setDouble(1, quantity);
            ps.setInt(2, drug_id);
            ps.executeUpdate();

            response = "ok";
        } catch (SQLException exc) {
            response = "fail";
            throw new DaoException(exc);
        }
        return response;
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
            connection = DriverManager.getConnection(url, userName, password);

            ps = connection.prepareStatement(updateOrder);
            ps.setInt(1, pharmacistId);
            ps.setInt(2, orderId);
            ps.executeUpdate();

            response = "ok";
        } catch (SQLException exc) {
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
