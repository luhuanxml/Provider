package com.luhuan.rxprovider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class RxPicasso {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    static Integer mdrawableId;

    public static void init(Context context, @DrawableRes Integer drawableId){
        mContext=context;
        mdrawableId=drawableId;
    }

    public static RequestCreator load(@NonNull String url, @DrawableRes Integer drawableId){
        if (mContext == null) {
            throw new NullPointerException("picasso的上下文为空");
        }else {
            return Picasso.with(mContext).load(url).placeholder(drawableId).error(drawableId);
        }
    }

    public static RequestCreator load(@NonNull String url){
        if (mContext == null) {
            throw new NullPointerException("picasso的上下文为空");
        }else {
            return Picasso.with(mContext).load(url).placeholder(mdrawableId).error(mdrawableId);
        }
    }
}
