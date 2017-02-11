package com.vid.utils;

import java.security.MessageDigest;

/**
 * Created by Jiayiwu on 16/7/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class SHA256 {
    public static String encrypt(String word) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return Byte2String(sha256.digest(word.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    private static String Byte2String(byte[] bytes) {
        String result = "";
        for (byte aByte : bytes) {
            int temp = aByte & 0xff;
            String tempHex = Integer.toHexString(temp);
            if (tempHex.length() < 2) {
                result += "0" + tempHex;

            } else {
                result += tempHex;
            }
        }

        return result;
    }
}
