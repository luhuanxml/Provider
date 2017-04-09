package com.luhuan.rxprovider.customview.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by luhuan on 2017/3/21.
 */

public abstract class RxViewHolder<T> extends RecyclerView.ViewHolder {
    public RxViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void setData(T t);
}
