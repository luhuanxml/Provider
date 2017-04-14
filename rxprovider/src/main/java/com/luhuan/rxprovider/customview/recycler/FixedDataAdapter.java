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
 * 固定数据列表的adapter 主页不对他做数据传递和变更
 */

public abstract class FixedDataAdapter<T> extends RecyclerView.Adapter<RxViewHolder<T>> {
    protected List<T> list;
    protected Context mContext;
    protected OnItemClickLitener<T> onItemClickListener;

    public FixedDataAdapter(Context context) {
        mContext=context;
    }

    public void setOnItemClickListener(OnItemClickLitener<T> onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public RxViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return getHolder(parent);
    }

    protected abstract RxViewHolder<T> getHolder(ViewGroup parent) ;

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
