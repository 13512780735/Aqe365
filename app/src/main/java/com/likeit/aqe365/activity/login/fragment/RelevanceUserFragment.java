package com.likeit.aqe365.activity.login.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.king.base.BaseFragment;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.FrameActivity;
import com.likeit.aqe365.activity.main.MainActivity;
import com.likeit.aqe365.constants.Constants;
import com.king.base.AppManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class RelevanceUserFragment extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    private TextView tv_relevance_forget_pwd;
    private TextView tv_relevance;
    private ToggleButton tb_re_pwd;
    private EditText et_pwd;

    public static RelevanceUserFragment newInstance() {
        Bundle bundle = new Bundle();
        RelevanceUserFragment fragment = new RelevanceUserFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int inflaterRootView() {
        return R.layout.fragment_relevance_user;
    }

    @Override
    public void initUI() {
        setBackView();
        setTitle("关联帐号");
        tv_relevance_forget_pwd = findView(R.id.tv_relevance_forget_pwd);
        tv_relevance = findView(R.id.tv_relevance);
        tb_re_pwd = findView(R.id.tb_re_pwd);
        et_pwd = findView(R.id.RelevanceUser_et_pwd);
    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {
        tv_relevance_forget_pwd.setOnClickListener(this);
        tv_relevance.setOnClickListener(this);
        tb_re_pwd.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_relevance_forget_pwd:
                startFrameActivity(Constants.FRAGMENT_FORGET_PWD);
                break;
            case R.id.tv_relevance:
                startMainActivity();
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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            //普通文本框
            et_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            //密码框
            et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        et_pwd.postInvalidate();//刷新View
        //将光标置于文字末尾
        CharSequence charSequence = et_pwd.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }
}
