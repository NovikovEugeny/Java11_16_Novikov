package by.tc.online_pharmacy.dao.connection_pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public final class ConnectionPool {

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private BlockingQueue<Connection> availableConnections;
    private BlockingQueue<Connection> occupiedConnections;

    private final static ConnectionPool instance = new ConnectionPool();

    private ConnectionPool() {
        this.driverName = DBParameter.DB_DRIVER;
        this.url = DBParameter.DB_URL;
        this.user = DBParameter.DB_USER;
        this.password = DBParameter.DB_PASSWORD;
        this.poolSize = DBParameter.DB_POOL_SIZE;
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void init() throws ClassNotFoundException, SQLException {
        availableConnections = new ArrayBlockingQueue<>(poolSize);
        occupiedConnections = new ArrayBlockingQueue<>(poolSize);

        Class.forName(driverName);

        for (int i = 0; i < poolSize; i++) {
            availableConnections.add(DriverManager.getConnection(url, user, password));
        }
    }

    public Connection takeConnection() throws InterruptedException {
        Connection connection = availableConnections.take();
        occupiedConnections.put(connection);
        return connection;
    }

    public void putBackConnection(Connection connection) {
        if (connection != null) {
            availableConnections.add(connection);
            occupiedConnections.remove(connection);
        }
    }

    public void closeConnections() throws SQLException {
        for (Connection connection : availableConnections) {
            connection.close();
        }
        for (Connection connection : occupiedConnections) {
            connection.close();
        }
        availableConnections.clear();
        occupiedConnections.clear();
    }
}