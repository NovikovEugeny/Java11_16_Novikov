package by.tc.onlinepharmacy.dao.impl;


import by.tc.onlinepharmacy.bean.Drug;
import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.dao.CommonDAO;
import by.tc.onlinepharmacy.dao.connectionpool.ConnectionPool;
import by.tc.onlinepharmacy.dao.exception.DAOException;
import by.tc.onlinepharmacy.dao.query.*;
import by.tc.onlinepharmacy.dao.util.DaoErrorMessage;
import by.tc.onlinepharmacy.dao.util.DrugListMaker;
import by.tc.onlinepharmacy.dao.util.TableColumnName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the interface {@link by.tc.onlinepharmacy.dao.CommonDAO CommonDAO}.
 */
public class CommonDAOImpl implements CommonDAO {

    private static final Logger LOGGER = LogManager.getLogger(CommonDAOImpl.class.getName());

    /**
     * If input mobile and password are exists, then return corresponding user.
     *
     * @param mobile
     * @param password
     * @return DTO {@link by.tc.onlinepharmacy.bean.User User}.
     * @throws DAOException
     */
    @Override
    public User signIn(String mobile, String password) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            ps = connection.prepareStatement(UserQueryStore.SIGN_IN_SELECT);
            ps.setString(1, mobile);
            ps.setString(2, password);
            resultSet = ps.executeQuery();

            User user = null;

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(TableColumnName.ID));
                user.setPosition(resultSet.getString(TableColumnName.POSITION));
                user.setSurname(resultSet.getString(TableColumnName.SURNAME));
                user.setName(resultSet.getString(TableColumnName.NAME));
                user.setPatronymic(resultSet.getString(TableColumnName.PATRONYMIC));
                user.setMobilePhone(resultSet.getString(TableColumnName.MOBILE_PHONE));
                user.setPassword(resultSet.getString(TableColumnName.PASSWORD));
            }

            return user;

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
    public List<Drug> takeDrugGroup(String group) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_ACTIVE_DRUGS_BY_GROUP);
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
     * Returns list of drugs with the specified name.
     *
     * @param name name of the drug.
     * @return list of DTOs {@link by.tc.onlinepharmacy.bean.Drug Drug} with data about drug.
     * @throws DAOException
     */
    @Override
    public List<Drug> takeDrugsByName(String name) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_ACTIVE_DRUGS_BY_NAME);
            ps.setString(1, name.toUpperCase() + "%");
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
     * This method cancels order by order id(updates order status on "canceled",
     * returns money to the client balance, returns drug quantity and if the drug
     * was ordered by recipe, then opens that recipe).
     *
     * @param orderId
     * @throws DAOException
     */
    @Override
    public void cancelOrder(int orderId) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(OrderQueryStore.UPDATE_CANCEL_ORDER_STATUS);
            ps.setInt(1, orderId);
            ps.executeUpdate();

            ps = connection.prepareStatement(AccountQueryStore.UPDATE_PLUS_BALANCE);
            ps.setInt(1, orderId);
            ps.setInt(2, orderId);
            ps.executeUpdate();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_RETURN_DRUG_QUANTITY);
            ps.setInt(1, orderId);
            ps.setInt(2, orderId);
            ps.executeUpdate();

            ps = connection.prepareStatement(OrderRecipeQueryStore.SELECT_RECIPE_CODE_BY_ORDER_ID);
            ps.setInt(1, orderId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                ps = connection.prepareStatement(RecipeQueryStore.UPDATE_RECIPE_STATUS_OPEN);
                ps.setString(1, resultSet.getString(1));
                ps.executeUpdate();
            }

            connection.commit();

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
                connection.setAutoCommit(true);
            } catch (SQLException exc) {
                LOGGER.log(Level.ERROR, DaoErrorMessage.SET_AUTO_COMMIT_TRUE_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

}