package com.likeit.aqe365.activity.web.jsinterface;

import android.os.Bundle;
import android.view.KeyEvent;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.web.base.BaseActivity;
import com.likeit.aqe365.activity.web.listener.FragmentBackListener;

import org.greenrobot.eventbus.EventBus;

public class JsInterfaceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercept);
        //EventBus.getDefault().register(this);
        JsInterfaceFragment fragment = (JsInterfaceFragment) getFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment == null) {
            fragment = new JsInterfaceFragment();
            JsInterfacePresenter presenter = new JsInterfacePresenter(fragment);
            fragment.setPresenter(presenter);
            addFragment(fragment, R.id.content_frame);
        }
    }


}