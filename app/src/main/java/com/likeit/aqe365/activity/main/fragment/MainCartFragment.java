package com.likeit.aqe365.activity.main.fragment;


import android.support.v4.app.Fragment;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainCartFragment extends BaseFragment {


    public MainCartFragment() {
        // Required empty public constructor
    }


    public void initUI() {

    }

    public void initData() {

    }

    public void addListeners() {

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_cart;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
        initData();
    }
}
