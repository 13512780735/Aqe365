package com.likeit.aqe365.activity.cart.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.listener.IEditTextChangeListener;
import com.likeit.aqe365.utils.EditTextSizeCheckUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class EditAddressActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ed_recipients_name)
    EditText mEdRecipientsName;
    @BindView(R.id.ed_recipients_phone)
    EditText mEdRecipientsPhone;
    @BindView(R.id.tv_recipients_address)
    TextView mTvRecipientsAddress;
    @BindView(R.id.ll_recipients_address)
    LinearLayout mLlRecipientsAddress;
    @BindView(R.id.ed_recipients_detailed_address)
    EditText mEdRecipientsDetailedAddress;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        flag = getIntent().getExtras().getInt("flag");  //0  新建  1 编辑
        initUI();
    }

    private void initUI() {
        setBackView();
        if (flag == 0) {
            setTitle("新建地址");
        } else if (flag == 1) {
            setTitle("编辑地址");
        }

        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(mTvSave);
        textChangeListener.addAllEditText(mEdRecipientsPhone, mEdRecipientsName, mEdRecipientsDetailedAddress);
        EditTextSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    mTvSave.setBackgroundResource(R.drawable.shape_round_blue_bg_5);
                    mTvSave.setOnClickListener(EditAddressActivity.this);
                } else {
                    mTvSave.setBackgroundResource(R.drawable.shape_round_grey_bg_5);
                    // tv_register.setClickable(false);
                }
            }
        });
    }

    @OnClick({R.id.ll_recipients_address})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ll_recipients_address:
                break;
            case R.id.tv_save:
                break;
        }
    }
}
