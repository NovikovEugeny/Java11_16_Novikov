package by.tc.onlinepharmacy.dao.query;

/**
 * The class contains constants that represent queries to the database
 * to perform operations with data from table account.
 */
public final class AccountQueryStore {

    private AccountQueryStore() {
    }

    public final static String SELECT_BALANCE = "SELECT balance FROM account WHERE client_id = ?";

    public final static String UPDATE_PLUS_BALANCE =
            "UPDATE account SET balance = balance + (SELECT cost FROM orders WHERE id = ?) " +
                    "WHERE client_id = (SELECT client_id FROM orders WHERE id = ?)";

    public final static String UPDATE_MINUS_BALANCE = "UPDATE account SET balance = balance - ? WHERE client_id = ?";

    public final static String INSERT_NEW_ACCOUNT = "INSERT INTO account(client_id, balance) VALUES (?, 0)";

    public final static String UPDATE_REPLENISH_BALANCE = "UPDATE account SET balance = balance + ? WHERE client_id = ?";

}