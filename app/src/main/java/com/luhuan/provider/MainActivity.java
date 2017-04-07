package com.luhuan.provider;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.luhuan.rxprovider.Permission;
import com.luhuan.rxprovider.RxCountDown;
import com.luhuan.rxprovider.RxListener;
import com.luhuan.rxprovider.RxToast;
import com.luhuan.rxprovider.CompressHelper;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends Activity {
    TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RxToast.init(this);
        time= (TextView) findViewById(R.id.time);
        RxListener.click(time).compose(Permission.getInstance(MainActivity.this).enSure(Permission.CAMERA))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Toast.makeText(MainActivity.this, "拿到权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RxCountDown.countDown(10).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                time.setText(integer + "");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                RxToast.show("计时结束");
            }
        });
    }
}
