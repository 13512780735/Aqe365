package com.likeit.aqe365.activity.people;

import android.os.Bundle;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseActivity;

public class UserInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initUI();
        addListeners();
        initData();
    }

    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_member_title));

    }

    public void initData() {

    }

    public void addListeners() {

    }
}
