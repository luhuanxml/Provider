package com.luhuan.rxprovider;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by 鲁欢 on 2017/4/15 0015.
 * 基于当前页面显示。不能像toast脱离当前页面
 */

public class RxSnackBar {
    public static void show(View rootprent,String msg,int duration){
        Snackbar.make(rootprent,msg,duration).show();
    }

    public static void showShort(View rootprent,String msg){
        Snackbar.make(rootprent,msg,Snackbar.LENGTH_SHORT).show();
    }
    public static void showShort(View rootprent,int msgResId){
        Snackbar.make(rootprent,msgResId,Snackbar.LENGTH_SHORT).show();
    }
    public static void showLong(View rootprent,String msg){
        Snackbar.make(rootprent,msg,Snackbar.LENGTH_LONG).show();
    }
    public static void showLong(View rootprent,int msgResId){
        Snackbar.make(rootprent,msgResId,Snackbar.LENGTH_LONG).show();
    }
}
