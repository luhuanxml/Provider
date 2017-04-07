package com.luhuan.rxprovider;

import android.os.Build;
import android.support.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/4/6 0006.
 * 加密工具
 */

public class Encryption {
    public static String encodePhone() {
        return encode(Build.MODEL);
    }

    public static String encode(@NonNull String s) {
        String urlCode = "";
        try {
            urlCode = URLEncoder.encode(s, "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlCode;
    }

    public static String getMD5(@NonNull String normalString) {
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
        StringBuilder md5builder = new StringBuilder();
        for (byte b : bytes) {
            if (Integer.toHexString(0xFF & b).length() == 1) {
                md5builder.append("0").append(Integer.toHexString(0xFF & b));
            } else {
                md5builder.append(Integer.toHexString(0xFF & b));
            }
        }
        // 16位加密，从第9位到25位
        return md5builder.toString().toLowerCase();
    }
}
