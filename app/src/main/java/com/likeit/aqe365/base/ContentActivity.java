package com.likeit.aqe365.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.likeit.aqe365.R;
import com.likeit.aqe365.utils.AppManager;
import com.likeit.aqe365.utils.StatusBarUtil;

/**
 * Created by admin on 2018/5/10.
 */

public abstract  class ContentActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        AppManager.getAppManager().addActivity(this);
        StatusBarUtil.transparencyBar(this); //设置状态栏全透明
        StatusBarUtil.StatusBarLightMode(this); //设置白底黑字
        switchFragment(getIntent());
    }

    public <T extends View> T findView(@IdRes int id) {
        return (T) findViewById(id);
    }

    protected void replaceFragment(Fragment fragmnet) {
        replaceFragment(R.id.fragmentContent, fragmnet);
    }

    protected void replaceFragment(@IdRes int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    protected abstract void switchFragment(Intent intent);
}
