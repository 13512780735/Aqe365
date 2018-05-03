package com.likeit.aqe365.activity.people;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.king.base.BaseFragment;
import com.likeit.aqe365.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleStatisticsFragment extends BaseFragment {


    public static PeopleStatisticsFragment newInstance() {
        Bundle bundle = new Bundle();
        PeopleStatisticsFragment fragment = new PeopleStatisticsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int inflaterRootView() {
        return R.layout.fragment_people_statistics;
    }

    @Override
    public void initUI() {
        /**
         * 布局文件152254235435
         */
        setBackView();
        setTitle("报表统计");

    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {

    }
}
