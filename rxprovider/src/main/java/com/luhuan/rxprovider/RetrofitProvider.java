package com.luhuan.rxprovider;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/4/6 0006.
 * Retrofit提供者
 */

public class RetrofitProvider {
    private static final String TAG = "RetrofitProvider";
    private static String ENDPOINT;
    private RetrofitProvider(){
        
    }

    public static Retrofit getInstance(@NonNull String baseUrl){
        ENDPOINT=baseUrl;
        return SingletonHolder.INSTANCE;
    }
    
    private static class SingletonHolder{
        private static final Retrofit INSTANCE =create();

        private static Retrofit create() {
            HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.d(TAG, "Retrofit: "+message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder=new OkHttpClient.Builder();
            builder.writeTimeout(10, TimeUnit.SECONDS);
            builder.connectTimeout(10,TimeUnit.SECONDS);
            builder.readTimeout(10,TimeUnit.SECONDS);
            builder.addInterceptor(loggingInterceptor);
            return new Retrofit.Builder().baseUrl(ENDPOINT)
                    .client(builder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
    }
}
