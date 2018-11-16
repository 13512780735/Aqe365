package com.likeit.aqe365.activity.web.jsinterface;


import android.app.Fragment;

import com.likeit.aqe365.activity.login.activity.LoginActivity;
import com.likeit.aqe365.activity.login.activity.WelcomeActivity;
import com.likeit.aqe365.activity.web.base.BasePresenter;
import com.likeit.aqe365.network.consts.Consts;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SharedPreferencesUtils;
import com.nostra13.universalimageloader.utils.L;

public class JsInterfacePresenter extends BasePresenter implements JsInterfaceContract.Presenter {
    private final JsInterfaceContract.View mView;

    JsInterfacePresenter(JsInterfaceContract.View mView) {
        this.mView = mView;
    }

    @Override
    protected void start(boolean isFirstStart) {
        String Login = SharedPreferencesUtils.getString(((Fragment) mView).getActivity(), "login");
        String mobile = SharedPreferencesUtils.getString(((Fragment) mView).getActivity(), "phone");
        String pwd = SharedPreferencesUtils.getString(((Fragment) mView).getActivity(), "pwd");
        String token = SharedPreferencesUtils.getString(((Fragment) mView).getActivity(), "token");
        if (isFirstStart) {
            //Activity activity= ((Activity)mView);

            LogUtils.d("token111-->" + Consts.HOME_HOST + "app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile" + token);
            LogUtils.d("Login-->" + Login);
            if ("1".equals(Login)) {
                mView.renderUrl(Consts.HOME_HOST + "app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile");
            } else {
                // mView.renderUrl("http://aoquan.maimaitoo.com/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&r=member&mobile=" + mobile + "&pwd=" + pwd + "&act=auto");
                mView.renderUrl(Consts.HOME_HOST + "app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&token=" + token);
            }
        }
//        else  {
//            if ("1".equals(Login)) {
//                mView.renderUrl(Consts.HOME_HOST + "app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile");
//            } else {
//                // mView.renderUrl("http://aoquan.maimaitoo.com/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&r=member&mobile=" + mobile + "&pwd=" + pwd + "&act=auto");
//                mView.renderUrl(Consts.HOME_HOST + "app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&token=" + token);
//            }
//        }
    }

    @Override
    public void clickBtn1() {
        mView.execJavaScript("complete()");
    }

    @Override
    public void clickBtn2() {
        mView.execJavaScript("faild()");
    }

}
