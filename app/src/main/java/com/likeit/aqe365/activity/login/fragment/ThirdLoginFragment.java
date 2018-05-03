package com.likeit.aqe365.activity.login.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.king.base.BaseFragment;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.FrameActivity;
import com.likeit.aqe365.constants.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdLoginFragment extends BaseFragment implements View.OnClickListener {


    private TextView tv_relevance;
    private TextView tv_register_quick;

    public static ThirdLoginFragment newInstance() {
        // Required empty public constructor
        Bundle bundle = new Bundle();
        ThirdLoginFragment fragment = new ThirdLoginFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int inflaterRootView() {
        return R.layout.fragment_third_login;
    }

    @Override
    public void initUI() {
        setBackView();
        setTitle("联合登录");
        tv_register_quick = findView(R.id.tv_register_quick);
        tv_relevance = findView(R.id.tv_relevance);

    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {
        tv_relevance.setOnClickListener(this);
        tv_register_quick.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register_quick:
                startFrameActivity(Constants.FRAGMENT_REGISTER);
                break;
            case R.id.tv_relevance:
                startFrameActivity(Constants.FRAGMENT_RELEVANCE_USER);
                break;
        }
    }

    private void startFrameActivity(int keyFragment) {
        Intent intent = new Intent(getActivity(), FrameActivity.class);
        intent.putExtra(KEY_FRAGMENT, keyFragment);
        startActivity(intent);
    }
}
