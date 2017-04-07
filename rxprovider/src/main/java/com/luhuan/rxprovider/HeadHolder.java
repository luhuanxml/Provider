package com.luhuan.rxprovider;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/4/7 0007.
 */

public abstract class HeadHolder<T>  extends RecyclerView.ViewHolder{
    public HeadHolder(View itemView) {
        super(itemView);
    }
    public abstract void getData(T t);
}
