package com.likeit.aqe365.activity;

import android.content.Intent;

import com.king.base.BaseInterface;
import com.king.base.ContentActivity;
import com.likeit.aqe365.activity.login.fragment.ForgetPwdFragment;
import com.likeit.aqe365.activity.login.fragment.PhoneLoginFragment;
import com.likeit.aqe365.activity.login.fragment.RegisterFragment;
import com.likeit.aqe365.activity.login.fragment.RegisterProtocolFragment;
import com.likeit.aqe365.activity.login.fragment.RelevanceUserFragment;
import com.likeit.aqe365.activity.login.fragment.ThirdLoginFragment;
import com.likeit.aqe365.activity.people.PeopleStatisticsFragment;
import com.likeit.aqe365.constants.Constants;

/**
 * Created by admin on 2018/4/19.
 */

public class FrameActivity extends ContentActivity {
    @Override
    protected void switchFragment(Intent intent) {
        int keyFragment = intent.getIntExtra(BaseInterface.KEY_FRAGMENT, 0);
        switch (keyFragment) {
            case Constants.FRAGMENT_PHONE_LOGIN://手机验证登录
                replaceFragment(PhoneLoginFragment.newInstance());
                break;
            case Constants.FRAGMENT_FORGET_PWD://忘记密码
                replaceFragment(ForgetPwdFragment.newInstance());
                break;
            case Constants.FRAGMENT_LOGIN://
                //replaceFragment(RecyclerFragment.newInstance());
                break;
            case Constants.FRAGMENT_REGISTER://注册
                replaceFragment(RegisterFragment.newInstance());
                break;
            case Constants.FRAGMENT_Third_LOGIN://第三方的登录
                replaceFragment(ThirdLoginFragment.newInstance());
                break;
            case Constants.FRAGMENT_RELEVANCE_USER://关联用户
                replaceFragment(RelevanceUserFragment.newInstance());
                break;
            case Constants.FRAGMENT_REGISTER_PROTOCOL://注册协议
                replaceFragment(RegisterProtocolFragment.newInstance());
                break;
            case Constants.FRAGMENT_PEOPLE_STATISTICS://报表统计
                replaceFragment(PeopleStatisticsFragment.newInstance());
                break;
            default:
                break;
        }
    }
}
