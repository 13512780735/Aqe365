package com.likeit.aqe365.activity.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.famabb.vtp.ViewTabPager;
import com.king.base.AppManager;
import com.king.base.StatusBarUtil;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.main.fragment.MainBrandFragment;
import com.likeit.aqe365.activity.main.fragment.MainCartFragment;
import com.likeit.aqe365.activity.main.fragment.MainHomeFragment;
import com.likeit.aqe365.activity.main.fragment.MainPeopleFragment;
import com.likeit.aqe365.activity.main.fragment.MainSortFragment;

import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    private ViewTabPager mTabPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.transparencyBar(this); //设置状态栏全透明
        StatusBarUtil.StatusBarLightMode(this); //设置白底黑字
        setContentView(R.layout.activity_main);
        AppManager.getAppManager().addActivity(this);

        mTabPager = findViewById(R.id.view_tab_pager);
        initTabPager();
    }

    private void initTabPager() {
        mTabPager.addItem(new MainHomeFragment(), getString(R.string.app_m_home), R.drawable.item_main_home_selector);
        mTabPager.addItem(new MainSortFragment(), getString(R.string.app_m_sort), R.drawable.item_main_sort_selector);
        mTabPager.addItem(new MainBrandFragment(), getString(R.string.app_m_brand), R.drawable.item_main_brand_selector);
        mTabPager.addItem(new MainCartFragment(), getString(R.string.app_m_cart), R.drawable.item_main_cart_selector);
        mTabPager.addItem(new MainPeopleFragment(), getString(R.string.app_m_people), R.drawable.item_main_people_selector);

        // mTabPager.setMsgResId(R.drawable.icon_f);
        mTabPager.setLineBackground(R.color.line_title);
        mTabPager.setTabLayoutBgColor(R.color.color_w);
        mTabPager.setFontColor(R.color.home_tab_unselect, R.color.home_tab_select);

        mTabPager.setFontDipSize(10);

        mTabPager.notifyViewChanger();
        mTabPager.setMsgState(1, true);
    }

    @OnClick(R.id.view_tab_pager)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.view_tab_pager:
                break;
        }
    }
}
