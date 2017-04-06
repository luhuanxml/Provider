package com.luhuan.rxprovider;

import android.os.Build;
import android.support.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/4/6 0006.
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
}
