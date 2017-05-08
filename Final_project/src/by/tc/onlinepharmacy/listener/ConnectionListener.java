package by.tc.onlinepharmacy.listener;

import by.tc.onlinepharmacy.dao.connectionpool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

/**
 * Initializes the {@link by.tc.onlinepharmacy.dao.connectionpool.ConnectionPool ConnectionPool}
 * when the application starts and closes all connections when the application exits.
 *
 * @see javax.servlet.ServletContextListener
 */
public class ConnectionListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionListener.class.getName());

    private static final String CONNECTION_POOL_EXCEPTION_MESSAGE = "Connection pool has not been connected";
    private final static String NOT_CLOSED_CONNECTIONS_MESSAGE = "Connections were not closed";

    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            pool.init();

        } catch (SQLException | ClassNotFoundException exc) {
            LOGGER.log(Level.FATAL, CONNECTION_POOL_EXCEPTION_MESSAGE, exc);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            pool.closeConnections();

        } catch (SQLException exc) {
            LOGGER.log(Level.WARN, NOT_CLOSED_CONNECTIONS_MESSAGE, exc);
        }
    }
}