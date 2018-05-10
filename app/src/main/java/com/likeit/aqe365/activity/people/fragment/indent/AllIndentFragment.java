package com.likeit.aqe365.activity.people.fragment.indent;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllIndentFragment extends BaseFragment {


    public void initUI() {

    }

    public void initData() {

    }

    public void addListeners() {

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_all_indent;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
        initData();
    }
}
