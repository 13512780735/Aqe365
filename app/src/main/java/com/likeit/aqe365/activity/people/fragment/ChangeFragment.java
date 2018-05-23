package com.likeit.aqe365.activity.people.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的零钱
 * A simple {@link Fragment} subclass.
 */
public class ChangeFragment extends BaseFragment {

    @BindView(R.id.tv_change_details)
    TextView mTvChangeDetails;
    @BindView(R.id.tv_change_income)
    TextView mTvChangeIncome;
    @BindView(R.id.tv_change_expend)
    TextView mTvChangeExpend;

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


    @OnClick({R.id.tv_change_details, R.id.tv_change_income, R.id.tv_change_expend})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_change_details:
                break;
            case R.id.tv_change_income:
                break;
            case R.id.tv_change_expend:
                break;
        }
    }
}
