package com.likeit.aqe365.activity.main;

import android.support.v4.app.Fragment;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.main.fragment.MainBrandFragment;
import com.likeit.aqe365.activity.main.fragment.MainCartFragment;
import com.likeit.aqe365.activity.main.fragment.MainHomeFragment;
import com.likeit.aqe365.activity.main.fragment.MainPeopleFragment;
import com.likeit.aqe365.activity.main.fragment.MainSortFragment;
import com.likeit.aqe365.view.tablayout.AbstractCommonTabLayout;

import java.util.ArrayList;


public class MainActivity extends AbstractCommonTabLayout {
    private String[] mTitles = {"首页", "分类", "品牌库", "购物车", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.main_tab_home_unselected, R.mipmap.main_tab_sort_unselected,
            R.mipmap.main_tab_brand_unselected, R.mipmap.main_tab_cart_unselected, R.mipmap.main_tab_people_unselected};//选中

    private int[] mIconSelectIds = {
            R.mipmap.main_tab_home_selected, R.mipmap.main_tab_sort_selected,
            R.mipmap.main_tab_brand_selected, R.mipmap.main_tab_cart_selected, R.mipmap.main_tab_people_selected};//未选中

    private ArrayList<Fragment> mFragments = new ArrayList<>();//Fragment 集合
    private String flag;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        flag = getIntent().getExtras().getString("flag");
        if ("1".equals(flag)) {
            setSelectDefaultIndex(3);
        } else {
            setSelectDefaultIndex(0);//设置默认的选项
        }
        setUnReadMsg(3, 1);
        setUnReadMsg(4, 2);
        // setUnReadMsg(2, 5, Color.parseColor("#6D8FB0"));
    }

    /**
     * 标题数组
     **/
    @Override
    protected String[] getTitles() {
        return mTitles;
    }

    /**
     * 选择图标数组
     **/
    @Override
    protected int[] getIconSelectIds() {
        return mIconSelectIds;
    }

    /**
     * 未选择图标数组
     **/
    @Override
    protected int[] getIconUnselectIds() {
        return mIconUnselectIds;
    }

    /**
     * Fragment 集合
     **/
    @Override
    protected ArrayList<Fragment> getFragmentList() {
        mFragments.add(new MainHomeFragment());
        mFragments.add(new MainSortFragment());
        mFragments.add(new MainBrandFragment());
        mFragments.add(new MainCartFragment());
        mFragments.add(new MainPeopleFragment());
        return mFragments;
    }

    /**
     * CommonTabLayout 资源id
     **/
    @Override
    protected int getCommonTabLayout() {
        return R.id.main_CommonTabLayout;
    }

    /**
     * ViewPager 资源id
     **/
    @Override
    protected int getCommonViewPager() {
        return R.id.main_viewpager;
    }


}
