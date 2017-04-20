package by.tc.online_pharmacy.listener;

import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;


public class Listener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(Listener.class.getName());

    private static final String CONNECTION_POOL_EXCEPTION_MESSAGE =
            "Connection pool has not been connected";
    private final static String NOT_CLOSED_CONNECTIONS_MESSAGE =
            "At the end of the application, the connections were not closed";

    private ConnectionPool pool = ConnectionPool.getInstance();

    public void contextInitialized(ServletContextEvent event) {
        try {
            pool.init();
        } catch (SQLException | ClassNotFoundException exc) {
            logger.log(Level.FATAL, CONNECTION_POOL_EXCEPTION_MESSAGE, exc);
            System.exit(0);
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        try {
            pool.closeConnections();
        } catch (SQLException exc) {
            logger.log(Level.WARN, NOT_CLOSED_CONNECTIONS_MESSAGE, exc);
        }
    }
}