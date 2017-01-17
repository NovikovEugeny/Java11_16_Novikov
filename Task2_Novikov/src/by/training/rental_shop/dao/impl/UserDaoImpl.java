package by.training.rental_shop.dao.impl;

import by.training.rental_shop.bean.User;
import by.training.rental_shop.dao.UserDao;
import by.training.rental_shop.dao.exception.DaoException;

import java.sql.*;

/**
 * Created by Евгений on 12.01.2017.
 */
public class UserDaoImpl implements UserDao{

    @Override
    public User signIn(String mobilePhone, String password) throws DaoException {
        User user = new User();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String url = "jdbc:mysql://localhost:3306/rentalshop";
        String userName = "root";
        String dbPassword = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException exc) {
            throw new DaoException("driver is not include", exc);
        }

        try {
            connection = DriverManager.getConnection(url, userName, dbPassword);
        } catch (SQLException exc) {
            throw new DaoException("connection error", exc);
        }

        try {
            statement = connection.createStatement();

            String query = "select * from users where password = '" +
                    password + "' and mobile_phone = '" + mobilePhone + "';";

            resultSet = statement.executeQuery(query);
            resultSet.next();

                user.setName(resultSet.getString("name"));
                user.setPhone(resultSet.getString("mobile_phone"));
                user.setPassword(resultSet.getString("password"));

            return user;
        } catch (SQLException exc) {
                throw new DaoException("incorrect mobile or password", exc);
        } finally {
                try {
                    if (resultSet != null) {resultSet.close();}
                    if (statement != null) {statement.close();}
                    if (connection != null) {connection.close();}
                } catch(SQLException exc) {
                    throw new DaoException("closing error" ,exc);
                }
        }
    }

    @Override
    public void registration(User user) throws DaoException {
        String name = user.getName();
        String mobile_phone = user.getPhone();
        String userPassword = user.getPassword();
        String hasRent = "no";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String url = "jdbc:mysql://localhost:3306/rentalshop";
        String userName = "root";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException exc) {
            throw new DaoException("driver is not include", exc);
        }

        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException exc) {
            throw new DaoException("connection error", exc);
        }

        try {
            statement = connection.createStatement();
            int rows = 0;
            resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                rows++;
            }
            rows = rows + 1;
            resultSet.close();

            String query = "insert into users values (" + rows + ", '" +
                    name + "', '" + mobile_phone + "', '" + userPassword + "', '" +
                    hasRent + "');";

            statement.execute(query);
        } catch (SQLException exc) {
            throw new DaoException("the query is not performed", exc);
        } finally {
            try {
                if (resultSet != null) {resultSet.close();}
                if (statement != null) {statement.close();}
                if (connection != null) {connection.close();}
            } catch(SQLException exc) {
                throw new DaoException("closing error" ,exc);
            }
        }

    }

}
