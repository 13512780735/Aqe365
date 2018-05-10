package com.likeit.aqe365.activity.people;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.people.fragment.indent.AllIndentFragment;
import com.likeit.aqe365.activity.people.fragment.indent.Indent01Fragment;
import com.likeit.aqe365.adapter.GoodsIndentAdapter;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodsIndentActivity extends BaseActivity {
    private List<String> mTitles;
    private TabLayout mTabLayout;
    private NoScrollViewPager mViewPager;
    private int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_indent);
        mTitles = new ArrayList<>(Arrays.asList("全部", "待付款", "待发货", "待收货", "已完成"));
//        status = getIntent().getExtras().getInt("status");
//        Log.e("TAG", status + "");
        initView();
        initData();
    }

    private void initView() {
        mTabLayout = findViewById(R.id.indent_TabLayout);
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.indent_TabLayout);
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
        List<Fragment> mfragments = new ArrayList<Fragment>();
        mfragments.add(new Indent01Fragment());
        mfragments.add(new AllIndentFragment());
        mfragments.add(new Indent01Fragment());
        mfragments.add(new AllIndentFragment());
        mfragments.add(new Indent01Fragment());
        mViewPager.setAdapter(new GoodsIndentAdapter(getSupportFragmentManager(), mfragments, mTitles));
        mViewPager.setCurrentItem(0);
    }


    public void initData() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }


//        mViewPager.setCurrentItem(status);
}
