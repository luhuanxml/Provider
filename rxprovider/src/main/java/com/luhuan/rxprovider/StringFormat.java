package com.luhuan.rxprovider;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by luhuan on 2017/3/22.
 * 占位符
 *  &#160  表示空格
 *  %1$d  表示整形占位
 *  %1$f  表示浮点占位
 *  %1$s  表示字符串占位
 */

public class StringFormat {

    /**
     * 整形填充
     * @param context
     * @param resId
     * @param d
     * @return
     */
    public static String formatD(Context context,@StringRes int resId, Integer... d){
        String res=context.getResources().getString(resId);
        return String.format(res, (Object[]) d);
    }

    /**
     * 浮点填充
     * @param context
     * @param resId
     * @param f
     * @return
     */
    public static String formatF(Context context,@StringRes int resId, Float... f){
        String res=context.getResources().getString(resId);
        return String.format(res, (Object[]) f);
    }

    /**
     * 字符串填充
     * @param context
     * @param resId
     * @param s
     * @return
     */
    public static String format(Context context,@StringRes int resId, String... s){
        String res=context.getResources().getString(resId);
        return String.format(res, (Object[]) s);
    }
}
