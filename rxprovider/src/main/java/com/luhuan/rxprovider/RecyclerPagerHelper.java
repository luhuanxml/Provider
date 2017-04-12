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
    /**
     * RecyclerView LayoutManager必须为LinerLayoutManager或者其子类 否则抛出类型转换异常
     * linearLayoutManager.getOrientation() 值必须等于HORIZONTAL 否者抛出运行时异常
     */
    private LinearLayoutManager linearLayoutManager;
    /**
     * 滑动的时候设置自动居中
     */
    private SnapHelper snapHelper;

    private RecyclerPagerHelper(){
        snapHelper = new LinearSnapHelper();
    }

    public static RecyclerPagerHelper getIntance() {
        if (intance == null) {
            synchronized (RecyclerPagerHelper.class) {
                intance = new RecyclerPagerHelper();
            }
        }
        return intance;
    }

    /**
     * 设置recyclerview item居中， 并且监听当前居中的item的值
     * @param recyclerView  RecyclerView
     * @param positionListener  当前剧中item position监听器
     */
    public void getPosition(RecyclerView recyclerView,@Nullable final PositionListener positionListener) {
        snapHelper.attachToRecyclerView(recyclerView);
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (linearLayoutManager.getOrientation()!=LinearLayoutManager.HORIZONTAL) {
                throw new RuntimeException("请确认你的"+linearLayoutManager.getClass().getName()+"方向为HORIZONTAL");
            }
        }else {
            throw new ClassCastException("你当前的"+recyclerView.getLayoutManager().getClass().getName()+"不是"+linearLayoutManager.getClass().getName()+"本身或者子类");
        }
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
