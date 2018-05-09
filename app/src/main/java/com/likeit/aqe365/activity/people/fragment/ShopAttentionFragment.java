package com.likeit.aqe365.activity.people.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.king.base.BaseFragment;
import com.likeit.aqe365.R;

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


    @Override
    public int inflaterRootView() {
        return R.layout.fragment_shop_attention;
    }

    @Override
    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_shop_title));
        setRightText(getResources().getString(R.string.app_people_shop_edit), new View.OnClickListener() {
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
