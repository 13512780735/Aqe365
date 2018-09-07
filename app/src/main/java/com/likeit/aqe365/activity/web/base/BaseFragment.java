package com.likeit.aqe365.activity.web.base;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.likeit.aqe365.activity.login.activity.LoginActivity;
import com.likeit.aqe365.utils.AppManager;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.wxapi.MD5;
import com.likeit.aqe365.wxapi.alipay.PayResult;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.IWXAPI;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Unbinder;

/**
 * @author gangan
 */
public class BaseFragment<T extends BasePresenterInterface> extends Fragment {
    static {
        WebView.setWebContentsDebuggingEnabled(true);
    }

    protected T mPresenter;
    protected Unbinder mUnbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

        if (mPresenter != null) {
            mPresenter.start();
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }
        super.onDestroy();
    }

    public void setPresenter(@NonNull T presenter) {
        mPresenter = presenter;
    }

    public void startActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }

    public void showNativeMessage(String message) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        AppManager.getAppManager().finishAllActivity();
    }

    public void showWeChatPay(String str) {

    }

    public void showAlipayPay(String str) {
       // String data="alipay_sdk=alipay-sdk-php-20161101&app_id=2018031902408105&biz_content=%7B%22subject%22%3A%22%E6%BE%B3%E6%B3%89%E5%8C%BB%E9%94%80%E7%BD%91%E8%AE%A2%E5%8D%95%E6%94%AF%E4%BB%98%22%2C%22out_trade_no%22%3A%22ME20180724135603821220%22%2C%22total_amount%22%3A%22138%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22timeout_express%22%3A%2210m%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Faqe365.wbteam.cn%2Faddons%2Fewei_shopv2%2Fpayment%2Falipay%2Fnotify.php&sign_type=RSA2&timestamp=2018-07-24+15%3A04%3A10&version=1.0&sign=hXld5CyUIajrvfesxDCT7JUmfOX6W9kXB%2BC9vqo%2FOGEGUo%2BfXRlOSbGsAd8pEuiQ8DVcUdF8R6IYZT643uQRRUl8YTB7ki85VgwWuCunY%2BxHBYueG%2BtVkfSVWoC%2FA1A1f%2FP2o5mUWlnWMn5n6%2FTP%2BEL5SFLi0DNmlneLoBucjwquXj%2BXb8mTy3zIUM3jVePyTLc5OqWFN9afGc4%2FQw90xHlRg45YhRJ%2Bdb7l4n7oIzHy3c65HEHxkNzPt3tLVDFv3m7Nl8ewc48ymIQtZE%2BLPaqPJ30zgLNYyJsXhVoih9Eh0AhyP8ySW27qo2JvGAMCJF4ewaQB6R1qci7yivuZ%2Fg%3D%3D";
      //  alipay(str);
    }



}
