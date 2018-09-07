package com.likeit.aqe365.activity.web.jsinterface;


import android.app.Fragment;

import com.likeit.aqe365.activity.web.base.BasePresenter;
import com.likeit.aqe365.utils.SharedPreferencesUtils;

public class JsInterfacePresenter extends BasePresenter implements JsInterfaceContract.Presenter {
    private final JsInterfaceContract.View mView;

    JsInterfacePresenter(JsInterfaceContract.View mView) {
        this.mView = mView;
    }

    @Override
    protected void start(boolean isFirstStart) {

        if (isFirstStart) {
            //Activity activity= ((Activity)mView);
            String mobile = SharedPreferencesUtils.getString(((Fragment) mView).getActivity(), "phone");
            String pwd = SharedPreferencesUtils.getString(((Fragment) mView).getActivity(), "pwd");
           //mView.renderUrl("http://aqe365.wbteam.cn/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&r=member&mobile=" + mobile + "&pwd=" + pwd + "&act=auto");
            mView.renderUrl("http://wx.aqe365.com/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&r=member&mobile=" + mobile + "&pwd=" + pwd + "&act=auto");
            // mView.renderUrl("http://aqe365.wbteam.cn/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&r=member&mobile=13680260576&pwd=123456&act=auto");
        }
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
