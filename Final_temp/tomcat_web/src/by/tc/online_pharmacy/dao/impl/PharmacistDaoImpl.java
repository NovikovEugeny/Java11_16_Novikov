package by.tc.online_pharmacy.dao.impl;


import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.dao.PharmacistDao;
import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.util.DaoErrorMessage;
import by.tc.online_pharmacy.dao.util.OrderDescriptionListMaker;
import by.tc.online_pharmacy.dao.query.DrugQueryStore;
import by.tc.online_pharmacy.dao.util.TableColumnName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PharmacistDaoImpl implements PharmacistDao {

    private static final Logger logger = LogManager.getLogger(PharmacistDaoImpl.class.getName());

    @Override
    public void addDrugQuantity(int id, int quantity) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_PLUS_DRUG_QUANTITY);
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc){
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public void addNewDrug(Drug drug) throws DaoException {

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
                ps.setString(10, TableColumnName.DRUG_STATUS_YES);
                ps.executeUpdate();
            }
        } catch (SQLException | InterruptedException exc){
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }


    @Override
    public void removeDrug(int id) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_DRUG_ACTIVE);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DaoException();
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public List<OrderDescription> takePharmacistOrderList() throws DaoException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DrugQueryStore.SELECT_NEW_ORDERS);

            return OrderDescriptionListMaker.makeList(resultSet);
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
    public void send(int orderId, int pharmacistId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_SENT_ORDER_STATUS);
            ps.setInt(1, pharmacistId);
            ps.setInt(2, orderId);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public List<OrderDescription> takeOrders() throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_ORDER_DESCRIPTION_TO_SALES_REPORT);
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
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) { ps.close(); }
            } catch (SQLException exc) {
                logger.log(Level.WARN, DaoErrorMessage.CLOSING_RESOURCES_ERROR, exc);
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }
}