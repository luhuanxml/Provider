package com.luhuan.rxprovider.customview.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.luhuan.rxprovider.customview.banner.transformer.DefaultTransformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
}
