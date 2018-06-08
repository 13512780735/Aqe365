package com.likeit.aqe365.activity.sort.goods;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.main.MainActivity;
import com.likeit.aqe365.activity.people.adapter.GoodsIndentTabAdapter;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsDetailsActivity extends BaseActivity {

    @BindView(R.id.back_view)
    LinearLayout mBackView;
    @BindView(R.id.indent_TabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.right_view)
    LinearLayout mRightView;
    @BindView(R.id.viewpager)
    NoScrollViewPager mViewpager;
    @BindView(R.id.tv_attention)
    TextView mTvAttention;
    @BindView(R.id.tv_shop)
    TextView mTvShop;
    @BindView(R.id.tv_cart)
    TextView mTvCart;
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;

    private ArrayList<String> mTitles;
    private ShopDialogFragment dialog;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        ButterKnife.bind(this);
        mTitles = new ArrayList<>(Arrays.asList("商品", "详情", "参数", "评价"));
        initUI();
    }

    private void initUI() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewpager);
        List<Fragment> mfragments = new ArrayList<Fragment>();
        mfragments.add(new GoodsDetails01Fragment());
        mfragments.add(new GoodsDetails02Fragment());
        mfragments.add(new GoodsDetails03Fragment());
        mfragments.add(new GoodsDetails03Fragment());
        mViewpager.setAdapter(new GoodsIndentTabAdapter(getSupportFragmentManager(), mfragments, mTitles));
        mViewpager.setCurrentItem(0);
    }

    @OnClick({R.id.back_view, R.id.right_view, R.id.tv_attention, R.id.tv_shop, R.id.tv_cart, R.id.tv_add, R.id.tv_buy})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back_view:
                onBackPressed();
                break;
            case R.id.right_view:
                break;

            case R.id.tv_attention:
                break;
            case R.id.tv_shop:
                break;
            case R.id.tv_cart:
                bundle=new Bundle();
                bundle.putString("flag","1");
                toActivity(MainActivity.class,bundle);
                break;
            case R.id.tv_add:
                dialog = new ShopDialogFragment();
                bundle=new Bundle();
                bundle.putString("keys","1");
                // bundle.putString("addressId",addressId);
                dialog.setArguments(bundle);
                dialog.show(this.getSupportFragmentManager(), "MakeFriendsFragment");
                break;
            case R.id.tv_buy:
                dialog = new ShopDialogFragment();
                bundle=new Bundle();
                bundle.putString("keys","2");
                // bundle.putString("addressId",addressId);
                dialog.setArguments(bundle);
                dialog.show(this.getSupportFragmentManager(), "MakeFriendsFragment");
                break;
        }
    }
}
