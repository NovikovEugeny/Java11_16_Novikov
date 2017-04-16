package by.tc.online_pharmacy.service.util;

import by.tc.online_pharmacy.service.exception.ServiceException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encoder {

    public static String encode(String str) throws ServiceException {

        MessageDigest messageDigest = null;
        byte[] digest = null;
        String md5Hex = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes());
            digest = messageDigest.digest();

            BigInteger bigInt = new BigInteger(1, digest);
            md5Hex = bigInt.toString(16);

            while( md5Hex.length() < 32 ){
                md5Hex = "0" + md5Hex;
            }
        } catch (NoSuchAlgorithmException exc) {
            throw new ServiceException(exc);
        }
        return md5Hex;
    }
}
