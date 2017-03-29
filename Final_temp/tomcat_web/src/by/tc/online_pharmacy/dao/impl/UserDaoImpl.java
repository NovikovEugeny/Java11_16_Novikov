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

    @Override
    public User signIn(String mobilePhone, String password) throws DaoException {

        final String query = "SELECT * FROM users WHERE " +
                "mobile_phone = ? AND password = ?";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, mobilePhone);
            ps.setString(2, password);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setPosition(resultSet.getString("position"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setPatronymic(resultSet.getString("patronymic"));
                user.setMobilePhone(resultSet.getString("mobile_phone"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
            return null;
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {

        }
    }

    @Override
    public int signUp(User user) throws DaoException {

        final String insertQuery = "INSERT INTO users VALUES (?,?,?,?,?,?,?)";

        final String selectQuery = "SELECT id FROM users";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);

            int n = 0;
            while (resultSet.next()) {
                n++;
            }
            n = n + 1;
            resultSet.next();

            ps = connection.prepareStatement(insertQuery);
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
        }
    }

    @Override
    public boolean isUnique(String mobile) throws DaoException {

        final String testQuery = "SELECT mobile_phone FROM users WHERE mobile_phone = ?";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            ps = connection.prepareStatement(testQuery);
            ps.setString(1, mobile);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        }
    }
}
