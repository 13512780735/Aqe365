package com.likeit.aqe365.activity.people.activity;

import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.login.fragment.RegisterFragment;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.listener.IEditTextChangeListener;
import com.likeit.aqe365.utils.EditTextSizeCheckUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class BindPhoneActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.bind_et_phone)
    EditText mBindEtPhone;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.bind_et_code)
    EditText mBindEtCode;
    @BindView(R.id.send_code_btn)
    TextView mSendCodeBtn;
    @BindView(R.id.bind_et_pwd)
    EditText mBindEtPwd;
    @BindView(R.id.tb_re_pwd)
    ToggleButton mTbRePwd;
    @BindView(R.id.bind_et_pwd_confirm)
    EditText mBindEtPwdConfirm;
    @BindView(R.id.tb_re_pwd_confirm)
    ToggleButton mTbRePwdConfirm;
    @BindView(R.id.tv_bind)
    TextView mTvBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initUI();
    }

    private void initUI() {
        setBackView();
        setTitle("更换绑定手机号");
        mTbRePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //普通文本框
                    mBindEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //密码框
                    mBindEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mBindEtPwd.postInvalidate();//刷新View
                //将光标置于文字末尾
                CharSequence charSequence = mBindEtPwd.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });
        mTbRePwdConfirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //普通文本框
                    mBindEtPwdConfirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //密码框
                    mBindEtPwdConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mBindEtPwdConfirm.postInvalidate();//刷新View
                //将光标置于文字末尾
                CharSequence charSequence = mBindEtPwdConfirm.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });

        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(mTvBind);
        textChangeListener.addAllEditText(mBindEtPhone, mBindEtCode, mBindEtPwd, mBindEtPwdConfirm);
        EditTextSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    mTvBind.setBackgroundResource(R.drawable.shape_round_blue_bg_5);
                    mTvBind.setOnClickListener(BindPhoneActivity.this);
                } else {
                    mTvBind.setBackgroundResource(R.drawable.shape_round_grey_bg_5);
                    // tv_register.setClickable(false);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
