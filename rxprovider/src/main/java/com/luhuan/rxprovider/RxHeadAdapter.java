package com.luhuan.rxprovider;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/4/7 0007.
 * 添加头部和尾部的recyclerAdapter
 */

public abstract class RxHeadAdapter<T> extends RecyclerView.Adapter<HeadHolder<T>> {

    protected View firstHeadView;
    protected View secondHeadView;
    protected View footView;
    public static final int HEAD_ONE = 1;
    public static final int HEAD_TWO = 2;
    public static final int NORMAL = 3;
    public static final int FOOT = 4;

    private OnItemClickLitener<T> onItemClickListener;

    public void setOnItemClickListener(OnItemClickLitener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private List<T> datas;

    public RxHeadAdapter(List<T> datas) {
        this.datas = datas;
    }

    public void addFisrtHead(View firstHead) {
        firstHeadView = firstHead;
    }

    public void addSecondHead(View secondHead) {
        secondHeadView = secondHead;
    }

    public void addFootView(View footView) {
        this.footView = footView;
    }

    private int getHeadOneCount() {
        return firstHeadView == null ? 0 : 1;
    }

    private int getHeadTwoCount() {
        return secondHeadView == null ? 0 : 1;
    }

    private int getTotolHeadCount() {
        return getHeadOneCount() + getHeadTwoCount();
    }

    private int getFootCount() {
        return footView == null ? 0 : 1;
    }

    @Override
    public HeadHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return getHolder(parent, viewType);
    }

    protected abstract HeadHolder<T> getHolder(ViewGroup parent, int viewType);

    protected abstract int getItemLayoutId();

    @Override
    public void onBindViewHolder(HeadHolder<T> holder, @SuppressLint("RecyclerView") final int position) {
        if (getItemViewType(position) == HEAD_ONE) {
            return;
        }
        if (getItemViewType(position) == HEAD_TWO) {
            return;
        }
        if (getItemViewType(position) == FOOT) {
            return;
        }
        if (getItemViewType(position)==NORMAL){
            holder.getData(datas.get(position - getTotolHeadCount()));
            RxListener.click(holder.itemView).subscribe(new Consumer<Object>() {
                @Override
                public void accept(@NonNull Object o) throws Exception {
                    onItemClickListener.onItemClick(position - getTotolHeadCount(), datas.get(position - getTotolHeadCount()));
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position < getHeadOneCount()) {
            return HEAD_ONE;
        }else if (position<getTotolHeadCount()&&position>=getHeadOneCount()){
            return HEAD_TWO;
        }else if (position>=datas.size()+getTotolHeadCount()) {
            return FOOT;
        }else {
            return NORMAL;
        }

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size() +getTotolHeadCount()+getFootCount();
    }

    public interface OnItemClickLitener<T> {
        void onItemClick(int position, T t);
    }
}
