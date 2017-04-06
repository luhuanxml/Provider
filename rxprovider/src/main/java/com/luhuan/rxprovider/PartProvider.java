package com.luhuan.rxprovider;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class PartProvider {
    public static MultipartBody.Part getPart(File file, String key){
        RequestBody requestBody=RequestBody.create(MultipartBody.FORM,file);
        return MultipartBody.Part.createFormData(key,file.getName(),requestBody);
    }
}
