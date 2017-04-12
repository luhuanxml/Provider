package com.luhuan.rxprovider;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

/**
 * Created by 鲁欢 on 2017/4/12 0012.
 */

public class RecyclerPagerHelper {
    private static volatile RecyclerPagerHelper intance;
    private LinearLayoutManager linearLayoutManager;

    private RecyclerPagerHelper(){

    }

    public static RecyclerPagerHelper getIntance() {
        if (intance == null) {
            synchronized (RecyclerPagerHelper.class) {
                intance = new RecyclerPagerHelper();
            }
        }
        return intance;
    }

    public void getPosition(RecyclerView recyclerView,@Nullable final PositionListener positionListener) {
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int postion=linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (postion > -1) {
                    if (positionListener != null) {
                        positionListener.onCenterItem(postion);
                    }
                }
            }
        });
    }

    public interface PositionListener{
        void onCenterItem(int position);
    }
}
