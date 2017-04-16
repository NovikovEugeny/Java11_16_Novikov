package by.tc.online_pharmacy.dao.impl.util;


import by.tc.online_pharmacy.bean.Drug;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugListBuilder {

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String PHARM_GROUP = "pharm_group";
    private final static String FORM = "form";
    private final static String DRUG_AMOUNT = "drug_amount";
    private final static String ACTIVE_SUBSTANCES = "active_substances";
    private final static String COUNTRY = "country";
    private final static String DISPENSING = "dispensing";
    private final static String PRICE = "price";
    private final static String QUANTITY = "quantity";

    public static List<Drug> getDrugList(ResultSet resultSet) throws SQLException {

        List<Drug> drugList = new ArrayList<>();

        while (resultSet.next()) {
            Drug drug = new Drug();
            drug.setId(resultSet.getInt(ID));
            drug.setName(resultSet.getString(NAME));
            drug.setGroup(resultSet.getString(PHARM_GROUP));
            drug.setForm(resultSet.getString(FORM));
            drug.setDrugAmount(resultSet.getString(DRUG_AMOUNT));
            drug.setActiveSubstances(resultSet.getString(ACTIVE_SUBSTANCES));
            drug.setCountry(resultSet.getString(COUNTRY));
            drug.setDispensing(resultSet.getString(DISPENSING));
            drug.setPrice(resultSet.getDouble(PRICE));
            drug.setQuantity(resultSet.getInt(QUANTITY));

            drugList.add(drug);
        }

        return drugList;
    }
}
