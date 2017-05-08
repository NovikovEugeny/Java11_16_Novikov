package by.tc.onlinepharmacy.service.util;

import by.tc.onlinepharmacy.service.exception.ServiceException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The class contains a static method that takes the input password
 * and returns the encoded.
 */
public final class Encoder {

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

            while (md5Hex.length() < 32) {
                md5Hex = "0" + md5Hex;
            }
        } catch (NoSuchAlgorithmException exc) {
            throw new ServiceException(exc);
        }
        return md5Hex;
    }
}