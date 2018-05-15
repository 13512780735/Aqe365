package com.likeit.aqe365.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import com.likeit.aqe365.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tencent.smtt.sdk.QbSdk;

import java.io.File;


/**
 * Created by Administrator on 2018/1/17.
 */

public class MyApplication extends Application {

    public static MyApplication mContext;
    private static MyApplication instance;
    public static Context applicationContext;


    public static MyApplication getInstance() {
        if (mContext == null) {
            return new MyApplication();
        } else {
            return mContext;
        }
    }

    @Override
    public void onCreate() {
        // MultiDex.install(this);
        super.onCreate();
        initImageLoad();
        instance = this;
        applicationContext = this;
        // DemoHelper.getInstance().init(mContext);
       //                                                           initX5WebView();

    }

    public static MyApplication getInstance(Context mContext) {
        return instance;
    }


    private void initX5WebView() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    private void initImageLoad() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.icon_default)
                .showImageOnFail(R.mipmap.icon_default)
                .cacheInMemory(true).cacheOnDisc(true).build();
        // 图片加载工具配置
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .discCacheSize(50 * 1024 * 1024)//
                .discCacheFileCount(100)// 缓存一百张图片
                .writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
    }

}
