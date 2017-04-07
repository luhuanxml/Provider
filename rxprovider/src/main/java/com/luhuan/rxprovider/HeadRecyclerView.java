package com.luhuan.rxprovider;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import static com.luhuan.rxprovider.RxHeadAdapter.FOOT;
import static com.luhuan.rxprovider.RxHeadAdapter.HEAD_ONE;
import static com.luhuan.rxprovider.RxHeadAdapter.HEAD_TWO;

/**
 * Created by Administrator on 2017/4/7 0007.
 */

public class HeadRecyclerView extends RecyclerView {
    GridLayoutManager manager;
    public HeadRecyclerView(Context context) {
        super(context);
    }

    public HeadRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void setAdapter(final Adapter adapter) {
        super.setAdapter(adapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getItemViewType(position)==HEAD_ONE
                        ||adapter.getItemViewType(position)==HEAD_TWO
                        ||adapter.getItemViewType(position)==FOOT
                        ?manager.getSpanCount():1;
            }
        });
    }

    @Override
    public void setLayoutManager(final LayoutManager layout) {
        super.setLayoutManager(layout);
        if (layout instanceof GridLayoutManager){
            manager= (GridLayoutManager) layout;
        }
    }
}
