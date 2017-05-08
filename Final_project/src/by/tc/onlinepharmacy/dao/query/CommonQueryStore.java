package by.tc.onlinepharmacy.dao.query;

/**
 * The class contains constants that represent queries to the database
 * to perform operations with data from several table.
 */
public final class CommonQueryStore {

    private CommonQueryStore() {
    }

    public final static String SELECT_LAST_INSERT_ID = "SELECT last_insert_id()";

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

    public final static String SELECT_RECIPE_DESCRIPTION_BY_CODE =
            "SELECT r.drug_id, d.name AS `drug_name`, d.pharm_group, d.form, d.drug_amount, d.active_substances, " +
                    "d.country, d.price, r.quantity, d.price*r.quantity AS `cost`, r.end_date, r.status FROM " +
                    "recipe r INNER JOIN drug d ON r.drug_id = d.id WHERE r.code = ?";

}