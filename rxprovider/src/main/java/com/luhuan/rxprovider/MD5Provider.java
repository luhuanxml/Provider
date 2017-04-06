package com.luhuan.rxprovider;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class MD5Provider {

    public static String getMD5(String normalString) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(normalString.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] bytes = messageDigest.digest();
        StringBuffer md5Buffer = new StringBuffer();
        for (byte b : bytes) {
            if (Integer.toHexString(0xFF & b).length() == 1) {
                md5Buffer.append("0").append(Integer.toHexString(0xFF & b));
            } else {
                md5Buffer.append(Integer.toHexString(0xFF & b));
            }
        }
        // 16位加密，从第9位到25位
        return md5Buffer.toString().toLowerCase();
    }
}
