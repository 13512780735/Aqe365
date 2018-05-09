package com.likeit.aqe365.activity.login.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.king.base.BaseFragment;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.FrameActivity;
import com.likeit.aqe365.activity.main.MainActivity;
import com.likeit.aqe365.constants.Constants;
import com.king.base.AppManager;
import com.likeit.aqe365.listener.IEditTextChangeListener;
import com.likeit.aqe365.utils.EditTextSizeCheckUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneLoginFragment extends BaseFragment implements View.OnClickListener {


    private TextView tvLogin;
    private TextView tvLoginWechat, tvLoginQQ;
    private EditText et_phone,et_code;

    public static PhoneLoginFragment newInstance() {
        Bundle bundle = new Bundle();
        PhoneLoginFragment fragment = new PhoneLoginFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int inflaterRootView() {
        return R.layout.fragment_phone_login;
    }

    @Override
    public void initUI() {
        setBackView();
        setTitle("手机验证登录");
       et_phone = findView(R.id.phone_login_et_phone);
        et_code = findView(R.id.phone_login_et_code);
        tvLogin = findView(R.id.tv_login);
        tvLoginQQ = findView(R.id.login_qq);
        tvLoginWechat = findView(R.id.login_wechat);
        EditTextSizeCheckUtil.textChangeListener textChangeListener=new EditTextSizeCheckUtil.textChangeListener(tvLogin);
        textChangeListener.addAllEditText(et_phone,et_code);
        EditTextSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    tvLogin.setBackgroundResource(R.drawable.shape_round_blue_bg);
                    tvLogin.setOnClickListener(PhoneLoginFragment.this);
                } else {
                    tvLogin.setBackgroundResource(R.drawable.shape_round_grey_bg);
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {

        tvLoginQQ.setOnClickListener(this);
        tvLoginWechat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                startMainActivity();
                break;
            case R.id.login_qq:
                startFrameActivity(Constants.FRAGMENT_Third_LOGIN);
                break;
            case R.id.login_wechat:
                startFrameActivity(Constants.FRAGMENT_Third_LOGIN);
                break;
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        AppManager.getAppManager().finishAllActivity();
    }

    private void startFrameActivity(int keyFragment) {
        Intent intent = new Intent(getActivity(), FrameActivity.class);
        intent.putExtra(KEY_FRAGMENT, keyFragment);
        startActivity(intent);
    }
}
