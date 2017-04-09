package com.luhuan.rxprovider;

import android.app.Activity;
import android.graphics.Point;
import android.view.WindowManager;

/**
 * Created by lh on 2017/4/9.
 */

public class Screen {

    public static int getScreenWidth(Activity currentActivity){
        WindowManager wm = currentActivity.getWindowManager();
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return size.x;
    }

    public static int getScreenHeight(Activity currentActivity){
        WindowManager wm = currentActivity.getWindowManager();
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return size.y;
    }
}
