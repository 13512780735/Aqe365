package com.likeit.aqe365.activity.login.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.FrameActivity;
import com.likeit.aqe365.activity.login.activity.LoginActivity;
import com.likeit.aqe365.activity.main.MainActivity;
import com.likeit.aqe365.activity.web.jsinterface.JsInterfaceActivity;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.constants.Constants;
import com.likeit.aqe365.listener.IEditTextChangeListener;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.EmptyEntity;
import com.likeit.aqe365.network.model.LoginRegisterModel;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.AppManager;
import com.likeit.aqe365.utils.EditTextSizeCheckUtil;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SharedPreferencesUtils;
import com.likeit.aqe365.utils.SignUtils;
import com.likeit.aqe365.utils.StringUtil;
import com.likeit.aqe365.utils.ToastUtils;

import rx.Subscriber;

import static com.likeit.aqe365.Interface.BaseInterface.KEY_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment implements View.OnClickListener {
    ToggleButton tb_re_pwd;
    EditText et_pwd;
    EditText et_pwd_confirm, et_phone, et_code, et_clinic, et_name;
    ToggleButton tb_re_pwd_confirm;
    CheckBox checkBox;
    private TextView protocol_tv01;
    private TextView tv_register;
    private TextView tvSendCode;
    private String mobile;
    TimeCount time = new TimeCount(60000, 1000);
    private String password;
    private String code;
    private String pwd_confirm;


    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public void initUI() {
        setBackView();
        setTitle("立即注册");
        tb_re_pwd = findView(R.id.tb_re_pwd);
        et_phone = findView(R.id.register_et_phone);
        et_code = findView(R.id.register_et_code);
        tvSendCode = findView(R.id.send_code_btn);
        et_clinic = findView(R.id.register_et_clinic);
        et_name = findView(R.id.register_et_name);
        et_pwd = findView(R.id.register_et_pwd);
        et_pwd_confirm = findView(R.id.register_et_pwd_confirm);
        tb_re_pwd_confirm = findView(R.id.tb_re_pwd_confirm);
        protocol_tv01 = findView(R.id.protocol_tv01);//注册协议入口
        tv_register = findView(R.id.tv_register);//注册进入主页入口
        checkBox = findView(R.id.checkbox01);
        tvSendCode.setOnClickListener(this);
        if (!checkBox.isChecked()) {
            showProgress("請同意條款");
            return;
        }
        //, et_clinic, et_name
        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(tv_register);
        textChangeListener.addAllEditText(et_pwd, et_pwd_confirm, et_phone, et_code);
        EditTextSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    tv_register.setBackgroundResource(R.drawable.shape_round_blue_bg_5);
                    tv_register.setOnClickListener(RegisterFragment.this);
                } else {
                    tv_register.setBackgroundResource(R.drawable.shape_round_grey_bg_5);
                    // tv_register.setClickable(false);
                }
            }
        });
    }


    public void addListeners() {
        protocol_tv01.setOnClickListener(this);

        tb_re_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        });
        tb_re_pwd_confirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //普通文本框
                    et_pwd_confirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //密码框
                    et_pwd_confirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                et_pwd_confirm.postInvalidate();//刷新View
                //将光标置于文字末尾
                CharSequence charSequence = et_pwd_confirm.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.protocol_tv01:
                startFrameActivity(Constants.FRAGMENT_REGISTER_PROTOCOL);


                break;
            case R.id.tv_register:
                //startMainActivity();\
                mobile = et_phone.getText().toString().trim();
                password = et_pwd.getText().toString().trim();
                pwd_confirm = et_pwd_confirm.getText().toString().trim();
                code = et_code.getText().toString().trim();
                // SMSSDK.submitVerificationCode("86", mobile, code);
                if (StringUtil.isBlank(password)) {
                    showProgress("密码不能为空");
                    return;
                }
                if (!pwd_confirm.equals(password)) {
                    showProgress("两次密码不一样，请重新输入");
                    return;
                }
                if (StringUtil.isBlank(code)) {
                    showProgress("验证码不能为空");
                    return;
                }
                if (!checkBox.isChecked()) {
                    showProgress("請同意條款");
                    return;
                }
                Register(password);
                LoaddingShow();
                break;
            case R.id.send_code_btn:
                sendCode();
                break;
        }
    }

    private void Register(final String password) {
        RetrofitUtil.getInstance().getUsersRegister(mobile, password, Integer.valueOf(code), signature, newtime, random, new Subscriber<BaseResponse<LoginRegisterModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LoaddingDismiss();
                Log.e(TAG, "rx失败:" + e.getMessage());
                showProgress("数据加载失败！");
            }

            @Override
            public void onNext(BaseResponse<LoginRegisterModel> baseResponse) {
                // LoaddingDismiss();

                if (baseResponse.code == 200) {
                    SharedPreferencesUtils.put(getActivity(), "phone", mobile);
                    SharedPreferencesUtils.put(getActivity(), "pwd", password);
                    SharedPreferencesUtils.put(getActivity(), "token", baseResponse.getData().getToken());
                    // LogUtils.d(baseResponse.getData().getMember().getNickname());
                    //startMainActivity();
                    Intent intent = new Intent(getActivity(), JsInterfaceActivity.class);
                    startActivity(intent);

                } else {
                    LoaddingDismiss();
                    showProgress(baseResponse.getMsg());
                }
            }
        });

    }

    private void startMainActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("flag", "0");
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtras(bundle);
        AppManager.getAppManager().finishAllActivity();
    }

    private void startFrameActivity(int keyFragment) {
        Intent intent = new Intent(getActivity(), FrameActivity.class);
        intent.putExtra(KEY_FRAGMENT, keyFragment);
        startActivity(intent);

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_register;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
    }

    private void sendCode() {
        mobile = et_phone.getText().toString().trim();
        if (StringUtil.isBlank(mobile)) {
            ToastUtils.showToast(getActivity(), "手机号不能为空");
            return;
        }
        if (!(StringUtil.isCellPhone(mobile))) {
            ToastUtils.showToast(getActivity(), "请输入正确的手机号码");
            return;
        } else {
            // SMSSDK.getVerificationCode("86", mobile);
            VerificationCode();
            time.start();
            LoaddingShow();
        }
    }
    String signature, newtime, random;
    private void VerificationCode() {
        String Lkey = "uKmy0e45wgh0B3e7";
        String Lappid = "200001";
        String sign = SignUtils.getSign(getActivity());
        String signs[] = sign.split("##");
        signature = signs[0];
        newtime = signs[1];
        random = signs[2];
        RetrofitUtil.getInstance().getVerifycode(mobile, "sms_reg",signature, newtime, random,
                new Subscriber<BaseResponse<EmptyEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LoaddingDismiss();
                    }

                    @Override
                    public void onNext(BaseResponse<EmptyEntity> baseResponse) {
                        LoaddingDismiss();
                        if (baseResponse.code == 200) {
                            showProgress(baseResponse.getMsg());
                        } else {
                            showProgress(baseResponse.getMsg());
                        }
                    }
                });
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            tvSendCode.setText("获取验证码");
            tvSendCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            tvSendCode.setClickable(false);//防止重复点击
            tvSendCode.setText(millisUntilFinished / 1000 + "s");
        }
    }
}
