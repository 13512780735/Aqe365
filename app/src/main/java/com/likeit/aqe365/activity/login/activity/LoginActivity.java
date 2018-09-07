package com.likeit.aqe365.activity.login.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.FrameActivity;
import com.likeit.aqe365.activity.TestActivity;
import com.likeit.aqe365.activity.main.MainActivity;
import com.likeit.aqe365.activity.web.jsinterface.JsInterfaceActivity;
import com.likeit.aqe365.constants.Constants;
import com.likeit.aqe365.listener.IEditTextChangeListener;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.LoginRegisterModel;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.AppManager;
import com.likeit.aqe365.utils.EditTextSizeCheckUtil;
import com.likeit.aqe365.utils.LoaddingDialog;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.MD5Utils;
import com.likeit.aqe365.utils.SHAUtils;
import com.likeit.aqe365.utils.SharedPreferencesUtils;
import com.likeit.aqe365.utils.SignUtils;
import com.likeit.aqe365.utils.StatusBarUtil;
import com.likeit.aqe365.utils.StringUtils;
import com.likeit.aqe365.utils.ToastUtils;


import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

import static com.likeit.aqe365.Interface.BaseInterface.KEY_FRAGMENT;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ToggleButton tb_password;
    EditText et_password;
    private TextView tv_login;
    private EditText et_phone, et_pwd;
    private String phone, pwd;
    private LoaddingDialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppManager.getAppManager().addActivity(this);
        int color = getResources().getColor(R.color.theme_bg);
        mDialog = new LoaddingDialog(this);
        StatusBarUtil.setColor(this, color, 0);
        StatusBarUtil.setLightMode(this);
        ButterKnife.bind(this);
        initUI();
        initView();
        addListeners();
        initData();
    }


    public void initUI() {
        tb_password = findViewById(R.id.tb_re_pwd);
        et_password = findViewById(R.id.login_et_pwd);
        et_phone = findViewById(R.id.login_et_phone);
        et_pwd = findViewById(R.id.login_et_pwd);
        tv_login = findViewById(R.id.tv_login);
        phone = SharedPreferencesUtils.getString(this, "phone");
        pwd = SharedPreferencesUtils.getString(this, "pwd");
        if (!StringUtils.isBlank(phone) && !StringUtils.isBlank(pwd)) {
            tv_login.setBackgroundResource(R.drawable.shape_round_blue_bg_5);
            tv_login.setOnClickListener(LoginActivity.this);
        }

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
                    tv_login.setBackgroundResource(R.drawable.shape_round_blue_bg_5);
                    tv_login.setOnClickListener(LoginActivity.this);
                } else {
                    tv_login.setBackgroundResource(R.drawable.shape_round_grey_bg_5);
                }
            }
        });
    }

    public void initData() {

    }

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
        Bundle bundle = new Bundle();
        bundle.putString("flag", "0");
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(bundle);
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
                Login(phone, pwd);
                break;
        }

    }

    private void Login(final String phone, final String pwd) {
        String Lkey = "uKmy0e45wgh0B3e7";
        String Lappid = "200001";
        String sign = SignUtils.getSign(LoginActivity.this);
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        //ToastUtils.showToast(this, sign);
        mDialog.show();
        RetrofitUtil.getInstance().getUsersLogin(phone, pwd, signature, newtime, random, new Subscriber<BaseResponse<LoginRegisterModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mDialog.dismiss();
                LogUtils.d(e + "");
            }

            @Override
            public void onNext(BaseResponse<LoginRegisterModel> baseResponse) {
                mDialog.dismiss();
                LogUtils.d("code-->" + baseResponse.code);
                if (baseResponse.code == 200) {
                    SharedPreferencesUtils.put(LoginActivity.this, "phone", phone);
                    SharedPreferencesUtils.put(LoginActivity.this, "pwd", pwd);
                    SharedPreferencesUtils.put(LoginActivity.this, "token", baseResponse.getData().getToken());
                    LogUtils.d(baseResponse.getData().getMember().getNickname());
              startMainActivity();
//                    Intent intent = new Intent(LoginActivity.this, TestActivity.class);
//                    startActivity(intent);
                    /**
                     * 跳转网页
                     */
//                    Intent intent = new Intent(LoginActivity.this, JsInterfaceActivity.class);
//                    startActivity(intent);

                } else {
                    Log.d("TAG", baseResponse.getMsg());
                    ToastUtils.showToast(LoginActivity.this, baseResponse.getMsg());
                }

            }
        });
    }

}
