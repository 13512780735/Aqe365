package com.likeit.aqe365.activity.main.fragment;


import android.support.v4.app.Fragment;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.utils.StatusBarUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainSortFragment extends BaseFragment {


    public void initUI() {

    }

    public void initData() {

    }

    public void addListeners() {

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_sort;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
        initData();
    }
}
