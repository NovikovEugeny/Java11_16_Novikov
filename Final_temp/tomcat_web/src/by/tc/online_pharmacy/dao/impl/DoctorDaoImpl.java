package by.tc.online_pharmacy.dao.impl;

import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.dao.DoctorDao;
import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.query.DrugQueryStore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DoctorDaoImpl implements DoctorDao {

    @Override
    public List<RERDescription> takeRecipeExtensionRequestList() throws DaoException {

        final String ID = "id";
        final String RECIPE_CODE = "recipe_code";
        final String REQUEST_DATE = "request_date";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<RERDescription> recipeExtensionRequestList = new ArrayList<>();

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(DrugQueryStore.SELECT_NEW_RECIPE_EXTENSION_REQUEST);

            while (resultSet.next()) {
                RERDescription rerDescription = new RERDescription();
                rerDescription.setId(resultSet.getInt(ID));
                rerDescription.setRecipeCode(resultSet.getString(RECIPE_CODE));
                rerDescription.setRequestDate(resultSet.getTimestamp(REQUEST_DATE));

                recipeExtensionRequestList.add(rerDescription);
            }

            return recipeExtensionRequestList;
        } catch (SQLException | InterruptedException exc) {
            exc.printStackTrace();
            throw new DaoException(exc);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public void approve(RERDescription rerDescription) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(DrugQueryStore.
                    UPDATE_RECIPE_EXTENSION_REQUEST_STATUS_APPROVED);
            ps.setInt(1, rerDescription.getDoctorId());
            ps.setInt(2, rerDescription.getId());
            ps.executeUpdate();

            ps = connection.prepareStatement(DrugQueryStore.
                    UPDATE_RECIPE_STATUS_OPEN_AND_EXTEND_DATE);
            ps.setString(1, rerDescription.getRecipeCode());
            ps.executeUpdate();

            connection.commit();

        } catch (SQLException | InterruptedException exc) {
            exc.printStackTrace();
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
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public void deny(RERDescription rerDescription) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.
                    UPDATE_RECIPE_EXTENSION_REQUEST_STATUS_DENIED);
            ps.setInt(1, rerDescription.getDoctorId());
            ps.setInt(2, rerDescription.getId());
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

}
