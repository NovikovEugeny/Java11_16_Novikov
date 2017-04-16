package by.tc.online_pharmacy.dao.impl;


import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.dao.ClientDao;
import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.impl.util.DrugListBuilder;
import by.tc.online_pharmacy.dao.impl.util.OrderDescriptionListBuilder;
import by.tc.online_pharmacy.dao.query.DrugQueryStore;
import by.tc.online_pharmacy.dao.query.UserQueryStore;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientDaoImpl implements ClientDao {


    @Override
    public int signUp(User user) throws DaoException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(UserQueryStore.SIGN_UP_SELECT);

            int n = 0;
            while (resultSet.next()) {
                n++;
            }
            n = n + 1;

            ps = connection.prepareStatement(UserQueryStore.SIGN_UP_INSERT);
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
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();////????
            }

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();//????
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public boolean isUnique(String mobile) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            ps = connection.prepareStatement(UserQueryStore.IS_UNIQUE_SELECT);
            ps.setString(1, mobile);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return false;
            }

            return true;
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

    @Override
    public void addAccount(int clientId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(UserQueryStore.INSERT_NEW_ACCOUNT);
            ps.setInt(1, clientId);
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

    @Override
    public double takeClientBalance(int clientId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(UserQueryStore.SELECT_BALANCE);
            ps.setInt(1, clientId);
            resultSet = ps.executeQuery();

            resultSet.next();
            return resultSet.getDouble(1);
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

    @Override
    public List<Drug> takeDrugGroupToOrder(String group) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_ACTIVE_DRUGS_BY_GROUP_TO_ORDER);
            ps.setString(1, group);
            resultSet = ps.executeQuery();

            return DrugListBuilder.getDrugList(resultSet);
        } catch (SQLException | InterruptedException exc) {
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();///????
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public int addOrder(Order order) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(DrugQueryStore.INSERT_ORDER);
            ps.setInt(1, order.getClientId());
            ps.setInt(2, order.getDrugId());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, order.getCost());
            ps.setString(5, order.getStatus());
            ps.executeUpdate();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(DrugQueryStore.SELECT_LAST_INSERT_ID);
            resultSet.next();
            int id = resultSet.getInt(1);

            ps = connection.prepareStatement(UserQueryStore.UPDATE_MINUS_BALANCE);
            ps.setDouble(1, order.getCost());
            ps.setInt(2, order.getClientId());
            ps.executeUpdate();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_MINUS_DRUG_QUANTITY);
            ps.setInt(1, order.getQuantity());
            ps.setInt(2, order.getDrugId());
            ps.executeUpdate();

            connection.commit();

            return id;
        } catch (SQLException | InterruptedException exc) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    @Override
    public List<OrderDescription> takeClientOrderList(int id) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_NEW_ORDERS_BY_CLIENT_ID);
            ps.setInt(1, id);
            resultSet = ps.executeQuery();

            return OrderDescriptionListBuilder.getOrderDescriptionList(resultSet);
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

    @Override
    public int takeDrugQuantity(int drugId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_DRUG_QUANTITY);
            ps.setInt(1, drugId);
            resultSet = ps.executeQuery();

            resultSet.next();
            return resultSet.getInt(1);
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

    @Override
    public RecipeDescription takeRecipeDescription(String recipeCode) throws DaoException {

        final String DRUG_ID = "drug_id";
        final String DRUG_NAME = "drug_name";
        final String PHARM_GROUP = "pharm_group";
        final String FORM = "form";
        final String DRUG_AMOUNT = "drug_amount";
        final String AS = "active_substances";
        final String COUNTRY = "country";
        final String PRICE = "price";
        final String QUANTITY = "quantity";
        final String COST = "cost";
        final String END_DATE = "end_date";
        final String STATUS = "status";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        RecipeDescription recipeDescription = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_RECIPE_DESCRIPTION_BY_CODE);
            ps.setString(1, recipeCode);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                recipeDescription = new RecipeDescription();
                recipeDescription.setRecipeCode(recipeCode);
                recipeDescription.setDrugId(resultSet.getInt(DRUG_ID));
                recipeDescription.setDrugName(resultSet.getString(DRUG_NAME));
                recipeDescription.setDrugGroup(resultSet.getString(PHARM_GROUP));
                recipeDescription.setDrugForm(resultSet.getString(FORM));
                recipeDescription.setDrugAmount(resultSet.getString(DRUG_AMOUNT));
                recipeDescription.setActiveSubstances(resultSet.getString(AS));
                recipeDescription.setCountry(resultSet.getString(COUNTRY));
                recipeDescription.setPrice(resultSet.getDouble(PRICE));
                recipeDescription.setQuantity(resultSet.getInt(QUANTITY));
                recipeDescription.setCost(resultSet.getDouble(COST));
                recipeDescription.setEndDate(resultSet.getTimestamp(END_DATE));
                recipeDescription.setStatus(resultSet.getString(STATUS));
            }

        } catch (SQLException | InterruptedException exc) {
            exc.printStackTrace();
            throw new DaoException(exc);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();///????
            }
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return recipeDescription;
    }

    @Override
    public Date takeRecipeEndDate(String recipeCode) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_END_RECIPE_DATE);
            ps.setString(1, recipeCode);
            resultSet = ps.executeQuery();

            Date endDate = null;

            if (resultSet.next()) {
                endDate = resultSet.getTimestamp("end_date");
            }

            return endDate;
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

    @Override
    public void closeRecipe(String recipeCode) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_RECIPE_STATUS_CLOSED);
            ps.setString(1, recipeCode);
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

    @Override
    public void linkOrderAndRecipe(int orderId, String recipeCode) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.INSERT_ORDER_RECIPE);
            ps.setInt(1, orderId);
            ps.setString(2, recipeCode);
            ps.executeUpdate();

        } catch (SQLException | InterruptedException exc) {
            exc.printStackTrace();
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
    public List<OrderDescription> takeSendingMessageList(int clientId) throws DaoException {

        final String ORDER_ID = "id";
        final String DRUG_NAME = "name";
        final String DRUG_AMOUNT = "drug_amount";
        final String QUANTITY = "quantity";
        final String COUNTRY = "country";
        final String RESPONSE_DATE = "response_date";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_SENDING_ORDERS_BY_CLIENT_ID);
            ps.setInt(1, clientId);
            resultSet = ps.executeQuery();

            List<OrderDescription> orderDescriptionList = new ArrayList<>();

            while (resultSet.next()) {
                OrderDescription orderDescription = new OrderDescription();
                orderDescription.setOrderId(resultSet.getInt(ORDER_ID));
                orderDescription.setDrugName(resultSet.getString(DRUG_NAME));
                orderDescription.setDrugAmount(resultSet.getString(DRUG_AMOUNT));
                orderDescription.setQuantity(resultSet.getInt(QUANTITY));
                orderDescription.setProductingCountry(resultSet.getString(COUNTRY));
                orderDescription.setResponseDate(resultSet.getTimestamp(RESPONSE_DATE));

                orderDescriptionList.add(orderDescription);
            }

            return orderDescriptionList;
        } catch (SQLException | InterruptedException exc) {
            exc.printStackTrace();
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
    public List<RERDescription> takeDoctorResponseMessageList(int clientId) throws DaoException {

        final String ID = "id";
        final String RECIPE_CODE = "recipe_code";
        final String RESPONSE_DATE = "response_date";
        final String STATUS = "status";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_DOCTOR_RESPONSE_BY_CLIENT_ID);
            ps.setInt(1, clientId);
            resultSet = ps.executeQuery();

            List<RERDescription> rerDescriptionList = new ArrayList<>();

            while (resultSet.next()) {
                RERDescription rerDescription = new RERDescription();
                rerDescription.setId(resultSet.getInt(ID));
                rerDescription.setRecipeCode(resultSet.getString(RECIPE_CODE));
                rerDescription.setResponseDate(resultSet.getTimestamp(RESPONSE_DATE));
                rerDescription.setStatus(resultSet.getString(STATUS));

                rerDescriptionList.add(rerDescription);
            }

            return rerDescriptionList;
        } catch(SQLException | InterruptedException exc) {
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
    public void addRecipeExtensionRequest(String recipeCode, int clientId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.INSERT_RECIPE_EXTENSION_REQUEST);
            ps.setString(1, recipeCode);
            ps.setInt(2, clientId);
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

    @Override
    public void reportAboutDelivery(int orderId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.UPDATE_RECEIVE_ORDER_STATUS);
            ps.setInt(1, orderId);
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

    @Override
    public void hideMessage(int requestId) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(
                    DrugQueryStore.UPDATE_RECIPE_EXTENSION_REQUEST_IS_READ_YES);
            ps.setInt(1, requestId);
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


    @Override
    public List<OrderDescription> takeShoppingList(int clientId) throws DaoException {

        final String REQUEST_DATE = "request_date";
        final String DRUG_NAME = "name";
        final String GROUP = "pharm_group";
        final String DRUG_AMOUNT = "drug_amount";
        final String COUNTRY = "country";
        final String QUANTITY = "quantity";
        final String COST = "cost";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        List<OrderDescription> shoppingList = new ArrayList<>();

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            ps = connection.prepareStatement(DrugQueryStore.SELECT_ORDER_DESCRIPTION_BY_CLIENT_ID);
            ps.setInt(1, clientId);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                OrderDescription orderDescription = new OrderDescription();
                orderDescription.setRequestDate(resultSet.getTimestamp(REQUEST_DATE));
                orderDescription.setDrugName(resultSet.getString(DRUG_NAME));
                orderDescription.setPharmacologicalGroup(resultSet.getString(GROUP));
                orderDescription.setDrugAmount(resultSet.getString(DRUG_AMOUNT));
                orderDescription.setProductingCountry(resultSet.getString(COUNTRY));
                orderDescription.setQuantity(resultSet.getInt(QUANTITY));
                orderDescription.setCost(resultSet.getDouble(COST));

                shoppingList.add(orderDescription);
            }

            return shoppingList;
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
