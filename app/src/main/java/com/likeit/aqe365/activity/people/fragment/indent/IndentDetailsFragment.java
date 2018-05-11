package com.likeit.aqe365.activity.people.fragment.indent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.people.fragment.BrandAttentionFragment;
import com.likeit.aqe365.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndentDetailsFragment extends BaseFragment {


    public static IndentDetailsFragment newInstance() {
        Bundle bundle = new Bundle();
        IndentDetailsFragment fragment = new IndentDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int setContentView() {
        return R.layout.fragment_indent_details;
    }

    @Override
    protected void lazyLoad() {

    }

}
