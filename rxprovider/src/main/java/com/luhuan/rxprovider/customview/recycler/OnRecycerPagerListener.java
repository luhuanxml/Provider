package com.luhuan.rxprovider.customview.recycler;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by 鲁欢 on 2017/4/12 0012.
 * horizontal slide like viewpager ,listen the center item position
 */

public abstract class OnRecycerPagerListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager linearLayoutManager;

    public OnRecycerPagerListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        onCenterItem(linearLayoutManager.findLastCompletelyVisibleItemPosition());
    }

    public abstract void onCenterItem(int position);
}
