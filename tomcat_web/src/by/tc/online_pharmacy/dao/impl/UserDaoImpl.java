package by.tc.online_pharmacy.dao.impl;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.dao.UserDao;
import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;
import by.tc.online_pharmacy.dao.exception.DaoException;

import java.sql.*;

/**
 * Created by Евгений on 17.02.2017.
 */
public class UserDaoImpl implements UserDao {

    private final static String SIGN_IN_SELECT = "SELECT * FROM users WHERE " +
            "mobile_phone = ? AND password = ?";

    private final static String SIGN_UP_INSERT = "INSERT INTO users VALUES " +
            "(?,?,?,?,?,?,?)";

    private final static String SIGN_UP_SELECT = "SELECT id FROM users";

    private final static String IS_UNIQUE_SELECT = "SELECT mobile_phone " +
            "FROM users WHERE mobile_phone = ?";

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

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            ps = connection.prepareStatement(SIGN_IN_SELECT);
            ps.setString(1, mobile);
            ps.setString(2, password);
            resultSet = ps.executeQuery();

            User user = new User();

            if (resultSet.next()) {
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
                e.printStackTrace();
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
            resultSet = statement.executeQuery(SIGN_UP_SELECT);

            int n = 0;
            while (resultSet.next()) {
                n++;
            }
            n = n + 1;
            resultSet.next();

            ps = connection.prepareStatement(SIGN_UP_INSERT);
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
    public boolean isUnique(String mobile) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            ps = connection.prepareStatement(IS_UNIQUE_SELECT);
            ps.setString(1, mobile);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return false;
            } else {
                return true;
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
}
