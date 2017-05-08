package by.tc.onlinepharmacy.service;

import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.dao.connectionpool.ConnectionPool;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.exception.ValidatorException;
import by.tc.onlinepharmacy.service.impl.CommonServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;


public class CommonServiceTest {

    private final static String MOBILE = "+375295555555";
    private final static String PASSWORD = "Eeee5555";

    private CommonService commonService;
    private ConnectionPool pool;

    @Before
    public void init() throws SQLException, ClassNotFoundException {
        commonService = new CommonServiceImpl();
        pool = ConnectionPool.getInstance();
        pool.init();
    }

    @After
    public void destroy() throws SQLException {
        pool.closeConnections();
    }

    @Test
    public void testSignIn() throws ServiceException, ValidatorException {
        User user = commonService.signIn(MOBILE, PASSWORD);
        assertTrue(user != null);
    }
}