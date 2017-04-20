package by.tc.online_pharmacy.dao.impl;

import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.dao.DoctorDao;
import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.query.DrugQueryStore;
import by.tc.online_pharmacy.dao.util.DaoErrorMessage;
import by.tc.online_pharmacy.dao.util.TableColumnName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DoctorDaoImpl implements DoctorDao {

    private static final Logger logger = LogManager.getLogger(DoctorDaoImpl.class.getName());

    @Override
    public List<RERDescription> takeRecipeExtensionRequestList() throws DaoException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(DrugQueryStore.SELECT_NEW_RECIPE_EXTENSION_REQUEST);

            List<RERDescription> recipeExtensionRequestList = new ArrayList<>();

            while (resultSet.next()) {
                RERDescription rerDescription = new RERDescription();
                rerDescription.setId(resultSet.getInt(TableColumnName.ID));
                rerDescription.setRecipeCode(resultSet.getString(TableColumnName.RECIPE_CODE));
                rerDescription.setRequestDate(resultSet.getTimestamp(TableColumnName.REQUEST_DATE));

                recipeExtensionRequestList.add(rerDescription);
            }

            return recipeExtensionRequestList;
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (statement != null) { statement.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
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
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.log(Level.ERROR, DaoErrorMessage.ROLLBACK_ERROR, exc);
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
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

}