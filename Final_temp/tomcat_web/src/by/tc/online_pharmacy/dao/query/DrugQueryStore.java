package by.tc.online_pharmacy.dao.query;


public final class DrugQueryStore {

    private DrugQueryStore() {
    }

    public final static String SELECT_LAST_INSERT_ID = "SELECT last_insert_id()";

    //drug
    public final static String SELECT_DRUG_QUANTITY = "SELECT quantity FROM drug WHERE id = ?";

    public final static String SELECT_ACTIVE_DRUGS_BY_GROUP =
            "SELECT * FROM drug WHERE pharm_group = ? AND is_active = 'yes'";

    public final static String SELECT_ACTIVE_DRUGS_BY_GROUP_TO_ORDER =
            "SELECT * FROM drug WHERE pharm_group = ? AND is_active = 'yes' " +
                    "AND dispensing = 'without prescription' AND quantity > 0";

    public final static String SELECT_ACTIVE_DRUGS_BY_NAME =
            "SELECT * FROM drug WHERE UPPER(name) = ? AND is_active = 'yes'";

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


    //orders
    public final static String SELECT_NEW_ORDERS =
            "SELECT d.name, d.pharm_group, d.form, d.drug_amount, d.active_substances, d.country, o.quantity, " +
                    "o.request_date, o.cost, o.id AS `order_id`, (SELECT mobile_phone FROM user WHERE " +
                    "id = o.client_id) AS `client_mobile` FROM drug d INNER JOIN orders o ON " +
                    "d.id = o.drug_id WHERE o.status = 'new'";

    public final static String SELECT_NEW_ORDERS_BY_CLIENT_ID =
            "SELECT d.name, d.pharm_group, d.form, d.drug_amount, d.active_substances, d.country, o.quantity, " +
                    "o.cost, o.request_date, o.id AS `order_id`, (SELECT mobile_phone FROM user WHERE " +
                    "id = o.client_id) AS `client_mobile` FROM drug d INNER JOIN orders o ON " +
                    "d.id = o.drug_id WHERE o.status = 'new' AND o.client_id = ?";

    public final static String UPDATE_SENT_ORDER_STATUS =
            "UPDATE orders SET pharmacist_id = ?, status = 'sent', request_date = request_date, " +
                    "response_date = NOW() WHERE id = ?";

    public final static String UPDATE_CANCEL_ORDER_STATUS =
            "UPDATE orders SET status = 'canceled', request_date = request_date WHERE id = ?";

    public final static String UPDATE_RECEIVE_ORDER_STATUS =
            "UPDATE orders SET status = 'received', request_date = request_date WHERE id = ?";

    public final static String INSERT_ORDER =
            "INSERT INTO orders(client_id, drug_id, quantity, cost, request_date, status) VALUES (?,?,?,?,NOW(),?)";

    public final static String SELECT_SENDING_ORDERS_BY_CLIENT_ID =
            "SELECT o.id, d.name, d.drug_amount, o.quantity, d.country, o.response_date " +
                    "FROM orders o INNER JOIN drug d ON d.id = o.drug_id WHERE o.client_id = ? AND o.status = 'sent'";

    public final static String SELECT_ORDER_DESCRIPTION_BY_CLIENT_ID =
            "SELECT o.request_date, d.name, d.pharm_group, d.drug_amount, d.country, o.quantity, o.cost FROM " +
                    "orders o INNER JOIN drug d ON d.id = o.drug_id WHERE client_id = ? AND status != 'canceled'";

    public final static String SELECT_ORDER_DESCRIPTION_TO_SALES_REPORT =
            "SELECT o.response_date, d.name, d.pharm_group, d.drug_amount, d.active_substances, d.country, " +
                    "o.quantity FROM orders o INNER JOIN drug d ON o.drug_id = d.id " +
                    "WHERE status IN ('received', 'sent');";

    //recipe
    public final static String SELECT_END_RECIPE_DATE = "SELECT end_date FROM recipe WHERE code = ?";

    public final static String UPDATE_RECIPE_STATUS_CLOSED =
            "UPDATE recipe SET status = 'closed', start_date = start_date WHERE code = ?";

    public final static String UPDATE_RECIPE_STATUS_OPEN =
            "UPDATE recipe SET status = 'open', start_date = start_date WHERE code = ?";

    public final static String UPDATE_RECIPE_STATUS_OPEN_AND_EXTEND_DATE =
            "UPDATE recipe SET status = 'open', start_date = start_date, end_date = end_date + " +
                    "INTERVAL 6 MONTH WHERE code = ?";

    public final static String SELECT_RECIPE_DESCRIPTION_BY_CODE =
            "SELECT r.drug_id, d.name AS `drug_name`, d.pharm_group, d.form, d.drug_amount, d.active_substances, " +
                    "d.country, d.price, r.quantity, d.price*r.quantity AS `cost`, r.end_date, r.status FROM " +
                    "recipe r INNER JOIN drug d ON r.drug_id = d.id WHERE r.code = ?";

    //order_recipe
    public final static String INSERT_ORDER_RECIPE = "INSERT INTO order_recipe(order_id, recipe_code) VALUES (?, ?)";

    public final static String SELECT_RECIPE_CODE_BY_ORDER_ID =
            "SELECT recipe_code FROM order_recipe WHERE order_id = ?";

    //recipe_extension_request
    public final static String INSERT_RECIPE_EXTENSION_REQUEST =
            "INSERT INTO recipe_extension_request(recipe_code, client_id, request_date, status, is_read) " +
                    "VALUES (?, ?, NOW(), 'new', 'no')";

    public final static String SELECT_NEW_RECIPE_EXTENSION_REQUEST =
            "SELECT * FROM recipe_extension_request WHERE status = 'new'";

    public final static String UPDATE_RECIPE_EXTENSION_REQUEST_STATUS_APPROVED =
            "UPDATE recipe_extension_request SET status = 'approved', doctor_id = ?, request_date = request_date, " +
                    "response_date = NOW() WHERE id = ?";

    public final static String UPDATE_RECIPE_EXTENSION_REQUEST_STATUS_DENIED =
            "UPDATE recipe_extension_request SET status = 'denied', doctor_id = ?, request_date = request_date, " +
                    "response_date = NOW() WHERE id = ?";

    public final static String SELECT_DOCTOR_RESPONSE_BY_CLIENT_ID =
            "SELECT id, recipe_code, response_date, status FROM recipe_extension_request WHERE client_id = ? " +
                    "AND is_read = 'no' AND (status = 'approved' OR status = 'denied')";

    public final static String UPDATE_RECIPE_EXTENSION_REQUEST_IS_READ_YES =
            "UPDATE recipe_extension_request SET is_read = 'yes', request_date = request_date WHERE id = ?";
}
