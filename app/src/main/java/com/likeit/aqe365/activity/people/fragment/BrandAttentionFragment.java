package com.likeit.aqe365.activity.people.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.king.base.BaseFragment;
import com.likeit.aqe365.R;

/**
 * 品牌关注
 * A simple {@link Fragment} subclass.
 */
public class BrandAttentionFragment extends BaseFragment {


    public static BrandAttentionFragment newInstance() {
        Bundle bundle = new Bundle();
        BrandAttentionFragment fragment = new BrandAttentionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int inflaterRootView() {
        return R.layout.fragment_brand_attention;
    }

    @Override
    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_brand_title));
        setRightText(getResources().getString(R.string.app_people_brand_edit), new View.OnClickListener() {
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
