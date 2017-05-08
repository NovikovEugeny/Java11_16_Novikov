package by.tc.onlinepharmacy.dao.query;

/**
 * The class contains constants that represent queries to the database
 * to perform operations with data from table orders.
 */
public final class OrderQueryStore {

    private OrderQueryStore() {
    }

    public final static String UPDATE_SENT_ORDER_STATUS =
            "UPDATE orders SET pharmacist_id = ?, status = 'sent', request_date = request_date, " +
                    "response_date = NOW() WHERE id = ?";

    public final static String UPDATE_CANCEL_ORDER_STATUS =
            "UPDATE orders SET status = 'canceled', request_date = request_date WHERE id = ?";

    public final static String UPDATE_RECEIVE_ORDER_STATUS =
            "UPDATE orders SET status = 'received', request_date = request_date WHERE id = ?";

    public final static String INSERT_ORDER =
            "INSERT INTO orders(client_id, drug_id, quantity, cost, request_date, status) VALUES (?,?,?,?,NOW(),?)";

}