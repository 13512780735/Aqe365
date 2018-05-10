package com.likeit.aqe365.activity.people.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;

/**
 * 商品关注
 * A simple {@link Fragment} subclass.
 */
public class GoodsAttentionFragment extends BaseFragment {


    public static GoodsAttentionFragment newInstance() {
        Bundle bundle = new Bundle();
        GoodsAttentionFragment fragment = new GoodsAttentionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    public int inflaterRootView() {
        return R.layout.fragment_goods_attention;
    }

    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_attention_title));
        setRightText(getResources().getString(R.string.app_people_attention_edit), new View.OnClickListener() {
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
        return R.layout.fragment_goods_attention;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
        initData();
    }
}
