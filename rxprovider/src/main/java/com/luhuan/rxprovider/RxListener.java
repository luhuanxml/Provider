package com.luhuan.rxprovider;

import android.support.annotation.NonNull;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class RxListener {
    public static Observable<Object> click(@NonNull View view){
        return RxView.clicks(view).throttleFirst(1, TimeUnit.SECONDS);
    }
}
