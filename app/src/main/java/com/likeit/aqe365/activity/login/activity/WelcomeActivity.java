package com.likeit.aqe365.activity.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.main.MainActivity;
import com.likeit.aqe365.activity.web.jsinterface.JsInterfaceActivity;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.utils.SharedPreferencesUtils;

public class WelcomeActivity extends BaseActivity {
    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this, R.layout.activity_welcome, null);
        setContentView(view);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_alpha);

        view.startAnimation(animation);
        animation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }   //在动画开始时使用

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }  //在动画重复时使用

            @Override
            public void onAnimationEnd(Animation arg0) {
                String isWeb=SharedPreferencesUtils.getString(mContext,"isWeb");
                if("1".equals(isWeb)){
                    toActivityFinish(LoginActivity.class);//原生
                }else{
                    SharedPreferencesUtils.put(WelcomeActivity.this, "login", "1");
                    toActivityFinish(JsInterfaceActivity.class);//半原生
                }



            }
        });
    }

}
