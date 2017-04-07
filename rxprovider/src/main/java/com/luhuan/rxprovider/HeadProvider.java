package com.luhuan.rxprovider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by lh on 2017/4/7.
 */

public class HeadProvider {

    public static View createHead(Context context,int layoutId){
        return LayoutInflater.from(context).inflate(layoutId,null);
    }
}
