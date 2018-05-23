package com.likeit.aqe365.activity.people.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.people.adapter.GoodsIndentTabAdapter;
import com.likeit.aqe365.activity.people.fragment.Coupon01Fragment;
import com.likeit.aqe365.activity.people.fragment.indent.AllIndentFragment;
import com.likeit.aqe365.activity.people.fragment.indent.Indent01Fragment;
import com.likeit.aqe365.activity.people.fragment.indent.Indent02Fragment;
import com.likeit.aqe365.activity.people.fragment.indent.Indent03Fragment;
import com.likeit.aqe365.activity.people.fragment.indent.Indent04Fragment;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 我的优惠卷
 * A simple {@link Fragment} subclass.
 */
public class MyCouponActivity extends BaseActivity {

    private ArrayList<String> mTitles;
    private TabLayout mTabLayout;
    private NoScrollViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);
        mTitles = new ArrayList<>(Arrays.asList("未使用", "已使用", "已过期"));
        initUI();
        addListeners();
        initData();
    }

    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_coupon_title));
        mTabLayout = findViewById(R.id.indent_TabLayout);
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
        List<Fragment> mfragments = new ArrayList<Fragment>();
        mfragments.add(new Coupon01Fragment());
        mfragments.add(new Coupon01Fragment());
        mfragments.add(new Coupon01Fragment());
        mViewPager.setAdapter(new GoodsIndentTabAdapter(getSupportFragmentManager(), mfragments, mTitles));
        mViewPager.setCurrentItem(0);
    }

    public void initData() {

    }

    public void addListeners() {

    }


}
