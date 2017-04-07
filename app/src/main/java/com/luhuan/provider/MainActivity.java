package com.luhuan.provider;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.luhuan.rxprovider.RxHeadAdapter;
import com.luhuan.rxprovider.RxListener;
import com.luhuan.rxprovider.RxToast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static com.luhuan.rxprovider.RxHeadAdapter.FOOT;
import static com.luhuan.rxprovider.RxHeadAdapter.HEAD_ONE;
import static com.luhuan.rxprovider.RxHeadAdapter.HEAD_TWO;

public class MainActivity extends Activity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RxToast.init(this );
        List<String> datas=new ArrayList<>();
        for (int i = 0; i <50; i++) {
            datas.add(i+"");
        }
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        final MyAdapter myAdapter=new MyAdapter(datas);
        ImageView head1=new ImageView(this);
        head1.setImageResource(R.mipmap.ic_launcher);
        RxListener.click(head1).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                RxToast.show("head1");
            }
        });
        ImageView head2=new ImageView(this);
        head2.setImageResource(R.mipmap.ic_launcher);
        RxListener.click(head2).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                RxToast.show("head2");
            }
        });
        ImageView foot=new ImageView(this);
        foot.setImageResource(R.mipmap.logo_sc);
        RxListener.click(foot).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                RxToast.show("foot");
            }
        });
        myAdapter.addFisrtHead(head1);
        myAdapter.addSecondHead(head2);
        myAdapter.addFootView(foot);
        final GridLayoutManager manager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myAdapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return myAdapter.getItemViewType(position)==HEAD_ONE
                        ||myAdapter.getItemViewType(position)==HEAD_TWO
                        ||myAdapter.getItemViewType(position)==FOOT
                        ?manager.getSpanCount():1;
            }
        });
        myAdapter.setOnItemClickListener(new RxHeadAdapter.OnItemClickLitener<String>() {

            @Override
            public void onItemClick(int position, String s) {
                RxToast.show(position+"");
            }
        });
    }
}
