package by.tc.online_pharmacy.dao.impl;


import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.dao.PharmacistDao;
import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.impl.util.OrderDescriptionListBuilder;
import by.tc.online_pharmacy.dao.query.DrugQueryStore;

import java.sql.*;
import java.util.List;

public class PharmacistDaoImpl implements PharmacistDao {

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

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();//???????????
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public void addNewDrug(Drug drug) throws DaoException {

        final String ID = "id";
        final String YES = "yes";

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
                int id = resultSet.getInt(ID);

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
                ps.setString(10, YES);
                ps.executeUpdate();
            }
        } catch (SQLException | InterruptedException exc){
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
    public List<OrderDescription> takePharmacistOrderList() throws DaoException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DrugQueryStore.SELECT_NEW_ORDERS);

            return OrderDescriptionListBuilder.getOrderDescriptionList(resultSet);
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
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
