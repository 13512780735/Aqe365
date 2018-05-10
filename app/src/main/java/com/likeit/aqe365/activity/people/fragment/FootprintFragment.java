package com.likeit.aqe365.activity.people.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;

/**
 * 我的足迹
 * A simple {@link Fragment} subclass.
 */
public class FootprintFragment extends BaseFragment {


    public static FootprintFragment newInstance() {
        Bundle bundle = new Bundle();
        FootprintFragment fragment = new FootprintFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_footprint_title));
        setRightText(getResources().getString(R.string.app_people_footprint_edit), new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void initData() {

    }

    public void addListeners() {

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_footprint;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
        initData();
    }
}
