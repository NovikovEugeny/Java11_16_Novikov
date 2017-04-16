package by.tc.online_pharmacy.dao.impl.util;


import by.tc.online_pharmacy.bean.OrderDescription;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDescriptionListBuilder {

    private final static String ORDER_ID = "order_id";
    private final static String REQUEST_DATE = "request_date";
    private final static String CLIENT_MOBILE = "client_mobile";
    private final static String NAME = "name";
    private final static String PHARM_GROUP = "pharm_group";
    private final static String FORM = "form";
    private final static String DRUG_AMOUNT = "drug_amount";
    private final static String ACTIVE_SUBSTANCES = "active_substances";
    private final static String COUNTRY = "country";
    private final static String QUANTITY = "quantity";
    private final static String COST = "cost";

    public static List<OrderDescription> getOrderDescriptionList(ResultSet resultSet) throws SQLException {

        List<OrderDescription> orderList = new ArrayList<>();

        while (resultSet.next()) {
            OrderDescription orderDescription = new OrderDescription();

            orderDescription.setOrderId(resultSet.getInt(ORDER_ID));
            orderDescription.setRequestDate(resultSet.getTimestamp(REQUEST_DATE));
            orderDescription.setClientMobile(resultSet.getString(CLIENT_MOBILE));
            orderDescription.setDrugName(resultSet.getString(NAME));
            orderDescription.setPharmacologicalGroup(resultSet.getString(PHARM_GROUP));
            orderDescription.setDrugForm(resultSet.getString(FORM));
            orderDescription.setDrugAmount(resultSet.getString(DRUG_AMOUNT));
            orderDescription.setActiveSubstances(resultSet.getString(ACTIVE_SUBSTANCES));
            orderDescription.setProductingCountry(resultSet.getString(COUNTRY));
            orderDescription.setQuantity(resultSet.getInt(QUANTITY));
            orderDescription.setCost(resultSet.getDouble(COST));

            orderList.add(orderDescription);
        }

        return orderList;
    }

}
