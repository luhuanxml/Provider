package com.luhuan.provider;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.luhuan.rxprovider.Resolution;
import com.luhuan.rxprovider.RxPicasso;
import com.luhuan.rxprovider.Screen;
import com.luhuan.rxprovider.customview.banner.Banner;
import com.luhuan.rxprovider.customview.banner.Transformer;
import com.luhuan.rxprovider.customview.banner.loader.GlideImageLoader;
import com.luhuan.rxprovider.customview.recycler.ArrowRefreshHeader;
import com.luhuan.rxprovider.customview.recycler.ProgressStyle;
import com.luhuan.rxprovider.customview.recycler.RxAdapter;
import com.luhuan.rxprovider.customview.recycler.RxViewHolder;
import com.luhuan.rxprovider.customview.recycler.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    XRecyclerView recyclerView;
    Banner banner;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "width: "+ Screen.getScreenWidth(this));
        Log.d(TAG, "height: "+Screen.getScreenHeight(this));
        Log.d(TAG, "width-dp: "+ Resolution.pxToDp(this,Screen.getScreenWidth(this)));
        Log.d(TAG, "height-dp: "+ Resolution.pxToDp(this,Screen.getScreenHeight(this)));
        RxPicasso.init(this,R.mipmap.ic_launcher);
        banner= (Banner) findViewById(R.id.banner);
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
        }
        banner.setImages(list)
                .setBannerAnimation(Transformer.Default)
                .setImageLoader(new GlideImageLoader())
                .isAutoPlay(true).start();
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
