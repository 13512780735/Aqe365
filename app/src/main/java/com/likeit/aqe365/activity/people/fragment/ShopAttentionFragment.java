package com.likeit.aqe365.activity.people.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;

/**
 * 店铺关注
 * A simple {@link Fragment} subclass.
 */
public class ShopAttentionFragment extends BaseFragment {


    public static ShopAttentionFragment newInstance() {
        Bundle bundle = new Bundle();
        ShopAttentionFragment fragment = new ShopAttentionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_shop_title));
        setRightText(getResources().getString(R.string.app_people_shop_edit), new View.OnClickListener() {
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
        return R.layout.fragment_shop_attention;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
        initData();
    }
}
