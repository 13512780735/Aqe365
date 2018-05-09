package com.likeit.aqe365.activity.people.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.king.base.BaseFragment;
import com.likeit.aqe365.R;

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



    @Override
    public int inflaterRootView() {
        return R.layout.fragment_footprint;
    }

    @Override
    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_footprint_title));
        setRightText(getResources().getString(R.string.app_people_footprint_edit), new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {

    }
}
