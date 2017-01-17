package by.training.rental_shop.dao.impl;

import by.training.rental_shop.bean.SportEquipment;
import by.training.rental_shop.bean.User;
import by.training.rental_shop.dao.GoodDao;
import by.training.rental_shop.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 07.01.2017.
 */
public class GoodDaoImpl implements GoodDao {

    private List<SportEquipment> readGoods(String query) throws DaoException {
        List<SportEquipment> records = new ArrayList<>();

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
            throw new DaoException("there is not connection", exc);
        }

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                SportEquipment sE = new SportEquipment();
                sE.setCategory(resultSet.getString("c_title"));
                sE.setTitle(resultSet.getString("g_title"));
                sE.setPrice(Double.parseDouble(resultSet.getString("$price")));

                records.add(sE);
            }

            if (records.isEmpty()) {
                throw new DaoException("there are no suitable items");
            }

            return records;
        } catch (SQLException exc) {
            throw new DaoException("the query is not performed", exc);
        } finally {
            try {
                if (resultSet != null) {resultSet.close();}
                if (statement != null) {statement.close();}
                if (connection != null) {connection.close();}
            } catch(SQLException exc) {
                throw new DaoException("closing error", exc);
            }
        }
    }

    @Override
    public List<SportEquipment> getAvailableGoods() throws DaoException {
        String availableGoodsQuery = "select goods.cat_id, categories.title c_title, " +
                "goods.title g_title, goods.$price, goods.status from goods left outer " +
                "join categories on goods.cat_id = categories.id " +
                "where goods.status = 'in stock';";
        return readGoods(availableGoodsQuery);
    }

    @Override
    public List<SportEquipment> findGood(String title) throws DaoException {
        String findGoodsQuery = "select categories.title c_title, " +
                "goods.title g_title, goods.$price, goods.status from goods left outer " +
                "join categories on goods.cat_id = categories.id " +
                "where goods.status = 'in stock' and goods.title like '" + title + "%';";
        return readGoods(findGoodsQuery);
    }

    @Override
    public SportEquipment chooseGood(String title) throws DaoException {
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
            throw new DaoException("there is not connection", exc);
        }

        try {
            statement = connection.createStatement();

            String query = "select categories.title c_title, goods.title g_title, " +
                    "goods.$price, goods.status from goods left outer join " +
                    "categories on goods.cat_id = categories.id where " +
                    "goods.status = 'in stock' and goods.title = '" + title + "';";

            resultSet = statement.executeQuery(query);
            resultSet.next();

            SportEquipment sE = new SportEquipment();

            sE.setCategory(resultSet.getString("c_title"));
            sE.setTitle(resultSet.getString("g_title"));
            sE.setPrice(Double.parseDouble(resultSet.getString("$price")));

            return sE;
        } catch (SQLException exc) {
            throw new DaoException("the query is not performed", exc);
        } finally {
            try {
                if (resultSet != null) {resultSet.close();}
                if (statement != null) {statement.close();}
                if (connection != null) {connection.close();}
            } catch(SQLException exc) {
                throw new DaoException("closing error", exc);
            }
        }
    }

    @Override
    public List<SportEquipment> getRentedGoods(User user) throws DaoException {
        List<SportEquipment> records = new ArrayList<>();

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
            throw new DaoException("there is not connection", exc);
        }

        try {
            statement = connection.createStatement();

            String query = "select good_id from rents where user_id =" +
                    "(select id from users where mobile_phone = '" +
                    user.getPhone() + "');";

            resultSet = statement.executeQuery(query);
            //коллекция содержит id товаров, которые
            //взял данный пользователь
            List<String> goodId = new ArrayList<>();
            while (resultSet.next()) {
                goodId.add(resultSet.getString(1));
            }
            resultSet.close();;

            for (String str : goodId) {
                query = "select categories.title c_title, " +
                        "goods.title g_title, goods.$price from goods " +
                        "left outer join categories on goods.cat_id = categories.id " +
                        "where goods.id = " + str + ";";

                resultSet = statement.executeQuery(query);
                resultSet.next();
                SportEquipment sE = new SportEquipment();
                sE.setCategory(resultSet.getString("c_title"));
                sE.setTitle(resultSet.getString("G_title"));
                sE.setPrice(Double.parseDouble(resultSet.getString("$price")));
                records.add(sE);
                resultSet.close();
            }

            if (records.isEmpty()) {
                throw new DaoException("you have never rented goods");
            }

            return records;
        } catch (SQLException exc) {
            throw new DaoException("the query is not performed", exc);
        } finally {
            try {
                if (resultSet != null) {resultSet.close();}
                if (statement != null) {statement.close();}
                if (connection != null) {connection.close();}
            } catch(SQLException exc) {
                throw new DaoException("closing error", exc);
            }
        }
    }

    @Override
    public void rent(User user, SportEquipment sportEquipment) throws DaoException {
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
            throw new DaoException("there is not connection", exc);
        }

        try {
            statement = connection.createStatement();

            String query = "select id from users where mobile_phone = '" +
                    user.getPhone() + "'";
            resultSet = statement.executeQuery(query);
            resultSet.next();
            String userId = resultSet.getString("id");
            resultSet.close();

            resultSet = statement.executeQuery("select * from rents " +
                    "where user_id = " + userId);
            int quantityOfGoods = 0;
            while (resultSet.next()) {
                quantityOfGoods++;
            }
            if (quantityOfGoods >= 3) {
                throw new DaoException("it is forbidden to take more then 3 items");
            }

            query = "UPDATE users SET has_rent = 'yes' where mobile_phone = '" +
                    user.getPhone() + "'";
            statement.execute(query);

            query = "UPDATE goods SET status = 'rented' where title = '" +
                    sportEquipment.getTitle() + "'";
            statement.execute(query);

            query = "select id from goods where title = '" +
                    sportEquipment.getTitle() + "'";
            resultSet = statement.executeQuery(query);
            resultSet.next();
            String goodId = resultSet.getString("id");
            resultSet.close();

            resultSet = statement.executeQuery("select * from rents");
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            i = i +1;

            query = "insert into rents values (" + i + ", " + userId  +
                    ", " + goodId + ")";
            statement.execute(query);
        } catch (SQLException exc) {
            throw new DaoException("the query is not performed", exc);
        } finally {
            try {
                if (resultSet != null) {resultSet.close();}
                if (statement != null) {statement.close();}
                if (connection != null) {connection.close();}
            } catch(SQLException exc) {
                throw new DaoException("closing error", exc);
            }
        }
    }
}