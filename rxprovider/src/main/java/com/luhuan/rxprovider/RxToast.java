package com.luhuan.rxprovider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

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
        Observable.just(msg).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static void show(Integer stringId){
        Observable.just(stringId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Toast.makeText(mContext, integer, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
