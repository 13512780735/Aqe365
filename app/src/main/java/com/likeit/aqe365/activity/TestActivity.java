package com.likeit.aqe365.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.web.model.JsInterfaceLogic;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.home.MainListItemsModel;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SharedPreferencesUtils;
import com.likeit.aqe365.utils.SignUtils;
import com.likeit.aqe365.utils.ToastUtils;

import rx.Subscriber;

public class TestActivity extends BaseActivity {

    private String act;
    private String mobile;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        mWebView = findViewById(R.id.webView);
//        mobile = SharedPreferencesUtils.getString(this, "phone");
//        pwd = SharedPreferencesUtils.getString(this, "pwd");
//        String url = "http://aqe365.wbteam.cn/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&r=member&mobile=13680260576&pwd=123456&act=auto";
//        initUI();
        initData();

    }

    private void initData() {
        String sign = SignUtils.getSign(this);
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        LogUtils.d("5555" + token + "<-->" + signature + "<-->" + newtime + "<-->" + random);
        RetrofitUtil.getInstance().MainList(token, signature, newtime, random, new Subscriber<BaseResponse<MainListItemsModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LoaddingDismiss();
                LogUtils.d("錯誤" + e);
            }

            @Override
            public void onNext(BaseResponse<MainListItemsModel> baseResponse) {
                LogUtils.d("HomeFragment-->" + 11111);
                 LogUtils.d("HomeFragment-->" + baseResponse.getCode());
                LogUtils.d("HomeFragment-->" + baseResponse.getData());
                    LogUtils.d("HomeFragment-->" + baseResponse.getData().getNotice());
                //ToastUtils.showToast(TestActivity.this, baseResponse.getData().getNotice().get(0).getTitle());
                LoaddingDismiss();
//                if (baseResponse.code == 200) {
//                    mainListItemsModel = baseResponse.getData();
//                    LogUtils.d("HomeFragment-->" + mainListItemsModel);
//                    adList = mainListItemsModel.getList().getBanner();
//                    if (adList != null && adList.size() > 0) {
//                        networkImage = adList;
//
//                    }
//                    initBanner();
//                    notice = mainListItemsModel.getList().getNotice().get(0).getTitle();
//                    tvNotice.setSelected(true);
//                    tvNotice.setText(notice);
//                    goodsList = mainListItemsModel.getList().getGoodsgroup();
//                }

            }
        });
    }

    private void initUI() {
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
    }

}
