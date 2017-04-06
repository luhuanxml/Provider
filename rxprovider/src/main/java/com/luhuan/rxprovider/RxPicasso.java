package com.luhuan.rxprovider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.DrawableRes;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import io.reactivex.annotations.NonNull;

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
        return Picasso.with(mContext).load(url).placeholder(drawableId).error(drawableId);
    }

    public static RequestCreator load(@NonNull String url){
        return Picasso.with(mContext).load(url).placeholder(mdrawableId).error(mdrawableId);
    }
}
