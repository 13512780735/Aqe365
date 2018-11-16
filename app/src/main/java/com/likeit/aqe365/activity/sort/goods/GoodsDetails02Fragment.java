package com.likeit.aqe365.activity.sort.goods;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.sort.product.bean.Product;
import com.likeit.aqe365.activity.web.model.JsInterfaceLogic;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.network.model.goods.GoodDetailModel;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsDetails02Fragment extends BaseFragment {

    @BindView(R.id.web)
    WebView mWebView;
    @BindView(R.id.load_error_layout)
    LinearLayout ll_control_error;
    @BindView(R.id.online_error_btn_retry)
    RelativeLayout error;
    private GoodDetailModel goodDetailModel;
    private Product product;
    private boolean isSuccess = false;
    private boolean isError = false;
    private WebSettings mWebSettings;
    @Override
    protected int setContentView() {
        return R.layout.fragment_goods_details02;
    }

    @Override
    protected void lazyLoad() {
        goodDetailModel = (GoodDetailModel) getArguments().getSerializable("goodDetailModel");
        product = (Product) getArguments().getParcelable("product");
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_control_error.setVisibility(View.GONE);
                // loading_over.setVisibility(View.VISIBLE);
                mWebView.reload();
            }
        });
        String url="http://aoquan.maimaitoo.com//app///index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&r=goods.appparticulars&id=377";
        mWebView.loadUrl(goodDetailModel.getParticulars());
        setupUI();
    }

    private void setupUI() {
        mWebView.getSettings().setJavaScriptEnabled(true);
     //   mWebView.addJavascriptInterface(new JsInterfaceLogic(this), "app");
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);    //允许加载javascript
        mWebSettings.setSupportZoom(false);          //允许缩放
        mWebSettings.setBuiltInZoomControls(false);  //原网页基础上缩放
        mWebSettings.setUseWideViewPort(false);      //任意比例缩放
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loaddingDialog.show();

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // mWebView.setVisibility(View.VISIBLE);
                loaddingDialog.dismiss();
                //hideErrorPage();
                //super.onPageFinished(view, url);
                if (!isError) {
                    isSuccess = true;
                    //回调成功后的相关操作
                    ll_control_error.setVisibility(View.GONE);
                    mWebView.setVisibility(View.VISIBLE);
                } else {
                    isError = false;
                    ll_control_error.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                isError = true;
                isSuccess = false;
                //6.0以上执行
                mWebView.setVisibility(View.GONE);
                ll_control_error.setVisibility(View.VISIBLE);
            }
        });
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
                    mWebView.goBack();
                    return true;
                }
                return false;

            }
        });
    }

}
