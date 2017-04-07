package com.luhuan.rxprovider;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class ParamsProvider {
    public static String joint(@NonNull HashMap<String,String> params){
        Iterator<String> iterator=params.keySet().iterator();
        StringBuilder stringBuilder=new StringBuilder();
        while (iterator.hasNext()){
            String key=iterator.next();
            String value=params.get(key);
            stringBuilder.append("&").append(key).append("=").append(value);
        }
        return stringBuilder.toString();
    }
}
