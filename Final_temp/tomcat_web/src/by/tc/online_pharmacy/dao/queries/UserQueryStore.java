package by.tc.online_pharmacy.dao.queries;


public class UserQueryStore {

    private UserQueryStore() {}

    public final static String SIGN_IN_SELECT =
            "SELECT * FROM user WHERE mobile_phone = ? AND password = ?";

    public final static String SIGN_UP_INSERT =
            "INSERT INTO user VALUES (?,?,?,?,?,?,?)";

    public final static String SIGN_UP_SELECT = "SELECT id FROM user";

    public final static String IS_UNIQUE_SELECT =
            "SELECT mobile_phone FROM user WHERE mobile_phone = ?";
}
