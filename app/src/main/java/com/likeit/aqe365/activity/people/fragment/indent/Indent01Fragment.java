package com.likeit.aqe365.activity.people.fragment.indent;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Indent01Fragment extends BaseFragment {

    @Override
    protected int setContentView() {
        return R.layout.fragment_indent01;
    }

    @Override
    protected void lazyLoad() {
        initUI();
    }

    private void initUI() {

    }

}
