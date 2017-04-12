package com.luhuan.rxprovider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by Administrator on 2017/4/6 0006.
 * 封装Picasso
 */

public class RxPicasso {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static Integer mdrawableId;

    public static void init(Context context, @DrawableRes Integer errorId){
        mContext=context;
        mdrawableId=errorId;
    }

    public static RequestCreator load(@NonNull String url, @DrawableRes Integer errorId){
        if (mContext == null) {
            throw new NullPointerException("picasso的上下文为空");
        }else {
            return Picasso.with(mContext).load(url).placeholder(errorId).error(errorId);
        }
    }

    public static RequestCreator load(@NonNull String url){
        if (mContext == null) {
            throw new NullPointerException("picasso的上下文为空");
        }else {
            return Picasso.with(mContext).load(url).placeholder(mdrawableId).error(mdrawableId);
        }
    }

    public static RequestCreator load(@DrawableRes Integer drawableId){
        if (mContext == null) {
            throw new NullPointerException("picasso的上下文为空");
        }else {
            return Picasso.with(mContext).load(drawableId).placeholder(mdrawableId).error(mdrawableId);
        }
    }
}
