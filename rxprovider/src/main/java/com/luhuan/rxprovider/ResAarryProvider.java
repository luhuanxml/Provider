package com.luhuan.rxprovider;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 鲁欢 on 2017/4/14 0014.
 * get res/array  一维数组
 */

public class ResAarryProvider {
    /**
     * 获取图片资源集合
     * @param context 上下文
     * @param drawablesRes  图片数组资源id
     * @return ArrayList<Integer>
     */
    public static ArrayList<Integer> getDrawables(Context context, int drawablesRes) {
        ArrayList<Integer> drawableList = new ArrayList<>();
        TypedArray pictures = context.getResources().obtainTypedArray(drawablesRes);
        for (int i = 0; i < pictures.length(); i++){
            drawableList.add(pictures.getResourceId(i,0));
        }
        pictures.recycle();
        return drawableList;
    }

    /**
     * 获取字符串数组资源
     * @param context 上下文
     * @param stringsRes  字符串数组资源id
     * @return ArrayList<String>
     */
    public static ArrayList<String> getStrings(Context context, int stringsRes) {
        ArrayList<String> stringList = new ArrayList<>();
        String[] strings = context.getResources().getStringArray(stringsRes);
        Collections.addAll(stringList, strings);
        return stringList;
    }
}
