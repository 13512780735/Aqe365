package com.likeit.aqe365.activity.people.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;

/**
 * 我的零钱
 * A simple {@link Fragment} subclass.
 */
public class ChangeFragment extends BaseFragment {

    public static ChangeFragment newInstance() {
        Bundle bundle = new Bundle();
        ChangeFragment fragment = new ChangeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_change_title));
    }

    public void initData() {

    }

    public void addListeners() {

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_change;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
        initData();
    }
}
