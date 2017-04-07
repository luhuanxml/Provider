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
 */

public abstract class RxHeadAdapter<T> extends RecyclerView.Adapter<HeadHolder<T>> {

    protected View headView1;
    protected View headView2;
    protected View footView;
    protected static final int HEAD_ONE = 1;
    protected static final int HEAD_TWO = 2;
    protected static final int NORMAL = 3;
    protected static final int FOOT = 4;

    protected OnItemClickLitener<T> onItemClickListener;

    public void setOnItemClickListener(OnItemClickLitener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private List<T> datas;

    public RxHeadAdapter(List<T> datas) {
        this.datas = datas;
    }

    public void addHeadOne(View headView) {
        this.headView1 = headView;
    }

    public void addHeadTwo(View headView) {
        this.headView2 = headView;
    }

    public void addFootView(View footView) {
        this.footView = footView;
    }

    public int getHeadOneCount() {
        return headView1 == null ? 0 : 1;
    }

    public int getHeadTwoCount() {
        return headView2 == null ? 0 : 1;
    }

    public int getTotolHeadCount() {
        return getHeadOneCount() + getHeadTwoCount();
    }

    public int getFootCount() {
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
