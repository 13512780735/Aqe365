package com.king.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class ContentActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView( R.layout.activity_content);
//        Window window = this.getWindow();
//        // 透明状态栏
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        // 透明导航栏
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));
//        }
        AppManager.getAppManager().addActivity(this);
        StatusBarUtil.transparencyBar(this); //设置状态栏全透明
        StatusBarUtil.StatusBarLightMode(this); //设置白底黑字
        switchFragment(getIntent());
    }

    public <T extends View> T findView(@IdRes int id){
        return (T)findViewById(id);
    }

    protected void replaceFragment(Fragment fragmnet){
        replaceFragment(R.id.fragmentContent,fragmnet);
    }

    protected void replaceFragment(@IdRes int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    protected abstract void switchFragment(Intent intent);
}
