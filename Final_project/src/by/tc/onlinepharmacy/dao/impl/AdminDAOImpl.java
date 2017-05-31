package by.tc.onlinepharmacy.dao.impl;

import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.dao.AdminDAO;
import by.tc.onlinepharmacy.dao.connectionpool.ConnectionPool;
import by.tc.onlinepharmacy.dao.exception.DAOException;
import by.tc.onlinepharmacy.dao.query.UserQueryStore;
import by.tc.onlinepharmacy.dao.util.DaoErrorMessage;
import by.tc.onlinepharmacy.dao.util.TableColumnName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of the interface {@link by.tc.onlinepharmacy.dao.ClientDAO AdminDAO}.
 */
public class AdminDAOImpl implements AdminDAO {

    private static final Logger LOGGER = LogManager.getLogger(AdminDAOImpl.class.getName());

    /**
     * Returns a list of employees.
     *
     * @return list of DTOs {@link by.tc.onlinepharmacy.bean.User User}.
     * @throws DAOException
     */
    @Override
    public List<User> takeEmployeeList() throws DAOException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(UserQueryStore.SELECT_EMPLOYEES);

            List<User> employees = new ArrayList<>();

            while (resultSet.next()) {
                User employee = new User();
                employee.setId(resultSet.getInt(TableColumnName.ID));
                employee.setPosition(resultSet.getString(TableColumnName.POSITION));
                employee.setSurname(resultSet.getString(TableColumnName.SURNAME));
                employee.setName(resultSet.getString(TableColumnName.NAME));
                employee.setPatronymic(resultSet.getString(TableColumnName.PATRONYMIC));
                employee.setMobilePhone(resultSet.getString(TableColumnName.MOBILE_PHONE));

                employees.add(employee);
            }

            return employees;

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException(exc);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
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
     * Set value "no" in the field "is_active" of the table "user".
     *
     * @param id
     * @throws DAOException
     */
    @Override
    public void removeEmployee(int id) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(UserQueryStore.UPDATE_USER_STATUS);
            ps.setInt(1, id);
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