package com.likeit.aqe365.activity.cart.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.cart.adapter.CartShopListAdatper;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.network.model.CaseEntity;
import com.likeit.aqe365.wxapi.PayActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    private List<CaseEntity> data;
    private CartShopListAdatper mAdapter;


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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        initAdapter();
    }

    public void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CaseEntity caseEntity = new CaseEntity();
            caseEntity.setUrl(i + "");
            data.add(caseEntity);
        }
    }

    private void initAdapter() {
        mAdapter = new CartShopListAdatper(R.layout.layout_cart_shoplist_items, data);
        mRecyclerView.setAdapter(mAdapter);
        //  mCurrentCounter = mAdapter.getData().size();
    }

    @OnClick({R.id.ll_address_default, R.id.rl_address_default, R.id.tv_go_to_pay})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ll_address_default:
                toActivity(SelectAddressActivity.class);
                break;
            case R.id.rl_address_default:
                toActivity(SelectAddressActivity.class);
                break;
            case R.id.tv_go_to_pay:
                toActivity(PayActivity.class);
                break;
        }
    }
}
