package by.tc.online_pharmacy;

import by.tc.online_pharmacy.dao.connection_pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;


public class Listener implements ServletContextListener {

    //private final static Logger logger = LogManager.getLogger(Listener.class);

    private ConnectionPool pool = ConnectionPool.getInstance();

    public void contextInitialized(ServletContextEvent event) {
        try {
            pool.init();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        try {
            pool.closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
