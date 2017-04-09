package com.luhuan.rxprovider.customview.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.luhuan.rxprovider.RxListener;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by luhuan on 2017/3/21.
 */

public abstract class RxAdapter<T> extends RecyclerView.Adapter<RxViewHolder<T>> {
    protected List<T> list;
    protected Context mContext;
    protected OnItemClickLitener<T> onItemClickListener;

    public RxAdapter(List<T> list ,Context context) {
        this.list = list;
        mContext=context;
    }

    public void setOnItemClickListener(OnItemClickLitener<T> onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public void refresh(List<T> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public RxViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return getHolder();
    }

    public abstract RxViewHolder<T> getHolder() ;

    @Override
    public void onBindViewHolder(RxViewHolder<T> holder, final int position) {
        holder.setData(list.get(position));
        RxListener.click(holder.itemView)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        onItemClickListener.onItemClick(position,list.get(position));
                    }
                });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public interface OnItemClickLitener<T> {
        void onItemClick(int position, T t);
    }
}
