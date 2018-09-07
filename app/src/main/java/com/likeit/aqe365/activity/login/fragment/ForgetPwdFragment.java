package com.likeit.aqe365.activity.login.fragment;


import android.os.Bundle;
import android.os.CountDownTimer;
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

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.login.activity.LoginActivity;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.listener.IEditTextChangeListener;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.EmptyEntity;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.EditTextSizeCheckUtil;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SHAUtils;
import com.likeit.aqe365.utils.SharedPreferencesUtils;
import com.likeit.aqe365.utils.SignUtils;
import com.likeit.aqe365.utils.StringUtil;
import com.likeit.aqe365.utils.ToastUtils;

import rx.Subscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPwdFragment extends BaseFragment implements View.OnClickListener {
    ToggleButton tb_re_pwd;
    EditText et_pwd;
    EditText et_pwd_confirm, et_phone, et_code;
    ToggleButton tb_re_pwd_confirm;
    private TextView tv_confirm;
    private TextView tvSendCode;
    private String mobile;
    TimeCount time = new TimeCount(60000, 1000);
    private String pwd;
    private String pwd_confirm;
    private String code;

    public static ForgetPwdFragment newInstance() {
        Bundle args = new Bundle();
        ForgetPwdFragment fragment = new ForgetPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public void addListeners() {
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
            case R.id.tv_confirm:
                mobile = et_phone.getText().toString().trim();
                pwd = et_pwd.getText().toString().trim();
                pwd_confirm = et_pwd_confirm.getText().toString().trim();
                code = et_code.getText().toString().trim();
                // SMSSDK.submitVerificationCode("86", mobile, code);
                if (StringUtil.isBlank(pwd)) {
                    showProgress("密码不能为空");
                    return;
                }
                if (!pwd_confirm.equals(pwd)) {
                    showProgress("两次密码不一样，请重新输入");
                    return;
                }
                if (StringUtil.isBlank(code)) {
                    showProgress("验证码不能为空");
                    return;
                }
                ChangePwd(pwd);
                LoaddingShow();
                break;
            case R.id.send_code_btn:
                sendCode();
                break;
        }
    }

    private void ChangePwd(final String pwd) {
        RetrofitUtil.getInstance().UserChangePwd(mobile, pwd, code, pwd_confirm, signature, newtime, random, new Subscriber<BaseResponse<EmptyEntity>>() {
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
                LogUtils.d("code-->" + baseResponse.getCode() + "");
                if (baseResponse.code == 200) {
                    SharedPreferencesUtils.put(getActivity(), "phone", mobile);
                    SharedPreferencesUtils.put(getActivity(), "pwd", pwd);
                    getActivity().finish();
                } else {
                    baseResponse.getMsg();
                }
            }
        });
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_forget_pwd;
    }

    @Override
    protected void lazyLoad() {
        initView();
        addListeners();
    }

    private void initView() {
        setBackView();
        setTitle("找回密码");
        tb_re_pwd = findView(R.id.tb_re_pwd);
        et_phone = findView(R.id.forget_pwd_et_phone);
        et_code = findView(R.id.forget_pwd_et_code);
        et_pwd = findView(R.id.forget_pwd_et_pwd);
        tvSendCode = findView(R.id.send_code_btn);
        et_pwd_confirm = findView(R.id.forget_pwd_et_pwd_confirm);
        tb_re_pwd_confirm = findView(R.id.tb_re_pwd_confirm);
        tv_confirm = findView(R.id.tv_confirm);
        tvSendCode.setOnClickListener(this);
        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(tv_confirm);
        textChangeListener.addAllEditText(et_pwd, et_pwd_confirm, et_phone, et_code);
        EditTextSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    tv_confirm.setBackgroundResource(R.drawable.shape_round_blue_bg_5);
                    tv_confirm.setOnClickListener(ForgetPwdFragment.this);
                } else {
                    tv_confirm.setBackgroundResource(R.drawable.shape_round_grey_bg_5);
                }
            }
        });
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
       // String sha = SHAUtils.getSHA(Lappid + Lkey + random + newtime);
        RetrofitUtil.getInstance().getVerifycode(mobile, "sms_forget", signature, newtime, random,
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
