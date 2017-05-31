package by.tc.onlinepharmacy.dao.impl;


import by.tc.onlinepharmacy.bean.Drug;
import by.tc.onlinepharmacy.bean.OrderDescription;
import by.tc.onlinepharmacy.dao.PharmacistDAO;
import by.tc.onlinepharmacy.dao.connectionpool.ConnectionPool;
import by.tc.onlinepharmacy.dao.exception.DAOException;
import by.tc.onlinepharmacy.dao.query.CommonQueryStore;
import by.tc.onlinepharmacy.dao.query.DrugQueryStore;
import by.tc.onlinepharmacy.dao.query.OrderQueryStore;
import by.tc.onlinepharmacy.dao.util.DaoErrorMessage;
import by.tc.onlinepharmacy.dao.util.OrderDescriptionListMaker;
import by.tc.onlinepharmacy.dao.util.TableColumnName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the interface {@link by.tc.onlinepharmacy.dao.PharmacistDAO PharmacistDAO}.
 */
public class PharmacistDAOImpl implements PharmacistDAO {

    private static final Logger LOGGER = LogManager.getLogger(PharmacistDAOImpl.class.getName());

    /**
     * Increases the quantity of the corresponding drug.
     *
     * @param id drug id.
     * @param quantity drug quantity.
     * @throws DAOException
     */
    @Override
    public void addDrugQuantity(int id, int quantity) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_PLUS_DRUG_QUANTITY);
            ps.setInt(1, quantity);
            ps.setInt(2, id);
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

    /**
     * Add a new drug in the table "drug". If such a drug is already in the table,
     * then its number is increasing.
     *
     * @param drug DTO {@link by.tc.onlinepharmacy.bean.Drug Drug}.
     * @throws DAOException
     */
    @Override
    public void addNewDrug(Drug drug) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_UNIQUE_TEST);
            ps.setString(1, drug.getName());
            ps.setString(2, drug.getForm());
            ps.setString(3, drug.getDrugAmount());
            ps.setString(4, drug.getActiveSubstances());
            ps.setString(5, drug.getCountry());
            ps.setString(6, drug.getDispensing());
            ps.setDouble(7, drug.getPrice());
            resultSet = ps.executeQuery();

            if (resultSet.next()) {

                int id = resultSet.getInt(TableColumnName.ID);

                ps = connection.prepareStatement(DrugQueryStore.UPDATE_PLUS_DRUG_QUANTITY);
                ps.setInt(1, drug.getQuantity());
                ps.setInt(2, id);
                ps.executeUpdate();
            } else {
                ps = connection.prepareStatement(DrugQueryStore.INSERT_DRUG);
                ps.setString(1, drug.getName());
                ps.setString(2, drug.getGroup());
                ps.setString(3, drug.getForm());
                ps.setString(4, drug.getDrugAmount());
                ps.setString(5, drug.getActiveSubstances());
                ps.setString(6, drug.getCountry());
                ps.setString(7, drug.getDispensing());
                ps.setDouble(8, drug.getPrice());
                ps.setInt(9, drug.getQuantity());
                ps.executeUpdate();
            }
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

    /**
     * Set value "no" in the field "is_active" of the table "drug".
     *
     * @param id drug id
     * @throws DAOException
     */
    @Override
    public void removeDrug(int id) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_DRUG_ACTIVE);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DAOException();
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

    /**
     * Returns the list of order descriptions(new orders).
     *
     * @return list of DTOs {@link by.tc.onlinepharmacy.bean.OrderDescription OrderDescription}.
     * @throws DAOException
     */
    @Override
    public List<OrderDescription> takePharmacistOrderList() throws DAOException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(CommonQueryStore.SELECT_NEW_ORDERS);

            return OrderDescriptionListMaker.makeList(resultSet);

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
     * Updates corresponding order status on "sent".
     *
     * @param orderId
     * @param pharmacistId
     * @throws DAOException
     */
    @Override
    public void send(int orderId, int pharmacistId) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(OrderQueryStore.UPDATE_SENT_ORDER_STATUS);
            ps.setInt(1, pharmacistId);
            ps.setInt(2, orderId);
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

    /**
     * Returns the list of order descriptions(for sales report).
     *
     * @return list of DTOs {@link by.tc.onlinepharmacy.bean.OrderDescription OrderDescription}.
     * @throws DAOException
     */
    @Override
    public List<OrderDescription> takeOrders() throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(CommonQueryStore.SELECT_ORDER_DESCRIPTION_TO_SALES_REPORT);
            resultSet = ps.executeQuery();

            List<OrderDescription> list = new ArrayList<>();

            while (resultSet.next()) {
                OrderDescription orderDescription = new OrderDescription();
                orderDescription.setResponseDate(resultSet.getTimestamp(TableColumnName.RESPONSE_DATE));
                orderDescription.setDrugName(resultSet.getString(TableColumnName.NAME));
                orderDescription.setPharmacologicalGroup(resultSet.getString(TableColumnName.GROUP));
                orderDescription.setDrugAmount(resultSet.getString(TableColumnName.DRUG_AMOUNT));
                orderDescription.setActiveSubstances(resultSet.getString(TableColumnName.ACTIVE_SUBSTANCES));
                orderDescription.setProductingCountry(resultSet.getString(TableColumnName.COUNTRY));
                orderDescription.setQuantity(resultSet.getInt(TableColumnName.QUANTITY));

                list.add(orderDescription);
            }

            return list;

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