package by.tc.onlinepharmacy.dao.impl;

import by.tc.onlinepharmacy.bean.RERDescription;
import by.tc.onlinepharmacy.dao.DoctorDAO;
import by.tc.onlinepharmacy.dao.connectionpool.ConnectionPool;
import by.tc.onlinepharmacy.dao.exception.DAOException;
import by.tc.onlinepharmacy.dao.query.DrugQueryStore;
import by.tc.onlinepharmacy.dao.query.RERQueryStore;
import by.tc.onlinepharmacy.dao.query.RecipeQueryStore;
import by.tc.onlinepharmacy.dao.util.DaoErrorMessage;
import by.tc.onlinepharmacy.dao.util.TableColumnName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the interface {@link by.tc.onlinepharmacy.dao.DoctorDAO DoctorDAO}.
 */
public class DoctorDAOImpl implements DoctorDAO {

    private static final Logger LOGGER = LogManager.getLogger(DoctorDAOImpl.class.getName());

    /**
     * Returns recipe extension request descriptions.
     *
     * @return list of DTOs {@link by.tc.onlinepharmacy.bean.RERDescription RERDescription}.
     * @throws DAOException
     */
    @Override
    public List<RERDescription> takeRecipeExtensionRequestList() throws DAOException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(RERQueryStore.SELECT_NEW_RECIPE_EXTENSION_REQUEST);

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
            throw new DAOException(exc);
        } finally {
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
     * Updates recipe extension request on "approved", updates recipe status on
     * "open" and extend it's validity on 6 month.
     *
     * @param rerDescription DTO {@link by.tc.onlinepharmacy.bean.RecipeDescription RecipeDescription},
     *                       contains: doctor id, recipe extension request id and recipe code.
     * @throws DAOException
     */
    @Override
    public void approve(RERDescription rerDescription) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(RERQueryStore.UPDATE_RECIPE_EXTENSION_REQUEST_STATUS_APPROVED);
            ps.setInt(1, rerDescription.getDoctorId());
            ps.setInt(2, rerDescription.getId());
            ps.executeUpdate();

            ps = connection.prepareStatement(RecipeQueryStore.UPDATE_RECIPE_STATUS_OPEN_AND_EXTEND_DATE);
            ps.setString(1, rerDescription.getRecipeCode());
            ps.executeUpdate();

            connection.commit();

        } catch (SQLException | InterruptedException exc) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, DaoErrorMessage.ROLLBACK_ERROR, exc);
            }
            throw new DAOException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException exc) {
                LOGGER.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException exc) {
                LOGGER.log(Level.ERROR, DaoErrorMessage.SET_AUTO_COMMIT_TRUE_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    /**
     * Updates recipe extension request on "denied".
     *
     * @param rerDescription DTO {@link by.tc.onlinepharmacy.bean.RecipeDescription RecipeDescription},
     *                       contains: doctor id and recipe extension request id.
     * @throws DAOException
     */
    @Override
    public void deny(RERDescription rerDescription) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(RERQueryStore.UPDATE_RECIPE_EXTENSION_REQUEST_STATUS_DENIED);
            ps.setInt(1, rerDescription.getDoctorId());
            ps.setInt(2, rerDescription.getId());
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