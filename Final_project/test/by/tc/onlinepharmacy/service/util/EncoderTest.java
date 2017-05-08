package by.tc.onlinepharmacy.service.util;


import by.tc.onlinepharmacy.service.exception.ServiceException;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class EncoderTest {

    private final static String PASSWORD = "Aaaa1111";

    @Test
    public void testEncode() throws ServiceException {
        String encryptedPassword = Encoder.encode(PASSWORD);
        assertTrue(!PASSWORD.equals(encryptedPassword) && encryptedPassword.length() == 32);
    }
}