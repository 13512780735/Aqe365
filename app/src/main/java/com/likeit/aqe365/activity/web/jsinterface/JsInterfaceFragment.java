package com.likeit.aqe365.activity.web.jsinterface;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.login.activity.LoginActivity;
import com.likeit.aqe365.activity.web.base.BaseFragment;
import com.likeit.aqe365.activity.web.model.JsInterfaceLogic;
import com.likeit.aqe365.event.PayEventMessage;
import com.likeit.aqe365.network.consts.Consts;
import com.likeit.aqe365.utils.CustomDialog;
import com.likeit.aqe365.utils.LoaddingDialog;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SharedPreferencesUtils;
import com.likeit.aqe365.wxapi.MD5;
import com.likeit.aqe365.wxapi.alipay.PayResult;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import onekeyshare.OnekeyShare;

/**
 * A simple {@link Fragment} subclass.
 */
public class JsInterfaceFragment extends BaseFragment<JsInterfaceContract.Presenter> implements JsInterfaceContract.View {

    @BindView(R.id.tv_toolbar_title)
    TextView mTvToolbarTitle;
    @BindView(R.id.img_toolbar_back)
    ImageView mImgToolbarBack;
    @BindView(R.id.web)
    WebView mWebView;
    @BindView(R.id.load_error_layout)
    LinearLayout ll_control_error;
    @BindView(R.id.online_error_btn_retry)
    RelativeLayout error;


    private LoaddingDialog mDialog;
    private IWXAPI api;
    private String WX_APPID;
    private boolean isSuccess = false;
    private boolean isError = false;

    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    LogUtils.d("错误码：" + resultStatus);
                    LogUtils.d("resultInfo：" + resultInfo);
                    SharedPreferencesUtils.put(getActivity(), "type", "WXPay");
                    SharedPreferencesUtils.put(getActivity(), "code", resultStatus);
//                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                        mPresenter.clickBtn1();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(getActivity(), "支付结果确认中", Toast.LENGTH_SHORT).show();
                            mPresenter.clickBtn2();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "resultStatus-->" + resultStatus);
                            mPresenter.clickBtn2();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };
    private String mobile, pwd;
    private CustomDialog dialog;
    private boolean mIsLoadSuccess;
    private View mErrorView;
    private WebSettings mWebSettings;
    private String token;
    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_js_interface, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mDialog = new LoaddingDialog(getActivity());
        WX_APPID = "wx53ba9da9956a74aa";
        api = WXAPIFactory.createWXAPI(getActivity(), WX_APPID, false);
        api.registerApp(WX_APPID);
        mobile = SharedPreferencesUtils.getString(getActivity(), "phone");
        pwd = SharedPreferencesUtils.getString(getActivity(), "pwd");
        token = SharedPreferencesUtils.getString(getActivity(), "token");
        //  token="ODcyMHlwMEx5SHB5bTc0bmg5SUpiU0w2aGxDWWdoUkZPL1ZzRUVzeThjNnVTL3VLV0VSMU15bk9ReGJoWWN3YmUrVQ==";
        String Login = SharedPreferencesUtils.getString(getActivity(), "login");
        LogUtils.d("Login-->" + Login);
        LogUtils.d("token-->" + token);
        if ("1".equals(Login)) {
            url = Consts.HOME_HOST + "app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile";

        } else {
            url = Consts.HOME_HOST + "app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&token=" + token;
            // url="http://aoquan.maimaitoo.com/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&r=member&mobile=" + mobile + "&pwd=" + pwd + "&act=auto";
        }
        if("2".equals(Login)){
            mWebView.clearCache(true);
        }
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_control_error.setVisibility(View.GONE);
                mWebView.reload();
            }
        });
        setupUI();
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage(PayEventMessage event) {
        String type = event.getType();
        LogUtils.d("evenyType-->" + type);
        if ("1".equals(type)) {
            mPresenter.clickBtn1();
        } else {
            mPresenter.clickBtn2();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void renderUrl(@NonNull String url) {
        LogUtils.d("TAGURL-->" + url);

        mWebView.loadUrl(url);

    }


    private void setupUI() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JsInterfaceLogic(this), "app");
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);    //允许加载javascript
        mWebSettings.setSupportZoom(false);          //允许缩放
        mWebSettings.setBuiltInZoomControls(false);  //原网页基础上缩放
        mWebSettings.setUseWideViewPort(false);      //任意比例缩放
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                result.confirm();
                return super.onJsAlert(view, url, message, result);
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.d("URL-->" + url);
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //  mDialog.show();

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // mWebView.setVisibility(View.VISIBLE);
                mDialog.dismiss();
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


    @Override
    public void showWeChatPay(String str) {
        super.showWeChatPay(str);
        LogUtils.d("微信支付888" + str);
        try {
            JSONObject object = new JSONObject(str);
            String appId = object.optString("appid");
            String partnerId = object.optString("mch_id");
            String prepayId = object.optString("prepay_id");
            String nonceStr = object.optString("nonce_str");
            String packageValue = "Sign=Wxpay";
            long timeMills = System.currentTimeMillis() / 1000;
            String timeStamp = String.valueOf(timeMills);
            String stringA =
                    "appid=" + appId
                            + "&noncestr=" + nonceStr
                            + "&package=" + packageValue
                            + "&partnerid=" + partnerId
                            + "&prepayid=" + prepayId
                            + "&timestamp=" + timeStamp;
            String key = "dahgdrh678fdh4sdhtui527gjsdtasaa";
            String stringSignTemp = stringA + "&key=" + key;
            String sign = MD5.getMessageDigest(stringSignTemp.getBytes()).toUpperCase();
            LogUtils.d("TAG" + "WX_APPID-->" + WX_APPID + "appId-->" + appId + "partnerId-->" + partnerId + "prepayId-->" + prepayId + "nonceStr-->" + nonceStr + "packageValue-->" + packageValue);
            sendPayred(appId, partnerId, prepayId, nonceStr, packageValue, sign, timeStamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void showAlipayPay(String str) {
        super.showAlipayPay(str);
        LogUtils.d("支付宝888" + str);
        alipay(str);
    }


    @Override
    public void execJavaScript(@NonNull String js) {
        mWebView.evaluateJavascript(js, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                //showNativeMessage("调用JS方法后得到的返回值是：" + s);
                LogUtils.d("获取JS值" + s);
                //window.history.go(-1);
            }
        });
    }

    /**
     * 支付宝支付
     *
     * @param data
     */
    private void alipay(String data) {

        final String payInfo = data;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(getActivity());
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    /**
     * 微信支付
     *
     * @param appId
     * @param partnerId
     * @param prepayId
     * @param nonceStr
     * @param packageValue
     * @param sign
     * @param timeStamp
     */
    private void sendPayred(String appId, String partnerId, String prepayId, String nonceStr, String packageValue, String sign, String timeStamp) {
        PayReq request = new PayReq();
        request.appId = appId;
        request.partnerId = partnerId;
        request.prepayId = prepayId;
        request.nonceStr = nonceStr;
        request.packageValue = packageValue;
        request.sign = sign;
        request.timeStamp = timeStamp;
        api.sendReq(request);
    }

    @Override
    public void appShare(String str) {
        super.appShare(str);
        LogUtils.d("支付宝888" + str);
        try {
            JSONObject object = new JSONObject(str);
            String title = object.optString("title");
            String desc = object.optString("desc");
            String text = object.optString("text");
            String imgPaths = object.optString("imgPaths");
            String url = object.optString("url");
            showShare(title, desc, text, imgPaths, url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //alipay(str);

    }

    /**
     * 商品詳情分享
     *
     * @param title
     * @param desc
     * @param text
     * @param imgPaths
     * @param url
     */

    private void showShare(String title, String desc, String text, String imgPaths, String url) {
        Resources res = getActivity().getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(title);
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(text);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImageData(bmp);
        oks.setImageUrl(imgPaths);

        oks.setSite(getActivity().getString(R.string.app_name));
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网使用
        // 启动分享GUI
        oks.show(getActivity());
    }

}
