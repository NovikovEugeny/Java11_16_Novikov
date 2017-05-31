package by.tc.onlinepharmacy.dao.connectionpool;


public final class DBParameter {

    private DBParameter() {
    }

    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";
    public static final int DB_POOL_SIZE = 5;
}
