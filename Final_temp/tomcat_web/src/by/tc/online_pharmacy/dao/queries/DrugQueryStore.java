package by.tc.online_pharmacy.dao.queries;


public class DrugQueryStore {

    private DrugQueryStore() {}

    public final static String SELECT_LAST_INSERT_ID = "SELECT last_insert_id()";

    //drug
    public final static String SELECT_DRUG_QUANTITY =
            "SELECT quantity FROM drug WHERE id = ?";

    public final static String SELECT_ACTIVE_DRUGS_BY_GROUP =
            "SELECT * FROM drug WHERE pharm_group = ? AND is_active = 'yes'";

    public final static String SELECT_ACTIVE_DRUGS_BY_NAME =
            "SELECT * FROM drug WHERE UPPER(name) = ? AND is_active = 'yes'";

    public final static String SELECT_UNIQUE_TEST =
            "SELECT id FROM drug WHERE name = ? AND form = ? AND " +
                    "drug_amount = ? AND active_substances = ? AND country = ? " +
                    "AND dispensing = ? AND price = ?";

    public final static String UPDATE_PLUS_DRUG_QUANTITY =
            "UPDATE drug SET quantity = quantity + ? WHERE id = ?";

    public final static String UPDATE_RETURN_DRUG_QUANTITY =
            "UPDATE drug SET quantity = quantity + (SELECT quantity FROM " +
                    "orders WHERE id = ?) WHERE id = (SELECT drug_id FROM " +
                    "orders WHERE id = ?)";

    public final static String UPDATE_MINUS_DRUG_QUANTITY =
            "UPDATE drug SET quantity = quantity - ? WHERE id = ?";

    public final static String UPDATE_DRUG_ACTIVE =
            "UPDATE drug SET is_active = 'no' WHERE id = ?";

    public final static String INSERT_DRUG =
            "INSERT INTO drug(name, pharm_group, form, drug_amount, " +
                    "active_substances, country, dispensing, price, quantity, " +
                    "is_active) VALUES (?,?,?,?,?,?,?,?,?,?)";


    //orders
    public final static String SELECT_NEW_ORDERS =
            "SELECT d.name, d.pharm_group, d.form, d.drug_amount, " +
                    "d.active_substances, d.country, o.quantity, " +
                    "o.request_date, o.cost, o.id AS `order_id`, " +
                    "(SELECT mobile_phone FROM user WHERE id = o.client_id) " +
                    "AS `client_mobile` FROM drug d INNER JOIN orders o ON " +
                    "d.id = o.drug_id WHERE o.status = 'new'";

    public final static String SELECT_NEW_ORDERS_BY_CLIENT_ID =
            "SELECT d.name, d.pharm_group, d.form, d.drug_amount, " +
                    "d.active_substances, d.country, o.quantity, " +
                    "o.cost, o.request_date, o.id AS `order_id`, " +
                    "(SELECT mobile_phone FROM user WHERE id = o.client_id) " +
                    "AS `client_mobile` FROM drug d INNER JOIN orders o ON " +
                    "d.id = o.drug_id WHERE o.status = 'new' AND o.client_id = ?";

    public final static String UPDATE_SENT_ORDER_STATUS =
            "UPDATE orders SET pharmacist_id = ?, status = 'sent', " +
                    "request_date = request_date, response_date = NOW() " +
                    "WHERE id = ?";

    public final static String UPDATE_CANCEL_ORDER_STATUS =
            "UPDATE orders SET status = 'canceled' WHERE id = ?";

    public final static String INSERT_ORDER =
            "INSERT INTO orders(client_id, drug_id, quantity, cost, " +
                    "request_date, status) VALUES (?,?,?,?,NOW(),?)";


    //account
    public final static String SELECT_BALANCE =
            "SELECT balance FROM account WHERE client_id = ?";

    public final static String UPDATE_PLUS_BALANCE =
            "UPDATE account SET balance = balance + " +
                    "(SELECT cost FROM orders WHERE id = ?) WHERE client_id = " +
                    "(SELECT client_id FROM orders WHERE id = ?)";

    public final static String UPDATE_MINUS_BALANCE =
            "UPDATE account SET balance = balance - ? WHERE client_id = ?";


    //recipe
    public final static String SELECT_END_RECIPE_DATE =
            "SELECT end_date FROM recipe WHERE code = ? AND drug_id = ? AND " +
                    "quantity = ?";

    public final static String UPDATE_RECIPE_STATUS_CLOSED =
            "UPDATE recipe SET status = 'closed' WHERE code = ?";

    public final static String UPDATE_RECIPE_STATUS_OPEN =
            "UPDATE recipe SET status = 'open' WHERE code = ?";

    public final static String UPDATE_RECIPE_STATUS_OPEN_AND_EXTEND_DATE =
            "UPDATE recipe SET status = 'open', end_date = end_date + " +
                    "INTERVAL 6 MONTH WHERE code = ?";


    //order_recipe
    public final static String INSERT_ORDER_RECIPE =
            "INSERT INTO order_recipe(order_id, recipe_code) VALUES (?, ?)";

    public final static String SELECT_RECIPE_CODE_BY_ORDER_ID =
            "SELECT recipe_code FROM order_recipe WHERE order_id = ?";

    //recipe_extension_request
    public final static String INSERT_RECIPE_EXTENSION_REQUEST =
            "INSERT INTO recipe_extension_request(recipe_code, status) VALUES" +
                    " (?, 'new')";

    public final static String SELECT_NEW_RECIPE_EXTENSION_REQUEST =
            "SELECT * FROM recipe_extension_request WHERE status = 'new'";

    public final static String UPDATE_RECIPE_EXTENSION_REQUEST_STATUS_APPROVED =
            "UPDATE recipe_extension_request SET status = 'approved' WHERE id = ?";

    public final static String UPDATE_RECIPE_EXTENSION_REQUEST_STATUS_DENIED =
            "UPDATE recipe_extension_request SET status = 'denied' WHERE id = ?";
}
