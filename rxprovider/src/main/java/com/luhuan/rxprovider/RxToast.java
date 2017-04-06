package com.luhuan.rxprovider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class RxToast {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    public static void init(Context context){
        mContext=context;
    }

    public static void show(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(Integer stringId){
        Toast.makeText(mContext, stringId, Toast.LENGTH_SHORT).show();
    }
}
