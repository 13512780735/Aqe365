package com.likeit.aqe365.activity.people.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.king.base.BaseFragment;
import com.likeit.aqe365.R;

/**
 * 我的优惠卷
 * A simple {@link Fragment} subclass.
 */
public class CouponFragment extends BaseFragment {

    public static CouponFragment newInstance() {
        Bundle bundle = new Bundle();
        CouponFragment fragment = new CouponFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int inflaterRootView() {
        return R.layout.fragment_coupon;
    }

    @Override
    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_coupon_title));
    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {

    }
}
