package com.likeit.aqe365.activity.people;

import android.os.Bundle;

import com.king.base.BaseActivity;
import com.likeit.aqe365.R;

public class UserInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }

    @Override
    public void initUI() {
        setBackView();
        setTitle(getResources().getString(R.string.app_people_member_title));

    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {

    }
}
