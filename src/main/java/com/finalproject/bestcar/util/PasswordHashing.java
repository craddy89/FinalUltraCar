package com.finalproject.bestcar.util;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {

    private static final Logger LOG = Logger.getLogger(PasswordHashing.class);

    private PasswordHashing() {
    }

    public static String hash(String input) {
        byte[] bytes = {};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(input.getBytes());
            bytes = messageDigest.digest();
        } catch (NoSuchAlgorithmException | NullPointerException e) {
            LOG.error("NoSuchAlgorithmException | NullPointerException: " + e.getMessage());
        }
        BigInteger bigInteger = new BigInteger(1, bytes);
        String result = bigInteger.toString(16);
        return result.toUpperCase();
    }

}
