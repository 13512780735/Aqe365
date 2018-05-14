package com.likeit.aqe365.activity.people.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.adapter.GoodAllIndentAdapter;
import com.likeit.aqe365.adapter.IndentDatailsShopAdapter;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.network.model.CaseEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class IndentDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_indent_cancel)
    TextView mTvIndentCancel;
    @BindView(R.id.tv_after_sale)
    TextView mTvAfterSale;
    @BindView(R.id.ll_indent_status)
    LinearLayout mLlIndentStatus;
    @BindView(R.id.tv_indent_status)
    TextView mTvIndentStatus;
    @BindView(R.id.tv_indent_price)
    TextView mTvIndentPrice;
    //    @BindView(R.id.tv_indent_name)
//    TextView mTvIndentName;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_shop_price)
    TextView mTvShopPrice;
    @BindView(R.id.tv_price_carriage)
    TextView mTvPriceCarriage;
    @BindView(R.id.tv_price_total)
    TextView mTvPriceTotal;
    @BindView(R.id.tv_indent_number)
    TextView mTvIndentNumber;
    @BindView(R.id.tv_create_time)
    TextView mTvCreateTime;
    @BindView(R.id.tv_pay_time)
    TextView mTvPayTime;
    @BindView(R.id.tv_delivery_time)
    TextView mTvDeliveryTime;
    @BindView(R.id.tv_finish_time)
    TextView mTvFinishTime;
    @BindView(R.id.tv_recipients_name)
    TextView mTvRecipientsName;
    @BindView(R.id.tv_recipients_phone)
    TextView mTvRecipientsPhone;
    @BindView(R.id.tv_recipients_address)
    TextView mTvRecipientsAddress;
    private int status;
    private ArrayList<CaseEntity> data;
    private IndentDatailsShopAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent_details);
        status = getIntent().getExtras().getInt("status");
        initUI();
    }

    private void initUI() {
        setBackView();
        setTitle("订单详情");
        mTvIndentStatus.setText("买家已付款");
        mTvIndentPrice.setText("订单金额：¥ " + "1000.00");
        mTvRecipientsName.setText("联系人：" + "王晓萌");
        mTvRecipientsPhone.setText("联系电话：" + "13800138000");
        mTvRecipientsAddress.setText("广东省广州市XXX");
        mTvShopPrice.setText("¥ " + "1000.00");
        mTvPriceCarriage.setText("¥ " + "0.00");
        mTvPriceTotal.setText("¥ " + "1000.00");
        mTvIndentNumber.setText("订单编号  " + "SH20180124064250464768");
        mTvCreateTime.setText("创建时间  " + "2018-01-25 18:56:32");
        mTvPayTime.setText("支付时间  " + "2018-01-25 18:56:32");
        mTvDeliveryTime.setText("发货时间  " + "2018-01-28 18:56:32");
        mTvFinishTime.setText("完成时间  " + "2018-01-28 18:56:32");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new IndentDatailsShopAdapter(R.layout.layout_indent_details_listview_items, data);
        //mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CaseEntity caseEntity = new CaseEntity();
            caseEntity.setUrl(i + "");
            data.add(caseEntity);
        }
    }

    @OnClick({R.id.tv_indent_cancel, R.id.tv_after_sale, R.id.ll_indent_status})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_indent_cancel:
                break;
            case R.id.tv_after_sale:
                toActivity(AfterSaleActivity.class);
                break;
            case R.id.ll_indent_status:
                break;
        }
    }
}
