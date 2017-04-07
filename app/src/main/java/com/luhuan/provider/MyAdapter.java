package com.luhuan.provider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luhuan.rxprovider.HeadHolder;
import com.luhuan.rxprovider.RxHeadAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7 0007.
 */

public class MyAdapter extends RxHeadAdapter<String> {
    public MyAdapter(List<String> datas) {
        super(datas);
    }

    @Override
    protected HeadHolder<String> getHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_ONE) {
            return new MyHolder(headView1);
        }
        if (viewType == HEAD_TWO) {
            return new MyHolder(headView2);
        }
        if (viewType == FOOT) {
            return new MyHolder(footView);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(getItemLayoutId(),null);
        return new MyHolder(view);

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_adpter;
    }

    private class MyHolder extends HeadHolder<String>{
        TextView textView;
        MyHolder(View itemView) {
            super(itemView);
            if (itemView == headView1) return;
            if (itemView == headView2) return;
            if (itemView == footView) return;
            textView= (TextView) itemView.findViewById(R.id.item);
        }

        @Override
        public void getData(String s) {
            textView.setText(s);
        }
    }
}
