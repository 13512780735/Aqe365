package com.likeit.aqe365.activity.people.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.king.base.BaseFragment;
import com.likeit.aqe365.R;

/**
 * 我的积分
 * A simple {@link Fragment} subclass.
 */
public class IntegralFragment extends BaseFragment {


    public static IntegralFragment newInstance() {
        Bundle bundle = new Bundle();
        IntegralFragment fragment = new IntegralFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int inflaterRootView() {
        return R.layout.fragment_integral;
    }

    @Override
    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_integral_title));
    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {

    }
}
