package by.tc.onlinepharmacy.dao;


import by.tc.onlinepharmacy.dao.connectionpool.ConnectionPool;
import by.tc.onlinepharmacy.dao.exception.DAOException;
import by.tc.onlinepharmacy.dao.impl.ClientDAOImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;

public class ClientDAOTest {

    private final static String MOBILE = "+375291111119";

    private ClientDAO clientDAO;
    private ConnectionPool pool;

    @Before
    public void init() throws SQLException, ClassNotFoundException {
        clientDAO = new ClientDAOImpl();
        pool = ConnectionPool.getInstance();
        pool.init();
    }

    @After
    public void destroy() throws SQLException {
        pool.closeConnections();
    }

    @Test
    public void testIsUnique() throws DAOException {
        assertTrue(clientDAO.isUnique(MOBILE));
    }
}