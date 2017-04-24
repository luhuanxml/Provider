package com.luhuan.provider;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luhuan.rxprovider.Permission;
import com.luhuan.rxprovider.Resolution;
import com.luhuan.rxprovider.RxListener;
import com.luhuan.rxprovider.RxGlide;
import com.luhuan.rxprovider.RxToast;
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

import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class MainActivity extends Activity {
    XRecyclerView recyclerView;
    Banner banner;
    private static final String TAG = "MainActivity";
    /** Not a real crash reporting library! */
    public static final class FakeCrashLibrary {
        public static void log(int priority, String tag, String message) {
            // TODO add log entry to circular buffer.
        }

        public static void logWarning(Throwable t) {
            // TODO report non-fatal warning.
        }

        public static void logError(Throwable t) {
            // TODO report non-fatal error.
        }

        private FakeCrashLibrary() {
            throw new AssertionError("No instances.");
        }
    }

    /** A tree which logs important information for crash reporting. */
    private static class CrashReportingTree extends Timber.Tree {
        @Override protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }else {
            Timber.plant(new CrashReportingTree());
        }
        setContentView(R.layout.activity_main);
        Log.d(TAG, "width: "+ Screen.getScreenWidth(this));
        Log.d(TAG, "height: "+Screen.getScreenHeight(this));
        Log.d(TAG, "width-dp: "+ Resolution.pxToDp(this,Screen.getScreenWidth(this)));
        Log.d(TAG, "height-dp: "+ Resolution.pxToDp(this,Screen.getScreenHeight(this)));
        RxToast.init(this.getApplicationContext());

        RxListener.click(findViewById(R.id.click)).compose(Permission.getInstance(this).enSure(Permission.WRITE_EXTERNAL_STORAGE))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean){
                           RxToast.show("权限拿到了");
                            Timber.tag(TAG).d(TAG);
                            Timber.d(new Throwable("异常"),"方法走了1");
                            Timber.d("方法走了2");
                            Timber.d("方法走了3");
                            Timber.d("方法走了4");
                            Timber.d("方法走了5");
                            Timber.d("方法走了6");
                            Timber.d("方法走了7");
                            Timber.d("方法走了8");
                            Timber.d("方法走了9");
                            Timber.e("方法走了9");
                       //     startActivity(new Intent(MainActivity.this,Main2Activity.class));
                        }
                    }
                });
        RxGlide.init(this,R.mipmap.ic_launcher);
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
            public RxViewHolder<String> getHolder(ViewGroup parent) {
                TextView textView=new TextView(MainActivity.this);
                return new ViewHoder(textView);
            }
        };
        GridLayoutManager manager=new GridLayoutManager(MainActivity.this,1);
        final ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        recyclerView.addHeaderView(imageView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallGridPulse);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setRefreshHeader(new ArrowRefreshHeader(this,true));
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        RxListener.click(imageView).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                /**动画组合**/
                PropertyValuesHolder objectAnimatorScaleX = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);
                PropertyValuesHolder objectAnimatorScaleY = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);
                /**同时播放两个动画**/
                ObjectAnimator.ofPropertyValuesHolder(imageView, objectAnimatorScaleX, objectAnimatorScaleY).setDuration(3000).start();
            }
        });
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
