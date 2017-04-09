package com.luhuan.rxprovider;

import android.content.Context;

/**
 * Created by Administrator on 2017/4/6 0006.
 * dp转px
 * px转dp
 */

public class Resolution {

    public static int dpToPx(Context context, float dp_Size) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp_Size * scale + 0.5f);
    }

    public static int pxToDp(Context context, float px_Size) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px_Size / scale + 0.5f);
    }

}
