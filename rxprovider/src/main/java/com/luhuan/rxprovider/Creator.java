package com.luhuan.rxprovider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/4/6 0006.
 * 反射泛型对象
 */

public class Creator {
    public static <T> T getT(Object object,int i){
        try {
            Type type=object.getClass().getGenericSuperclass();
            ParameterizedType parameterizedType= (ParameterizedType) type;
            Type[] types=parameterizedType.getActualTypeArguments();
            Class<T> clazz= (Class<T>) types[i];
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
