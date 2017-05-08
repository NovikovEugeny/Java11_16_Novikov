package by.tc.onlinepharmacy.dao.impl;


import by.tc.onlinepharmacy.bean.*;
import by.tc.onlinepharmacy.dao.ClientDAO;
import by.tc.onlinepharmacy.dao.connectionpool.ConnectionPool;
import by.tc.onlinepharmacy.dao.exception.DAOException;
import by.tc.onlinepharmacy.dao.query.*;
import by.tc.onlinepharmacy.dao.util.DaoErrorMessage;
import by.tc.onlinepharmacy.dao.util.DrugListMaker;
import by.tc.onlinepharmacy.dao.util.OrderDescriptionListMaker;
import by.tc.onlinepharmacy.dao.util.TableColumnName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the interface {@link by.tc.onlinepharmacy.dao.ClientDAO ClientDAO}.
 */
public class ClientDAOImpl implements ClientDAO {

    private static final Logger LOGGER = LogManager.getLogger(ClientDAOImpl.class.getName());

    /**
     * Add new user in the table "user" of DB.
     *
     * @param user DTO {@link by.tc.onlinepharmacy.bean.User User} with data about user.
     * @return user id in the table of DB.
     * @throws DAOException
     */
    @Override
    public int addUser(User user) throws DAOException {

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
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Search input mobile in the DB.
     *
     * @param mobile
     * @return false if mobile already exists.
     * @throws DAOException
     */
    @Override
    public boolean isUnique(String mobile) throws DAOException {

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
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Adds an entry to the table "account" for a new user.
     *
     * @param clientId
     * @throws DAOException
     */
    @Override
    public void addAccount(int clientId) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(AccountQueryStore.INSERT_NEW_ACCOUNT);
            ps.setInt(1, clientId);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Returns current balance by client id.
     *
     * @param clientId
     * @return current balance.
     * @throws DAOException
     */
    @Override
    public double takeClientBalance(int clientId) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(AccountQueryStore.SELECT_BALANCE);
            ps.setInt(1, clientId);
            resultSet = ps.executeQuery();

            resultSet.next();
            return resultSet.getDouble(1);

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Returns list of drugs of the specified group.
     *
     * @param group name of the pharmacological group.
     * @return list of DTOs {@link by.tc.onlinepharmacy.bean.Drug Drug} with data about drug.
     * @throws DAOException
     */
    @Override
    public List<Drug> takeDrugGroupToOrder(String group) throws DAOException {

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
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Adds an entry to the table "orders", take money from the client account,
     * reduces the quantity of drugs in the table "drugs".
     *
     * @param order DTO {@link by.tc.onlinepharmacy.bean.Order Order} with data about order.
     * @return id of an order for linking the recipe with the order in the table "order_recipe",
     * when ordering a prescription drug.
     * @throws DAOException
     */
    @Override
    public int addOrder(Order order) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(OrderQueryStore.INSERT_ORDER);
            ps.setInt(1, order.getClientId());
            ps.setInt(2, order.getDrugId());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, order.getCost());
            ps.setString(5, order.getStatus());
            ps.executeUpdate();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(CommonQueryStore.SELECT_LAST_INSERT_ID);
            resultSet.next();
            int id = resultSet.getInt(1);

            ps = connection.prepareStatement(AccountQueryStore.UPDATE_MINUS_BALANCE);
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
                LOGGER.log(Level.ERROR, DaoErrorMessage.ROLLBACK_ERROR, e);
            }
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, DaoErrorMessage.SET_AUTO_COMMIT_TRUE_ERROR, e);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Returns a list of client orders, that have not yet been sent.
     *
     * @param id
     * @return list of DTOs {@link by.tc.onlinepharmacy.bean.OrderDescription OrderDescription}.
     * @throws DAOException
     */
    @Override
    public List<OrderDescription> takeClientOrderList(int id) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(CommonQueryStore.SELECT_NEW_ORDERS_BY_CLIENT_ID);
            ps.setInt(1, id);
            resultSet = ps.executeQuery();

            return OrderDescriptionListMaker.makeList(resultSet);

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Returns a current drug quantity by id.
     *
     * @param drugId
     * @return drug quantity
     * @throws DAOException
     */
    @Override
    public int takeDrugQuantity(int drugId) throws DAOException {

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
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Returns a current drug price by id.
     *
     * @param drugId
     * @return drug price
     * @throws DAOException
     */
    @Override
    public double takeDrugPrice(int drugId) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_DRUG_PRICE);
            ps.setInt(1, drugId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble(1);

            } else {
                return 0;
            }

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Returns an object with recipe description by recipe code.
     *
     * @param recipeCode
     * @return DTO {@link by.tc.onlinepharmacy.bean.RecipeDescription RecipeDescription}.
     * @throws DAOException
     */
    @Override
    public RecipeDescription takeRecipeDescription(String recipeCode) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(CommonQueryStore.SELECT_RECIPE_DESCRIPTION_BY_CODE);
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
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Returns date of recipe closure by recipe code.
     *
     * @param recipeCode
     * @return date of recipe closure
     * @throws DAOException
     */
    @Override
    public Date takeRecipeEndDate(String recipeCode) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(RecipeQueryStore.SELECT_END_RECIPE_DATE);
            ps.setString(1, recipeCode);
            resultSet = ps.executeQuery();

            Date endDate = null;

            if (resultSet.next()) {
                endDate = resultSet.getTimestamp(TableColumnName.END_DATE);
            }

            return endDate;

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Changes the status of the entry in the table "recipe" to closed by recipe code.
     *
     * @param recipeCode
     * @throws DAOException
     */
    @Override
    public void closeRecipe(String recipeCode) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(RecipeQueryStore.UPDATE_RECIPE_STATUS_CLOSED);
            ps.setString(1, recipeCode);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Adds an entry to the table "order_recipe".
     *
     * @param orderId
     * @param recipeCode
     * @throws DAOException
     */
    @Override
    public void linkOrderAndRecipe(int orderId, String recipeCode) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(OrderRecipeQueryStore.INSERT_ORDER_RECIPE);
            ps.setInt(1, orderId);
            ps.setString(2, recipeCode);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Returns the list of order description, which were sent, to alert a client about sending,
     * by client id.
     *
     * @param clientId
     * @return list of DTOs {@link by.tc.onlinepharmacy.bean.OrderDescription OrderDescription}.
     * @throws DAOException
     */
    @Override
    public List<OrderDescription> takeSendingMessageList(int clientId) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(CommonQueryStore.SELECT_SENDING_ORDERS_BY_CLIENT_ID);
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
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Returns a descriptions of recipe extension requests, to alert a client about doctor
     * response by client id.
     *
     * @param clientId
     * @return list of DTOs {@link by.tc.onlinepharmacy.bean.RERDescription RERDescription}
     * @throws DAOException
     */
    @Override
    public List<RERDescription> takeDoctorResponseMessageList(int clientId) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(RERQueryStore.SELECT_DOCTOR_RESPONSE_BY_CLIENT_ID);
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

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Adds an entry to the table "recipe_extension_request".
     *
     * @param recipeCode
     * @param clientId
     * @throws DAOException
     */
    @Override
    public void addRecipeExtensionRequest(String recipeCode, int clientId) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(RERQueryStore.INSERT_RECIPE_EXTENSION_REQUEST);
            ps.setString(1, recipeCode);
            ps.setInt(2, clientId);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Changes the status of the entry in the table "orders" to received by order id.
     *
     * @param orderId
     * @throws DAOException
     */
    @Override
    public void reportAboutDelivery(int orderId) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(OrderQueryStore.UPDATE_RECEIVE_ORDER_STATUS);
            ps.setInt(1, orderId);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * In the table "recipe_extension_request" in the field "is_read" install
     * the value "yes".
     *
     * @param requestId
     * @throws DAOException
     */
    @Override
    public void deleteMessage(int requestId) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(RERQueryStore.UPDATE_RECIPE_EXTENSION_REQUEST_IS_READ_YES);
            ps.setInt(1, requestId);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Returns an order descriptions by client id.
     *
     * @param clientId
     * @return list of DTOs {@link by.tc.onlinepharmacy.bean.OrderDescription OrderDescription}
     * @throws DAOException
     */
    @Override
    public List<OrderDescription> takeShoppingList(int clientId) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(CommonQueryStore.SELECT_ORDER_DESCRIPTION_BY_CLIENT_ID);
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
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }


    /**
     * Search the recipe application by recipe code in the table "recipe_extension_request".
     *
     * @param recipeCode
     * @return true if such recipe application is already exists.
     * @throws DAOException
     */
    @Override
    public boolean isDuplicateApplication(String recipeCode) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(RERQueryStore.IS_UNIQUE_APPLICATION_SELECT);
            ps.setString(1, recipeCode);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Increases client balance in the table "account" by client id.
     *
     * @param clientId
     * @param amount
     * @throws DAOException
     */
    @Override
    public void replenishBalance(int clientId, double amount) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(AccountQueryStore.UPDATE_REPLENISH_BALANCE);
            ps.setDouble(1, amount);
            ps.setInt(2, clientId);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }
}