package by.tc.online_pharmacy.dao.impl;


import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.dao.CommonDao;
import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.util.DaoErrorMessage;
import by.tc.online_pharmacy.dao.util.DrugListMaker;
import by.tc.online_pharmacy.dao.query.DrugQueryStore;
import by.tc.online_pharmacy.dao.query.UserQueryStore;
import by.tc.online_pharmacy.dao.util.TableColumnName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class CommonDaoImpl implements CommonDao {

    private static final Logger logger = LogManager.getLogger(CommonDaoImpl.class.getName());

    @Override
    public User signIn(String mobile, String password) throws DaoException {

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
    public List<Drug> takeDrugGroup(String group) throws DaoException {

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
    public List<Drug> takeDrugsByName(String name) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_ACTIVE_DRUGS_BY_NAME);
            ps.setString(1, name);
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
    public void cancelOrder(int orderId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_CANCEL_ORDER_STATUS);
            ps.setInt(1, orderId);
            ps.executeUpdate();

            ps = connection.prepareStatement(UserQueryStore.UPDATE_PLUS_BALANCE);
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

            connection.commit();

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
                connection.setAutoCommit(true);
            } catch (SQLException exc) {
                logger.log(Level.ERROR, DaoErrorMessage.SET_AUTO_COMMIT_TRUE_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

}