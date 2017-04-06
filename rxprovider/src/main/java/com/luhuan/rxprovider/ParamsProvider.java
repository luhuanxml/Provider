package com.luhuan.rxprovider;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class ParamsProvider {
    public static String joint(HashMap<String,String> params){
        Iterator<String> iterator=params.keySet().iterator();
        StringBuffer stringBuffer=new StringBuffer();
        while (iterator.hasNext()){
            String key=iterator.next();
            String value=params.get(key);
            stringBuffer.append("&").append(key).append("=").append(value);
        }
        return stringBuffer.toString();
    }
}
