package com.luhuan.rxprovider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;

import static com.bumptech.glide.Glide.with;

/**
 * Created by Administrator on 2017/4/6 0006.
 * 封装Picasso
 */

public class RxGlide {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static Integer mdrawableId;

    public static void init(Context context, @DrawableRes Integer errorId){
        mContext=context;
        mdrawableId=errorId;
    }

    public static DrawableRequestBuilder<String> load(@NonNull String url, @DrawableRes Integer errorId){
        if (mContext == null) {
            throw new NullPointerException("picasso的上下文为空");
        }else {
            return with(mContext).load(url).placeholder(errorId).error(errorId);
        }
    }

    public static DrawableRequestBuilder<String> load(@NonNull String url){
        if (mContext == null) {
            throw new NullPointerException("picasso的上下文为空");
        }else {
            return with(mContext).load(url).placeholder(mdrawableId).error(mdrawableId);
        }
    }

    public static DrawableRequestBuilder<Integer> load(@DrawableRes Integer drawableId){
        if (mContext == null) {
            throw new NullPointerException("picasso的上下文为空");
        }else {
            return Glide.with(mContext).load(drawableId).placeholder(mdrawableId).error(mdrawableId);
        }
    }
}
