package com.likeit.aqe365.activity.people.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;

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

    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_coupon_title));
    }

    public void initData() {

    }

    public void addListeners() {

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
        initData();
    }
}
