package com.likeit.aqe365.activity.login.activity;

import android.view.animation.Animation;

import com.king.base.SplashActivity;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.login.activity.LoginActivity;

public class WelcomeActivity extends SplashActivity {



    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    public Animation.AnimationListener getAnimationListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivityFinish(LoginActivity.class);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

}
