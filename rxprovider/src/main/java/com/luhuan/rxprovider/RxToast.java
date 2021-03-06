package com.luhuan.rxprovider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class RxToast {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static void show(@NonNull String msg) {
        Observable.just(msg).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        if (mContext == null) {
                            throw new NullPointerException("Toast的上下文为空");
                        } else {
                            Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void show(@StringRes Integer stringId) {
        Observable.just(stringId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        if (mContext == null) {
                            throw new NullPointerException("Toast的上下文为空");
                        } else {
                            Toast.makeText(mContext, integer, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
