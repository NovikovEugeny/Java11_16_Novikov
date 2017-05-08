package by.tc.onlinepharmacy.dao.query;

/**
 * The class contains constants that represent queries to the database
 * to perform operations with data from table drug.
 */
public final class DrugQueryStore {

    private DrugQueryStore() {
    }

    public final static String SELECT_DRUG_QUANTITY = "SELECT quantity FROM drug WHERE id = ?";

    public final static String SELECT_ACTIVE_DRUGS_BY_GROUP =
            "SELECT * FROM drug WHERE pharm_group = ? AND is_active = 'yes'";

    public final static String SELECT_ACTIVE_DRUGS_BY_GROUP_TO_ORDER =
            "SELECT * FROM drug WHERE pharm_group = ? AND is_active = 'yes' " +
                    "AND dispensing = 'without prescription' AND quantity > 0";

    public final static String SELECT_ACTIVE_DRUGS_BY_NAME =
            "SELECT * FROM drug WHERE UPPER(name) like ? AND is_active = 'yes'";

    public final static String SELECT_UNIQUE_TEST =
            "SELECT id FROM drug WHERE name = ? AND form = ? AND drug_amount = ? AND active_substances = ? " +
                    "AND country = ? AND dispensing = ? AND price = ?";

    public final static String UPDATE_PLUS_DRUG_QUANTITY = "UPDATE drug SET quantity = quantity + ? WHERE id = ?";

    public final static String UPDATE_RETURN_DRUG_QUANTITY =
            "UPDATE drug SET quantity = quantity + (SELECT quantity FROM orders WHERE id = ?) WHERE " +
                    "id = (SELECT drug_id FROM orders WHERE id = ?)";

    public final static String UPDATE_MINUS_DRUG_QUANTITY = "UPDATE drug SET quantity = quantity - ? WHERE id = ?";

    public final static String UPDATE_DRUG_ACTIVE = "UPDATE drug SET is_active = 'no' WHERE id = ?";

    public final static String INSERT_DRUG =
            "INSERT INTO drug(name, pharm_group, form, drug_amount, active_substances, country, dispensing, price, " +
                    "quantity, is_active) VALUES (?,?,?,?,?,?,?,?,?,?)";

    public final static String SELECT_DRUG_PRICE = "SELECT price FROM drug WHERE id = ?";

}