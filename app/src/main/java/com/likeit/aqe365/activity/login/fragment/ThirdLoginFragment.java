package com.likeit.aqe365.activity.login.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.FrameActivity;
import com.likeit.aqe365.activity.login.activity.LoginActivity;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.constants.Constants;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SharedPreferencesUtils;
import com.likeit.aqe365.view.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import static com.likeit.aqe365.Interface.BaseInterface.KEY_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdLoginFragment extends BaseFragment implements View.OnClickListener {


    private TextView tv_relevance;
    private TextView tv_register_quick;
    private CircleImageView iv_avatar;
    private String avatarUrl;

    public static ThirdLoginFragment newInstance() {
        // Required empty public constructor
        Bundle bundle = new Bundle();
        ThirdLoginFragment fragment = new ThirdLoginFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//    }

    public void initUI() {
        setBackView();
        setTitle("联合登录");
        tv_register_quick = findView(R.id.tv_register_quick);
        tv_relevance = findView(R.id.tv_relevance);
        iv_avatar = findView(R.id.iv_avatar);
        LogUtils.d("avatarUrl-->"+avatarUrl);
        ImageLoader.getInstance().displayImage(avatarUrl, iv_avatar);
    }

    public void initData() {

    }

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

    @Override
    protected int setContentView() {
        return R.layout.fragment_third_login;
    }

    @Override
    protected void lazyLoad() {
        avatarUrl = SharedPreferencesUtils.getString(getActivity(), "avatarUrl");
        initUI();
        addListeners();
        initData();
    }
}
