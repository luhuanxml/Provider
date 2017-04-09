package com.luhuan.rxprovider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by luhuan on 2017/3/30 0030.
 * 只针对item类型单一的情况  实现头部炫富停靠
 */

public class FloatHelper {
    @SuppressLint("StaticFieldLeak")
    private volatile static FloatHelper instance;

    private RecyclerView recyclerView;
    private View floatHead;
    private GridLayoutManager manager;

    private Context context;

    private int spanCount;
    /**
     * dy recyclerview的滑动距离 不停的累记dy这个值
     */
    private int totalChange;

    private FloatHelper(Context context) {
        this.context = context;
    }

    public static FloatHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (FloatHelper.class) {
                if (instance == null) {
                    instance = new FloatHelper(context);
                }
            }
        }
        return instance;
    }

    /**
     * @param recyclerView
     * @param floatHead    //要进行管理的悬停头部view
     */
    public FloatHelper prepare(RecyclerView recyclerView, View floatHead) {
        this.recyclerView = recyclerView;
        this.floatHead = floatHead;
        return instance;
    }

    /**
     * 默认使用 GridLayoutManager
     *
     * @param spanCount GridLayoutManager 设置的一排的并列数
     * @param adapter   这里直接传入Adapter给recyclerview去设置，防止方法用在setAdapter()之后
     * @return instance
     */
    public FloatHelper setManagerAndAdapter(final int spanCount, RecyclerView.Adapter adapter) {
        this.spanCount = spanCount;
        manager = new GridLayoutManager(context, spanCount);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return instance;
    }

    /**
     * @param maxDistPx 需要悬浮的头部到屏幕顶部(不包括状态栏)的距离，单位px。
     *                  控件相对于其顶部位置的垂直位置 屏幕左上方为原点(0,0)。
     *                  进来时先给浮点头部一个相对于Y的偏移量。
     *                  设置第一个头部悬浮时，这个值为0。
     *                  当设置第二个头部悬浮时，这个值为第一个头部的高度
     */
    public void setFloatHead(final int maxDistPx) {
        floatHead.setTranslationY(maxDistPx);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.d("y", "onScrolled: " + dy);
                totalChange += dy;
                int tranY = Math.max(0, maxDistPx - totalChange);
                //移动距离超过maxDist，就定在0处
                floatHead.setTranslationY(tranY);
                //lastPosition 是最后从屏幕中出现的item 顶部的值是10*spanCount。
                int lastPosition = manager.findLastVisibleItemPosition();
                Log.d("yy", "onScrolled: " + lastPosition);
                if (lastPosition <= 10 * spanCount) {
                    floatHead.setVisibility(View.GONE);
                } else {
                    floatHead.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
