package by.tc.online_pharmacy.dao.impl;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.dao.UserDao;
import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.query.UserQueryStore;

import java.sql.*;


public class UserDaoImpl implements UserDao {

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
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();////????
            }

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();//????
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
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();//????
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }
}