package com.luhuan.provider;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.luhuan.rxprovider.RxAdapter;
import com.luhuan.rxprovider.RxViewHolder;
import com.luhuan.rxprovider.recycler.ArrowRefreshHeader;
import com.luhuan.rxprovider.recycler.ProgressStyle;
import com.luhuan.rxprovider.recycler.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    XRecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (XRecyclerView) findViewById(R.id.recycler);
        List<String> ss=new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ss.add(i+"");
        }

        RxAdapter<String> adapter=new RxAdapter<String>(ss,MainActivity.this) {
            @Override
            public RxViewHolder<String> getHolder() {
                TextView textView=new TextView(MainActivity.this);
                return new ViewHoder(textView);
            }
        };
        GridLayoutManager manager=new GridLayoutManager(MainActivity.this,1);
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        recyclerView.addHeaderView(imageView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallGridPulse);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setRefreshHeader(new ArrowRefreshHeader(this,true));
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        adapter.setOnItemClickListener(new RxAdapter.OnItemClickLitener<String>() {
            @Override
            public void onItemClick(int position, String s) {

            }
        });
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
               // recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
           //     recyclerView.loadMoreComplete();
            }
        });
    }
    private class ViewHoder extends RxViewHolder<String>{
        TextView textView;
        public ViewHoder(View itemView) {
            super(itemView);
            textView= (TextView) itemView;
        }

        @Override
        public void setData(String s) {
            textView.setText(s);
        }
    }
}
