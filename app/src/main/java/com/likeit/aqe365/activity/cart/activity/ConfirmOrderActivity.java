package com.likeit.aqe365.activity.cart.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseActivity;

import butterknife.BindView;

public class ConfirmOrderActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address_default)
    LinearLayout mLlAddressDefault;//有地址显示
    @BindView(R.id.rl_address_default)
    RelativeLayout mRlAddressDefault;//无地址时
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_go_to_pay)
    TextView mTvGoToPay;
    @BindView(R.id.ed_message)
    EditText mEdMessage;
    @BindView(R.id.tv_invoice)
    TextView mTvInvoice;
    @BindView(R.id.iv_coupon)
    ImageView mIvCoupon;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @BindView(R.id.tv_expressage)
    TextView mTvExpressage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initUI();
    }

    private void initUI() {
        setBackView();
        setTitle("确认订单");
        mTvName.setText("王晓萌");
        mTvPhone.setText("13800138000");
        mTvAddress.setText("广东省广州市XXXX");
        mTvPrice.setText("￥ " + 1000.00);
        mTvInvoice.setText("发票信息    " + "不开发票");
        mTvTotalPrice.setText("¥ " + 1000.00);
        mTvExpressage.setText("¥ " + 0.00);
    }
}
