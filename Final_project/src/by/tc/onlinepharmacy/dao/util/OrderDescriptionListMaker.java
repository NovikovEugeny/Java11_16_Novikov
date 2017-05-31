package by.tc.onlinepharmacy.dao.util;


import by.tc.onlinepharmacy.bean.OrderDescription;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * It is utility class to form DTO list of {@link by.tc.onlinepharmacy.bean.OrderDescription
 * OrderDescription} from resultSet.
 */
public final class OrderDescriptionListMaker {

    public static List<OrderDescription> makeList(ResultSet resultSet) throws SQLException {

        List<OrderDescription> orderList = new ArrayList<>();

        while (resultSet.next()) {
            OrderDescription orderDescription = new OrderDescription();
            orderDescription.setOrderId(resultSet.getInt(TableColumnName.ORDER_ID));
            orderDescription.setRequestDate(resultSet.getTimestamp(TableColumnName.REQUEST_DATE));
            orderDescription.setClientMobile(resultSet.getString(TableColumnName.CLIENT_MOBILE));
            orderDescription.setDrugName(resultSet.getString(TableColumnName.NAME));
            orderDescription.setPharmacologicalGroup(resultSet.getString(TableColumnName.PHARM_GROUP));
            orderDescription.setDrugForm(resultSet.getString(TableColumnName.FORM));
            orderDescription.setDrugAmount(resultSet.getString(TableColumnName.DRUG_AMOUNT));
            orderDescription.setActiveSubstances(resultSet.getString(TableColumnName.ACTIVE_SUBSTANCES));
            orderDescription.setProductingCountry(resultSet.getString(TableColumnName.COUNTRY));
            orderDescription.setQuantity(resultSet.getInt(TableColumnName.QUANTITY));
            orderDescription.setCost(resultSet.getDouble(TableColumnName.COST));

            orderList.add(orderDescription);
        }
        return orderList;
    }

}