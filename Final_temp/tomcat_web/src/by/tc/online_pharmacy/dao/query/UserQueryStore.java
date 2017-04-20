package by.tc.online_pharmacy.dao.query;


public final class UserQueryStore {

    private UserQueryStore() {
    }

    //aythorization
    public final static String SIGN_IN_SELECT = "SELECT * FROM user WHERE mobile_phone = ? AND password = ?";

    public final static String SIGN_UP_INSERT = "INSERT INTO user VALUES (?,?,?,?,?,?,?)";

    public final static String SIGN_UP_SELECT = "SELECT id FROM user";

    public final static String IS_UNIQUE_SELECT = "SELECT mobile_phone FROM user WHERE mobile_phone = ?";

    //account
    public final static String SELECT_BALANCE = "SELECT balance FROM account WHERE client_id = ?";

    public final static String UPDATE_PLUS_BALANCE =
            "UPDATE account SET balance = balance + (SELECT cost FROM orders WHERE id = ?) " +
                    "WHERE client_id = (SELECT client_id FROM orders WHERE id = ?)";

    public final static String UPDATE_MINUS_BALANCE = "UPDATE account SET balance = balance - ? WHERE client_id = ?";

    public final static String INSERT_NEW_ACCOUNT = "INSERT INTO account(client_id, balance) VALUES (?, 0)";

}
