package by.tc.onlinepharmacy.dao.util;


import by.tc.onlinepharmacy.bean.Drug;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * It is utility class to form DTO list of {@link by.tc.onlinepharmacy.bean.Drug Drug} from resultSet.
 */
public final class DrugListMaker {

    public static List<Drug> makeList(ResultSet resultSet) throws SQLException {

        List<Drug> drugList = new ArrayList<>();

        while (resultSet.next()) {
            Drug drug = new Drug();
            drug.setId(resultSet.getInt(TableColumnName.ID));
            drug.setName(resultSet.getString(TableColumnName.NAME));
            drug.setGroup(resultSet.getString(TableColumnName.GROUP));
            drug.setForm(resultSet.getString(TableColumnName.FORM));
            drug.setDrugAmount(resultSet.getString(TableColumnName.DRUG_AMOUNT));
            drug.setActiveSubstances(resultSet.getString(TableColumnName.ACTIVE_SUBSTANCES));
            drug.setCountry(resultSet.getString(TableColumnName.COUNTRY));
            drug.setDispensing(resultSet.getString(TableColumnName.DISPENSING));
            drug.setPrice(resultSet.getDouble(TableColumnName.PRICE));
            drug.setQuantity(resultSet.getInt(TableColumnName.QUANTITY));

            drugList.add(drug);
        }

        return drugList;
    }
}