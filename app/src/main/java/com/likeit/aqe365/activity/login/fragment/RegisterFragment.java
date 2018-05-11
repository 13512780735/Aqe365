package com.likeit.aqe365.activity.login.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.FrameActivity;
import com.likeit.aqe365.activity.main.MainActivity;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.constants.Constants;
import com.likeit.aqe365.listener.IEditTextChangeListener;
import com.likeit.aqe365.utils.AppManager;
import com.likeit.aqe365.utils.EditTextSizeCheckUtil;

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
        et_clinic = findView(R.id.register_et_clinic);
        et_name = findView(R.id.register_et_name);
        et_pwd = findView(R.id.register_et_pwd);
        et_pwd_confirm = findView(R.id.register_et_pwd_confirm);
        tb_re_pwd_confirm = findView(R.id.tb_re_pwd_confirm);
        protocol_tv01 = findView(R.id.protocol_tv01);//注册协议入口
        tv_register = findView(R.id.tv_register);//注册进入主页入口
        checkBox = findView(R.id.checkbox01);
        if (!checkBox.isChecked()) {
            showProgress("請同意條款");
            return;
        }
        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(tv_register);
        textChangeListener.addAllEditText(et_pwd, et_pwd_confirm, et_phone, et_code,et_clinic,et_name);
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
    protected int setContentView() {
        return R.layout.fragment_register;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
    }
}
