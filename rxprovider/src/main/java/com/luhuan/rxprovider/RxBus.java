package com.luhuan.rxprovider;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class RxBus {
    private final Subject<Object> mBus;

    private RxBus() {
        mBus = PublishSubject.create().toSerialized();
    }

    public static RxBus getSingle(){
        return Holder.BUS;
    }

    public void post(Object object){
        mBus.onNext(object);
    }

    public <T>Observable<T> toObservable(Class<T> tClass){
        return mBus.ofType(tClass);
    }

    private static class Holder {
        private static final RxBus BUS = new RxBus();
    }
}
