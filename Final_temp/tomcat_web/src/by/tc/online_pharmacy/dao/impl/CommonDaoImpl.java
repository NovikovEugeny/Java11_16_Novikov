package by.tc.online_pharmacy.dao.impl;


import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.dao.CommonDao;
import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.impl.util.DrugListBuilder;
import by.tc.online_pharmacy.dao.query.DrugQueryStore;
import by.tc.online_pharmacy.dao.query.UserQueryStore;

import java.sql.*;
import java.util.List;

public class CommonDaoImpl implements CommonDao {

    @Override
    public User signIn(String mobile, String password) throws DaoException {

        final String ID = "id";
        final String POSITION = "position";
        final String SURNAME = "surname";
        final String NAME = "name";
        final String PATRONYMIC = "patronymic";
        final String MOBILE_PHONE = "mobile_phone";
        final String PASSWORD = "password";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        User user = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            ps = connection.prepareStatement(UserQueryStore.SIGN_IN_SELECT);
            ps.setString(1, mobile);
            ps.setString(2, password);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(ID));
                user.setPosition(resultSet.getString(POSITION));
                user.setSurname(resultSet.getString(SURNAME));
                user.setName(resultSet.getString(NAME));
                user.setPatronymic(resultSet.getString(PATRONYMIC));
                user.setMobilePhone(resultSet.getString(MOBILE_PHONE));
                user.setPassword(resultSet.getString(PASSWORD));
            }

            return user;
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();//???
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

            return DrugListBuilder.getDrugList(resultSet);
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
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

            return DrugListBuilder.getDrugList(resultSet);
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
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
                e.printStackTrace();
            }
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

}
