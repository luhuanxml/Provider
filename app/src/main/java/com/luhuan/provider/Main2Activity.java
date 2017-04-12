package com.luhuan.provider;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.ImageView;

import com.luhuan.rxprovider.RecyclerPagerHelper;
import com.luhuan.rxprovider.RxToast;
import com.luhuan.rxprovider.customview.recycler.OnRecycerPagerListener;
import com.luhuan.rxprovider.customview.recycler.RxAdapter;
import com.luhuan.rxprovider.customview.recycler.RxViewHolder;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends Activity {
    RecyclerView recyclerView;
    List<Integer> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxToast.init(this);
        setContentView(R.layout.activity_main2);
        recyclerView= (RecyclerView) findViewById(R.id.horizontal);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ItemDec itemDec=new ItemDec(20);
        list.add(R.mipmap.img01);
        list.add(R.mipmap.img02);
        list.add(R.mipmap.img03);
        list.add(R.mipmap.img04);
        list.add(R.mipmap.img05);
        list.add(R.mipmap.img06);
        list.add(R.mipmap.img07);
        list.add(R.mipmap.img08);
        list.add(R.mipmap.img09);
        RxAdapter<Integer> adapter=new RxAdapter<Integer>(list,this) {
            @Override
            public RxViewHolder<Integer> getHolder() {
                View view=View.inflate(Main2Activity.this,R.layout.item2_adpter,null);
                return new ViewHoder(view);
            }
        };
        recyclerView.addItemDecoration(itemDec);
        recyclerView.setAdapter(adapter);
        RecyclerPagerHelper.getIntance().getPosition(recyclerView,null);
   }

   private class ItemDec extends RecyclerView.ItemDecoration{
       private int dividerWidth;

       public ItemDec(int dividerWidth) {
           this.dividerWidth = dividerWidth;
       }

       @Override
       public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
           super.getItemOffsets(outRect, view, parent, state);
           outRect.left=dividerWidth;
           outRect.right=dividerWidth;
       }
   }

    private class ViewHoder extends RxViewHolder<Integer>{
        ImageView imgView;
        ViewHoder(View itemView) {
            super(itemView);
            imgView= (ImageView) itemView.findViewById(R.id.item2);
        }

        @Override
        public void setData(Integer integer) {
            imgView.setImageResource(integer);
        }
    }
}
