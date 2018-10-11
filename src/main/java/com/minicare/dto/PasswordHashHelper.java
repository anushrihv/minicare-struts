package com.minicare.dto;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;


public class PasswordHashHelper {


    public static String get_SHA_256_SecurePassword(String passwordToHash)
    {
        String result = null;

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(passwordToHash.getBytes("UTF-8"));

            return bytesToHex(hash); // make it printable

        }catch(Exception ex) {

            ex.printStackTrace();

        }

        return result;

    }

    private static String  bytesToHex(byte[] hash) {

        return DatatypeConverter.printHexBinary(hash);

    }

}
