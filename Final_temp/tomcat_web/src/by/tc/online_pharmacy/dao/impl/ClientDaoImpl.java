package by.tc.online_pharmacy.dao.impl;


import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.dao.ClientDao;
import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.util.DaoErrorMessage;
import by.tc.online_pharmacy.dao.util.DrugListMaker;
import by.tc.online_pharmacy.dao.util.OrderDescriptionListMaker;
import by.tc.online_pharmacy.dao.query.DrugQueryStore;
import by.tc.online_pharmacy.dao.query.UserQueryStore;
import by.tc.online_pharmacy.dao.util.TableColumnName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientDaoImpl implements ClientDao {

    private static final Logger logger = LogManager.getLogger(ClientDaoImpl.class.getName());

    @Override
    public int signUp(User user) throws DaoException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(UserQueryStore.SIGN_UP_SELECT);

            int n = 0;
            while (resultSet.next()) {
                n++;
            }
            n = n + 1;

            ps = connection.prepareStatement(UserQueryStore.SIGN_UP_INSERT);
            ps.setInt(1, n);
            ps.setString(2, user.getPosition());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getName());
            ps.setString(5, user.getPatronymic());
            ps.setString(6, user.getMobilePhone());
            ps.setString(7, user.getPassword());
            ps.executeUpdate();

            return n;
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            try {
                if (statement != null) { statement.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public boolean isUnique(String mobile) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(UserQueryStore.IS_UNIQUE_SELECT);
            ps.setString(1, mobile);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return false;
            }

            return true;
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public void addAccount(int clientId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(UserQueryStore.INSERT_NEW_ACCOUNT);
            ps.setInt(1, clientId);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public double takeClientBalance(int clientId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(UserQueryStore.SELECT_BALANCE);
            ps.setInt(1, clientId);
            resultSet = ps.executeQuery();

            resultSet.next();
            return resultSet.getDouble(1);
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
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

            return DrugListMaker.makeList(resultSet);
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
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
            connection.setAutoCommit(false);

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

            ps = connection.prepareStatement(UserQueryStore.UPDATE_MINUS_BALANCE);
            ps.setDouble(1, order.getCost());
            ps.setInt(2, order.getClientId());
            ps.executeUpdate();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_MINUS_DRUG_QUANTITY);
            ps.setInt(1, order.getQuantity());
            ps.setInt(2, order.getDrugId());
            ps.executeUpdate();

            connection.commit();

            return id;
        } catch (SQLException | InterruptedException exc) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.log(Level.ERROR, DaoErrorMessage.ROLLBACK_ERROR, e);
            }
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            try {
                if (statement != null) { statement.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.log(Level.ERROR, DaoErrorMessage.SET_AUTO_COMMIT_TRUE_ERROR, e);
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

            return OrderDescriptionListMaker.makeList(resultSet);
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public int takeDrugQuantity(int drugId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_DRUG_QUANTITY);
            ps.setInt(1, drugId);
            resultSet = ps.executeQuery();

            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public RecipeDescription takeRecipeDescription(String recipeCode) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_RECIPE_DESCRIPTION_BY_CODE);
            ps.setString(1, recipeCode);
            resultSet = ps.executeQuery();

            RecipeDescription recipeDescription = null;

            if (resultSet.next()) {
                recipeDescription = new RecipeDescription();
                recipeDescription.setRecipeCode(recipeCode);
                recipeDescription.setDrugId(resultSet.getInt(TableColumnName.DRUG_ID));
                recipeDescription.setDrugName(resultSet.getString(TableColumnName.DRUG_NAME));
                recipeDescription.setDrugGroup(resultSet.getString(TableColumnName.PHARM_GROUP));
                recipeDescription.setDrugForm(resultSet.getString(TableColumnName.FORM));
                recipeDescription.setDrugAmount(resultSet.getString(TableColumnName.DRUG_AMOUNT));
                recipeDescription.setActiveSubstances(resultSet.getString(TableColumnName.ACTIVE_SUBSTANCES));
                recipeDescription.setCountry(resultSet.getString(TableColumnName.COUNTRY));
                recipeDescription.setPrice(resultSet.getDouble(TableColumnName.PRICE));
                recipeDescription.setQuantity(resultSet.getInt(TableColumnName.QUANTITY));
                recipeDescription.setCost(resultSet.getDouble(TableColumnName.COST));
                recipeDescription.setEndDate(resultSet.getTimestamp(TableColumnName.END_DATE));
                recipeDescription.setStatus(resultSet.getString(TableColumnName.STATUS));
            }

            return recipeDescription;
        } catch (SQLException | InterruptedException exc) {
            exc.printStackTrace();
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public Date takeRecipeEndDate(String recipeCode) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_END_RECIPE_DATE);
            ps.setString(1, recipeCode);
            resultSet = ps.executeQuery();

            Date endDate = null;

            if (resultSet.next()) {
                endDate = resultSet.getTimestamp(TableColumnName.END_DATE);
            }

            return endDate;
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
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
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public void linkOrderAndRecipe(int orderId, String recipeCode) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.INSERT_ORDER_RECIPE);
            ps.setInt(1, orderId);
            ps.setString(2, recipeCode);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public List<OrderDescription> takeSendingMessageList(int clientId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_SENDING_ORDERS_BY_CLIENT_ID);
            ps.setInt(1, clientId);
            resultSet = ps.executeQuery();

            List<OrderDescription> orderDescriptionList = new ArrayList<>();

            while (resultSet.next()) {
                OrderDescription orderDescription = new OrderDescription();
                orderDescription.setOrderId(resultSet.getInt(TableColumnName.ID));
                orderDescription.setDrugName(resultSet.getString(TableColumnName.NAME));
                orderDescription.setDrugAmount(resultSet.getString(TableColumnName.DRUG_AMOUNT));
                orderDescription.setQuantity(resultSet.getInt(TableColumnName.QUANTITY));
                orderDescription.setProductingCountry(resultSet.getString(TableColumnName.COUNTRY));
                orderDescription.setResponseDate(resultSet.getTimestamp(TableColumnName.RESPONSE_DATE));

                orderDescriptionList.add(orderDescription);
            }

            return orderDescriptionList;
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public List<RERDescription> takeDoctorResponseMessageList(int clientId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_DOCTOR_RESPONSE_BY_CLIENT_ID);
            ps.setInt(1, clientId);
            resultSet = ps.executeQuery();

            List<RERDescription> rerDescriptionList = new ArrayList<>();

            while (resultSet.next()) {
                RERDescription rerDescription = new RERDescription();
                rerDescription.setId(resultSet.getInt(TableColumnName.ID));
                rerDescription.setRecipeCode(resultSet.getString(TableColumnName.RECIPE_CODE));
                rerDescription.setResponseDate(resultSet.getTimestamp(TableColumnName.RESPONSE_DATE));
                rerDescription.setStatus(resultSet.getString(TableColumnName.STATUS));

                rerDescriptionList.add(rerDescription);
            }

            return rerDescriptionList;
        } catch(SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public void addRecipeExtensionRequest(String recipeCode, int clientId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.INSERT_RECIPE_EXTENSION_REQUEST);
            ps.setString(1, recipeCode);
            ps.setInt(2, clientId);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public void reportAboutDelivery(int orderId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_RECEIVE_ORDER_STATUS);
            ps.setInt(1, orderId);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public void hideMessage(int requestId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_RECIPE_EXTENSION_REQUEST_IS_READ_YES);
            ps.setInt(1, requestId);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }


    @Override
    public List<OrderDescription> takeShoppingList(int clientId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_ORDER_DESCRIPTION_BY_CLIENT_ID);
            ps.setInt(1, clientId);
            resultSet = ps.executeQuery();

            List<OrderDescription> shoppingList = new ArrayList<>();

            while (resultSet.next()) {
                OrderDescription orderDescription = new OrderDescription();
                orderDescription.setRequestDate(resultSet.getTimestamp(TableColumnName.REQUEST_DATE));
                orderDescription.setDrugName(resultSet.getString(TableColumnName.NAME));
                orderDescription.setPharmacologicalGroup(resultSet.getString(TableColumnName.GROUP));
                orderDescription.setDrugAmount(resultSet.getString(TableColumnName.DRUG_AMOUNT));
                orderDescription.setProductingCountry(resultSet.getString(TableColumnName.COUNTRY));
                orderDescription.setQuantity(resultSet.getInt(TableColumnName.QUANTITY));
                orderDescription.setCost(resultSet.getDouble(TableColumnName.COST));

                shoppingList.add(orderDescription);
            }

            return shoppingList;
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

}