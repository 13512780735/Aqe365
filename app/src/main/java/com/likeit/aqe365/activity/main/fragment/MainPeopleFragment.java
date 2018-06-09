package com.likeit.aqe365.activity.main.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.FrameActivity;
import com.likeit.aqe365.activity.cart.activity.SelectAddressActivity;
import com.likeit.aqe365.activity.login.activity.LoginActivity;
import com.likeit.aqe365.activity.people.activity.AfterSaleActivity;
import com.likeit.aqe365.activity.people.activity.FeedbackActivity;
import com.likeit.aqe365.activity.people.activity.GoodsIndentActivity;
import com.likeit.aqe365.activity.people.activity.UserInfoActivity;
import com.likeit.aqe365.activity.people.activity.MyCouponActivity;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.constants.Constants;
import com.likeit.aqe365.utils.AppManager;
import com.likeit.aqe365.view.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import static com.likeit.aqe365.Interface.BaseInterface.KEY_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPeopleFragment extends BaseFragment implements View.OnClickListener {


    private MyGridView mGridView;
    private int[] icon = {R.mipmap.icon_people_my_forum, R.mipmap.icon_people_integral_mall,
            R.mipmap.icon_people_my_invitation, R.mipmap.icon_people_qr_code, R.mipmap.icon_people_invoice_service, R.mipmap.icon_people_statistics, R.mipmap.icon_people_feedback, R.mipmap.icon_people_regular_purchase};
    private String[] iconName = {"我的社区", "积分商城", "我的邀请", "邀请二维码", "发票服务", "报表统计", "意见反馈", "常购商品"};
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;
    private RelativeLayout mRlGoodsAttention, mRlShopAttention, mRlBrandAttention, mRlFootprint, mRlAllOrders;
    private TextView mTvChange, mTvIntegral, mTvCoupon, mTvObligation, mTvShipments, mTvReceiving;
    private ImageView mIvSetting;
    private TextView tv_logout, tv_edit_pwd;
    private List<Badge> badges;
    private int status;
    private ScrollView mScrollview;
    private TextView mTvAfterSale;


    public void initUI() {
        badges = new ArrayList<>();
        mScrollview = findView(R.id.main_people_scrollview);
        mGridView = findView(R.id.MyGridView);
        mRlGoodsAttention = findView(R.id.rl_goodsAttention);
        mRlShopAttention = findView(R.id.rl_tv_shopAttention);
        mRlBrandAttention = findView(R.id.rl_brandAttention);
        mRlFootprint = findView(R.id.rl_footprint);
        mTvChange = findView(R.id.tv_change);
        mRlAllOrders = findView(R.id.rl_all_orders);
        mTvObligation = findView(R.id.tv_obligation);
        mTvAfterSale = findView(R.id.tv_after_sale);
        mTvShipments = findView(R.id.tv_shipments);
        mTvReceiving = findView(R.id.tv_Receiving);
        mTvObligation = findView(R.id.tv_obligation);
        mTvCoupon = findView(R.id.tv_coupon);
        mTvIntegral = findView(R.id.tv_integral);
        mIvSetting = findView(R.id.iv_setting);
        tv_logout = findView(R.id.tv_logout);
        tv_edit_pwd = findView(R.id.tv_edit_pwd);
        badges.add(new QBadgeView(getActivity()).bindTarget(mTvObligation).setBadgeNumber(1).setBadgeBackgroundColor(Color.parseColor("#ff0000")).setShowShadow(false));
        badges.add(new QBadgeView(getActivity()).bindTarget(mTvShipments).setBadgeNumber(2).setBadgeBackgroundColor(Color.parseColor("#ff0000")).setShowShadow(false));
        badges.add(new QBadgeView(getActivity()).bindTarget(mTvReceiving).setBadgeNumber(5).setBadgeBackgroundColor(Color.parseColor("#ff0000")).setShowShadow(false));
    }

    public void initData() {
        mScrollview.smoothScrollTo(0, 20);
        mScrollview.setFocusable(true);
        dataList = new ArrayList<>();
        getData();
        String[] from = {"img", "name"};
        final int[] to = {R.id.iv_people_avatar, R.id.tv_people_name};
        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.layout_people_gridview_items, from, to);
        //配置适配器
        mGridView.setAdapter(simpleAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: //我的社区
                        break;
                    case 1:// 积分商城
                        break;
                    case 2://我的邀请
                        break;
                    case 3://邀请二维码
                        break;
                    case 4://发票服务
                        break;
                    case 5://报表统计
                        startFrameActivity(Constants.FRAGMENT_PEOPLE_STATISTICS);
                        break;
                    case 6://意见反馈
                        toActivity(FeedbackActivity.class);
                        break;
                    case 7://常购商品
                        break;
                }
            }
        });
    }

    private void startFrameActivity(int keyFragment) {
        Intent intent = new Intent(getActivity(), FrameActivity.class);
        intent.putExtra(KEY_FRAGMENT, keyFragment);
        startActivity(intent);
    }

    public void addListeners() {
        mRlGoodsAttention.setOnClickListener(this);
        mRlShopAttention.setOnClickListener(this);
        mRlBrandAttention.setOnClickListener(this);
        mRlFootprint.setOnClickListener(this);
        mTvChange.setOnClickListener(this);
        mTvCoupon.setOnClickListener(this);
        mTvIntegral.setOnClickListener(this);
        mTvAfterSale.setOnClickListener(this);
        mIvSetting.setOnClickListener(this);
        tv_logout.setOnClickListener(this);
        tv_edit_pwd.setOnClickListener(this);
        mTvReceiving.setOnClickListener(this);
        mTvObligation.setOnClickListener(this);
        mTvShipments.setOnClickListener(this);
        mRlAllOrders.setOnClickListener(this);
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("name", iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setting://我的设置
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.rl_goodsAttention://商品关注
                startFrameActivity(Constants.FRAGMENT_PEOPLE_GOODS_ATTENTION);
                break;
            case R.id.rl_tv_shopAttention://店铺关注
                startFrameActivity(Constants.FRAGMENT_PEOPLE_SHOP_ATTENTION);
                break;
            case R.id.rl_brandAttention://品牌关注
                startFrameActivity(Constants.FRAGMENT_PEOPLE_BRAND_ATTENTION);
                break;
            case R.id.rl_all_orders://全部订单
                status = 0;
                startIndentActivity(status);
                break;
            case R.id.tv_obligation://待付款
                status = 1;
                startIndentActivity(status);
                break;
            case R.id.tv_shipments://待发货
                status = 2;
                startIndentActivity(status);
                break;
            case R.id.tv_Receiving://待收货
                status = 3;
                startIndentActivity(status);
                break;
            case R.id.tv_after_sale://售后
                toActivity(AfterSaleActivity.class);
               // toActivity(SelectAddressActivity.class);
                break;
            case R.id.rl_footprint://我的足迹
                startFrameActivity(Constants.FRAGMENT_PEOPLE_FOOTPRINT);
                break;
            case R.id.tv_change://我的零钱
                startFrameActivity(Constants.FRAGMENT_PEOPLE_CHANGE);
                break;
            case R.id.tv_coupon://我的优惠卷
              //  startFrameActivity(Constants.FRAGMENT_PEOPLE_COUPON);
                toActivity(MyCouponActivity.class);
                break;
            case R.id.tv_integral://我的积分
                startFrameActivity(Constants.FRAGMENT_PEOPLE_INTEGRAL);
                break;
            case R.id.tv_edit_pwd://修改密码
                 startFrameActivity(Constants.FRAGMENT_PEOPLE_INTEGRAL);
                break;
            case R.id.tv_logout://退出登录
                startActivity(new Intent(getActivity(), LoginActivity.class));
                AppManager.getAppManager().finishAllActivity();
                break;
        }
    }

    private void startIndentActivity(int status) {
        Bundle bundle = new Bundle();
        bundle.putInt("status", status);
        Intent intent = new Intent(getActivity(), GoodsIndentActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_people;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
        initData();
    }
}
