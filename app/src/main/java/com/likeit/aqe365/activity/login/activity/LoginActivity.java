package com.likeit.aqe365.activity.login.activity;


import android.content.Intent;
import android.graphics.Color;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.king.base.BaseActivity;
import com.king.base.util.SharedPreferencesUtils;
import com.king.base.util.StringUtils;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.FrameActivity;
import com.likeit.aqe365.activity.main.MainActivity;
import com.likeit.aqe365.constants.Constants;
import com.king.base.AppManager;
import com.likeit.aqe365.listener.IEditTextChangeListener;
import com.likeit.aqe365.utils.EditTextSizeCheckUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    ToggleButton tb_password;
    EditText et_password;
    private TextView tv_login;
    private EditText et_phone, et_pwd;
    private String phone, pwd;

    @Override
    public void initUI() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        tb_password = findViewById(R.id.tb_re_pwd);
        et_password = findViewById(R.id.login_et_pwd);
        et_phone = findViewById(R.id.login_et_phone);
        et_pwd = findViewById(R.id.login_et_pwd);
        tv_login = findViewById(R.id.tv_login);
        phone = SharedPreferencesUtils.getString(this, "phone");
        pwd = SharedPreferencesUtils.getString(this, "pwd");
        if(!StringUtils.isBlank(phone)&&!StringUtils.isBlank(pwd)){
            tv_login.setBackgroundResource(R.drawable.shape_round_blue_bg);
            tv_login.setOnClickListener(LoginActivity.this);
        }
        initView();

    }

    private void initView() {
        et_phone.setText(phone);
        et_pwd.setText(pwd);
        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(tv_login);
        textChangeListener.addAllEditText(et_phone, et_pwd);
        EditTextSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    tv_login.setBackgroundResource(R.drawable.shape_round_blue_bg);
                    tv_login.setOnClickListener(LoginActivity.this);
                } else {
                    tv_login.setBackgroundResource(R.drawable.shape_round_grey_bg);
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {
        tb_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //普通文本框
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //密码框
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                et_password.postInvalidate();//刷新View
                //将光标置于文字末尾
                CharSequence charSequence = et_password.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });
    }

    @OnClick({R.id.tv_phone_login, R.id.tv_forget_pwd, R.id.tv_register, R.id.login_qq, R.id.login_wechat})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_phone_login:
                startFrameActivity(Constants.FRAGMENT_PHONE_LOGIN);
                break;
            case R.id.tv_forget_pwd:
                startFrameActivity(Constants.FRAGMENT_FORGET_PWD);
                break;

            case R.id.tv_register:
                startFrameActivity(Constants.FRAGMENT_REGISTER);
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        AppManager.getAppManager().finishAllActivity();
    }

    private void startFrameActivity(int keyFragment) {
        Intent intent = new Intent(this, FrameActivity.class);
        intent.putExtra(KEY_FRAGMENT, keyFragment);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                phone = et_phone.getText().toString().trim();
                pwd = et_pwd.getText().toString().trim();
                SharedPreferencesUtils.put(this, "phone", phone);
                SharedPreferencesUtils.put(this, "pwd", pwd);
                startMainActivity();
                break;
        }

    }
}
