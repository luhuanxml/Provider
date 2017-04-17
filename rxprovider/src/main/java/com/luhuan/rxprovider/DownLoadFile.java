package com.luhuan.rxprovider;

import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by lh on 2017/4/1 0001.
 * 文件下载封装
 */

public class DownLoadFile {

    interface ApiService {
        //下载二维码
        @GET
        Observable<ResponseBody> downPic(@Url String url);
    }

    private static volatile DownLoadFile instance;

    private String path;
    private String fileName;

    private DownLoadFile() {
    }

    public static DownLoadFile getInstance() {
        if (instance == null) {
            synchronized (DownLoadFile.class) {
                if (instance == null) {
                    instance = new DownLoadFile();
                }
            }
        }
        return instance;
    }

    /**
     * 默认保存路径为为 Environment.getExternalStorageDirectory().getPath()
     *
     * @param url      文件下载地址
     * @param fileName 文件名
     * @return  Observable<File>
     */
    public Observable<File> download(@NonNull String url, @NonNull String fileName) {
        path = Environment.getExternalStorageDirectory().getPath();
        this.fileName = fileName;
        return downloadFile(url);
    }

    /**
     * @param url      文件下载地址
     * @param path     文件保存路径
     * @param fileName 文件名
     * @return  Observable<File>
     */
    public Observable<File> download(@NonNull String url, @NonNull String fileName, String path) {
        this.path = path;
        this.fileName = fileName;
        return downloadFile(url);
    }

    private Observable<File> downloadFile(String url) {
        return RetrofitProvider.getInstance("")
                .create(ApiService.class)
                .downPic(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(@NonNull ResponseBody responseBody) throws Exception {
                        return saveFile(responseBody.byteStream());
                    }
                }).filter(new Predicate<File>() {
                    @Override
                    public boolean test(@NonNull File file) throws Exception {
                        return file != null;
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }

    private File saveFile(InputStream inputStream) {
        File tempFile = new File(path);
        OutputStream os = null;
        try {
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tempFile;
    }
}
