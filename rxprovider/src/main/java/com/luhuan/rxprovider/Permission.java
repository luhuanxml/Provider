package com.luhuan.rxprovider;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class Permission {
    public final static String WRITE_EXTERNAL_STORAGE =android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public final static String READ_EXTERNAL_STORAGE =android.Manifest.permission.READ_EXTERNAL_STORAGE;
    public final static String CAMERA =android.Manifest.permission.CAMERA;

    private RxPermissions rxPermissions;
    private static Permission instance;
    private Permission(Activity activity){
        rxPermissions=new RxPermissions(activity);
    }
    public static Permission getInstance(Activity activity){
        if (instance == null) {
            synchronized (RxPermissions.class){
                if (instance == null) {
                    instance=new Permission(activity);
                }
            }
        }
        return instance;
    }

    public Observable<Boolean> request(String... permision){
        return rxPermissions.request(permision).filter(new Predicate<Boolean>() {
            @Override
            public boolean test(@NonNull Boolean aBoolean) throws Exception {
                return true;
            }
        });
    }
}
