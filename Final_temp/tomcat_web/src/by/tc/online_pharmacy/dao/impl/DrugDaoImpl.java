package by.tc.online_pharmacy.dao.impl;

import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.dao.DrugDao;
import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.queries.DrugQueryStore;

import java.sql.*;
import java.util.*;
import java.util.Date;


public class DrugDaoImpl implements DrugDao {

    private List<Drug> takeDrugList(ResultSet resultSet) throws SQLException {
        final String ID = "id";
        final String NAME = "name";
        final String PHARM_GROUP = "pharm_group";
        final String FORM = "form";
        final String DRUG_AMOUNT = "drug_amount";
        final String ACTIVE_SUBSTANCES = "active_substances";
        final String COUNTRY = "country";
        final String DISPENSING = "dispensing";
        final String PRICE = "price";
        final String QUANTITY = "quantity";

        List<Drug> drugList = new ArrayList<>();

        while (resultSet.next()) {
            Drug drug = new Drug();

            drug.setId(resultSet.getInt(ID));
            drug.setName(resultSet.getString(NAME));
            drug.setGroup(resultSet.getString(PHARM_GROUP));
            drug.setForm(resultSet.getString(FORM));
            drug.setDrugAmount(resultSet.getString(DRUG_AMOUNT));
            drug.setActiveSubstances(resultSet.getString(ACTIVE_SUBSTANCES));
            drug.setCountry(resultSet.getString(COUNTRY));
            drug.setDispensing(resultSet.getString(DISPENSING));
            drug.setPrice(resultSet.getDouble(PRICE));
            drug.setQuantity(resultSet.getInt(QUANTITY));

            drugList.add(drug);
        }

        return drugList;
    }


    @Override
    public List<Drug> takeDrugGroup(String group) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_ACTIVE_DRUGS_BY_GROUP);
            ps.setString(1, group);
            resultSet = ps.executeQuery();

            return takeDrugList(resultSet);
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();///??????
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();///????
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public List<Drug> takeDrugGroupToOrder(String group) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_ACTIVE_DRUGS_BY_GROUP_TO_ORDER);
            ps.setString(1, group);
            resultSet = ps.executeQuery();

            return takeDrugList(resultSet);
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();///??????
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();///????
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public List<Drug> takeDrugsByName(String name) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_ACTIVE_DRUGS_BY_NAME);
            ps.setString(1, name);
            resultSet = ps.executeQuery();

            return takeDrugList(resultSet);
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();///??????
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();///????
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public RecipeDescription takeRecipeDescription(String recipeCode) throws DaoException {

        final String DRUG_ID = "drug_id";
        final String DRUG_NAME = "drug_name";
        final String PHARM_GROUP = "pharm_group";
        final String FORM = "form";
        final String DRUG_AMOUNT = "drug_amount";
        final String AS = "active_substances";
        final String COUNTRY = "country";
        final String PRICE = "price";
        final String QUANTITY = "quantity";
        final String COST = "cost";
        final String END_DATE = "end_date";
        final String STATUS = "status";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        RecipeDescription recipeDescription = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_RECIPE_DESCRIPTION_BY_CODE);
            ps.setString(1, recipeCode);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                recipeDescription = new RecipeDescription();
                recipeDescription.setRecipeCode(recipeCode);
                recipeDescription.setDrugId(resultSet.getInt(DRUG_ID));
                recipeDescription.setDrugName(resultSet.getString(DRUG_NAME));
                recipeDescription.setDrugGroup(resultSet.getString(PHARM_GROUP));
                recipeDescription.setDrugForm(resultSet.getString(FORM));
                recipeDescription.setDrugAmount(resultSet.getString(DRUG_AMOUNT));
                recipeDescription.setActiveSubstances(resultSet.getString(AS));
                recipeDescription.setCountry(resultSet.getString(COUNTRY));
                recipeDescription.setPrice(resultSet.getDouble(PRICE));
                recipeDescription.setQuantity(resultSet.getInt(QUANTITY));
                recipeDescription.setCost(resultSet.getDouble(COST));
                recipeDescription.setEndDate(resultSet.getTimestamp(END_DATE));
                recipeDescription.setStatus(resultSet.getString(STATUS));
            }

        } catch (SQLException | InterruptedException exc) {
            exc.printStackTrace();
            throw new DaoException(exc);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();///??????
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();///????
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return recipeDescription;
    }

    @Override
    public void addDrugQuantity(int id, int quantity) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            ps = connection.prepareStatement(DrugQueryStore.UPDATE_PLUS_DRUG_QUANTITY);
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
                e.printStackTrace();//???????????
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public void addNewDrug(Drug drug) throws DaoException {

        final String ID = "id";
        final String YES = "yes";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            ps = connection.prepareStatement(DrugQueryStore.SELECT_UNIQUE_TEST);
            ps.setString(1, drug.getName());
            ps.setString(2, drug.getForm());
            ps.setString(3, drug.getDrugAmount());
            ps.setString(4, drug.getActiveSubstances());
            ps.setString(5, drug.getCountry());
            ps.setString(6, drug.getDispensing());
            ps.setDouble(7, drug.getPrice());
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(ID);

                ps = connection.prepareStatement(DrugQueryStore.UPDATE_PLUS_DRUG_QUANTITY);
                ps.setInt(1, drug.getQuantity());
                ps.setInt(2, id);
                ps.executeUpdate();
            } else {
                ps = connection.prepareStatement(DrugQueryStore.INSERT_DRUG);
                ps.setString(1, drug.getName());
                ps.setString(2, drug.getGroup());
                ps.setString(3, drug.getForm());
                ps.setString(4, drug.getDrugAmount());
                ps.setString(5, drug.getActiveSubstances());
                ps.setString(6, drug.getCountry());
                ps.setString(7, drug.getDispensing());
                ps.setDouble(8, drug.getPrice());
                ps.setInt(9, drug.getQuantity());
                ps.setString(10, YES);
                ps.executeUpdate();
            }
        } catch (SQLException | InterruptedException exc){
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
    }


    @Override
    public void removeDrug(int id) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_DRUG_ACTIVE);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
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
    }


    @Override
    public int addOrder(Order order) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_BALANCE);
            ps.setInt(1, order.getClientId());
            resultSet = ps.executeQuery();
            resultSet.next();
            double balance = resultSet.getDouble("balance");

            if (balance < order.getCost()) {
                throw new DaoException("is not enough money");
            }

            ps = connection.prepareStatement(DrugQueryStore.SELECT_DRUG_QUANTITY);
            ps.setInt(1, order.getDrugId());
            resultSet = ps.executeQuery();
            resultSet.next();
            int quantity = resultSet.getInt("quantity");

            if (quantity < order.getQuantity()) {
                throw new DaoException("Unacceptable quantity of drug");
            }

            ps = connection.prepareStatement(DrugQueryStore.INSERT_ORDER);
            ps.setInt(1, order.getClientId());
            ps.setInt(2, order.getDrugId());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, order.getCost());
            ps.setString(5, order.getStatus());
            ps.executeUpdate();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(DrugQueryStore.SELECT_LAST_INSERT_ID);
            resultSet.next();
            int id = resultSet.getInt(1);

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_MINUS_BALANCE);
            ps.setDouble(1, order.getCost());
            ps.setInt(2, order.getClientId());
            ps.executeUpdate();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_MINUS_DRUG_QUANTITY);
            ps.setInt(1, order.getQuantity());
            ps.setInt(2, order.getDrugId());
            ps.executeUpdate();

            return id;
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
    public Date takeRecipeEndDate(String recipeCode) throws DaoException {

        Date endDate = null;

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
             connection = ConnectionPool.getInstance().takeConnection();

             ps = connection.prepareStatement(DrugQueryStore.SELECT_END_RECIPE_DATE);
             ps.setString(1, recipeCode);
             resultSet = ps.executeQuery();

             if (resultSet.next()) {
                 endDate = resultSet.getTimestamp("end_date");
             }
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return endDate;
    }


    @Override
    public void closeRecipe(String recipeCode) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_RECIPE_STATUS_CLOSED);
            ps.setString(1, recipeCode);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
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
    }

    @Override
    public void linkOrderAndRecipe(int orderId, String recipeCode) throws
            DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.INSERT_ORDER_RECIPE);
            ps.setInt(1, orderId);
            ps.setString(2, recipeCode);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            exc.printStackTrace();
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
    }

    private List<OrderDescription> takeOrderList(ResultSet resultSet) throws SQLException {
        final String ORDER_ID = "order_id";
        final String REQUEST_DATE = "request_date";
        final String CLIENT_MOBILE = "client_mobile";
        final String NAME = "name";
        final String PHARM_GROUP = "pharm_group";
        final String FORM = "form";
        final String DRUG_AMOUNT = "drug_amount";
        final String ACTIVE_SUBSTANCES = "active_substances";
        final String COUNTRY = "country";
        final String QUANTITY = "quantity";
        final String COST = "cost";

        List<OrderDescription> orderList = new ArrayList<>();

        while (resultSet.next()) {
            OrderDescription orderDescription = new OrderDescription();

            orderDescription.setOrderId(resultSet.getInt(ORDER_ID));
            orderDescription.setRequestDate(resultSet.getTimestamp(REQUEST_DATE));
            orderDescription.setClientMobile(resultSet.getString(CLIENT_MOBILE));
            orderDescription.setDrugName(resultSet.getString(NAME));
            orderDescription.setPharmacologicalGroup(resultSet.getString(PHARM_GROUP));
            orderDescription.setDrugForm(resultSet.getString(FORM));
            orderDescription.setDrugAmount(resultSet.getString(DRUG_AMOUNT));
            orderDescription.setActiveSubstances(resultSet.getString(ACTIVE_SUBSTANCES));
            orderDescription.setProductingCountry(resultSet.getString(COUNTRY));
            orderDescription.setQuantity(resultSet.getInt(QUANTITY));
            orderDescription.setCost(resultSet.getDouble(COST));

            orderList.add(orderDescription);
        }

        return orderList;
    }

    @Override
    public List<OrderDescription> takePharmacistOrderList() throws DaoException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DrugQueryStore.SELECT_NEW_ORDERS);

            return takeOrderList(resultSet);
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
    public List<OrderDescription> takeClientOrderList(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_NEW_ORDERS_BY_CLIENT_ID);
            ps.setInt(1, id);
            resultSet = ps.executeQuery();

            return takeOrderList(resultSet);
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }


    @Override
    public void send(int orderId, int pharmacistId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_SENT_ORDER_STATUS);
            ps.setInt(1, pharmacistId);
            ps.setInt(2, orderId);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
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
    }

    @Override
    public void cancelOrder(int orderId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_CANCEL_ORDER_STATUS);
            ps.setInt(1, orderId);
            ps.executeUpdate();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_PLUS_BALANCE);
            ps.setInt(1, orderId);
            ps.setInt(2, orderId);
            ps.executeUpdate();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_RETURN_DRUG_QUANTITY);
            ps.setInt(1, orderId);
            ps.setInt(2, orderId);
            ps.executeUpdate();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_RECIPE_CODE_BY_ORDER_ID);
            ps.setInt(1, orderId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                ps = connection.prepareStatement(DrugQueryStore.UPDATE_RECIPE_STATUS_OPEN);
                ps.setString(1, resultSet.getString(1));
                ps.executeUpdate();
            }

        } catch (SQLException | InterruptedException exc) {
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
    }


    @Override
    public void addRecipeExtensionRequest(String recipeCode) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.INSERT_RECIPE_EXTENSION_REQUEST);
            ps.setString(1, recipeCode);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
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
    }


    @Override
    public Map<Integer, String> takeRecipeExtensionRequestList() throws DaoException {

        final String ID = "id";
        final String RECIPE_CODE = "recipe_code";

        Map<Integer, String> recipeExtensionRequestList = new LinkedHashMap<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(DrugQueryStore.SELECT_NEW_RECIPE_EXTENSION_REQUEST);

            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String recipeCode = resultSet.getString(RECIPE_CODE);
                recipeExtensionRequestList.put(id, recipeCode);
            }

            return recipeExtensionRequestList;
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }


    @Override
    public void approve(int id, String recipeCode) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.
                    UPDATE_RECIPE_EXTENSION_REQUEST_STATUS_APPROVED);
            ps.setInt(1, id);
            ps.executeUpdate();

            ps = connection.prepareStatement(DrugQueryStore.
                    UPDATE_RECIPE_STATUS_OPEN_AND_EXTEND_DATE);
            ps.setString(1, recipeCode);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            exc.printStackTrace();
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public void deny(int id, String recipeCode) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.
                    UPDATE_RECIPE_EXTENSION_REQUEST_STATUS_DENIED);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public void sendFeedback(int recipeId, String feedback) throws DaoException {

        /*final String updateRecipe = "UPDATE recipes SET feedback = ?, " +
                "request_date = request_date WHERE id = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            ps = connection.prepareStatement(updateRecipe);
            ps.setString(1, feedback);
            ps.setInt(2, recipeId);
            ps.executeUpdate();

        } catch (SQLException exc) {
            throw new DaoException(exc);
        }*/
    }

}
