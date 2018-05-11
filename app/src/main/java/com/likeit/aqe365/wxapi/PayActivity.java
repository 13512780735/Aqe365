package com.likeit.aqe365.wxapi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.utils.CustomDialog;
import com.likeit.aqe365.wxapi.alipay.PayResult;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


import butterknife.BindView;
import butterknife.ButterKnife;


public class PayActivity extends BaseActivity implements OnClickListener {

    @BindView(R.id.rl_pay_weixin)
    RelativeLayout rlweixin;
    @BindView(R.id.rl_pay_zhifubao)
    RelativeLayout rlZfb;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_indent_number)
    TextView tv_indent_number;
    @BindView(R.id.back_view)
    LinearLayout back_view;
    private IWXAPI api;


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
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {


                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(mContext, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "resultStatus-->" + resultStatus);

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };
    private String ordersn, price;
    private String flag;
    private String pay_type;
    private String vipId;
    private String paykey;
    private CustomDialog dialog;
    private PayActivity mContext;
    private String ukey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        mContext = this;
        setTitle("收银台");
        setBackView();
        Intent intent = getIntent();
        String WX_APPID = "wx5132fa74303fb155";
        api = WXAPIFactory.createWXAPI(this, WX_APPID, false);
        api.registerApp(WX_APPID);

        initView();
    }

    private void initView() {
        tvPrice.setText("¥ " + price);
        rlweixin.setOnClickListener(this);
        rlZfb.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //微信支付
//            case R.id.ddhk_pay_weixin:
//                UtilPreference.saveString(mContext, "weixinKey", flag);
//                pay_type = "wxpay";
//                if ("1".equals(flag)) {
//                    inidata();
//                    loaddingdialog.show();
//                } else if ("2".equals(flag)) {
//                    initRecharge(pay_type);
//                } else if ("3".equals(flag)) {
//                    buyVip(pay_type);
//
//                }
//                break;
//            //支付宝支付
//            case R.id.ddhk_pay_zhifubao:
//                pay_type = "alipay";
//                // showProgress("暂未开通此服务");
//                if ("1".equals(flag)) {
//                    initData1();
//                    loaddingdialog.show();
//                } else if ("2".equals(flag)) {
//                    initRecharge1(pay_type);
//                } else if ("3".equals(flag)) {
//                    buyVip1(pay_type);
//                }


        }
    }



    private void alipay(String data) {

        final String payInfo = data;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PayActivity.this);
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



/*
    private void inidata() {
        RetrofitUtil.getInstance().PayWxpay(ukey, ordersn, new Subscriber<BaseResponse<WeixinModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                loaddingdialog.dismiss();
            }

            @Override
            public void onNext(BaseResponse<WeixinModel> baseResponse) {
                loaddingdialog.dismiss();
                if (baseResponse.ret == 200) {
                    String appId = baseResponse.getData().getAppid();
                    String partnerId = baseResponse.getData().getMch_id();
                    String prepayId = baseResponse.getData().getPrepay_id();
                    String nonceStr = baseResponse.getData().getNonce_str();
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
                    //   String key = "9ijy7876yuio987yhjkfjdklkjhy6543";
                    //String key = "177b59d8b56ae5c63145d9824f661020";
                    String key = "kldghur52525fhsdhdsfhdfklgjadlgt";
                    // String key = baseResponse.getData().getSign();
                    String stringSignTemp = stringA + "&key=" + key;
                    String sign = MD5.getMessageDigest(stringSignTemp.getBytes()).toUpperCase();
                    Log.d("TAG", "sign-->" + sign);
                    Log.d("TAG", "appId-->" + appId + "partnerId-->" + partnerId + "prepayId-->" + prepayId + "nonceStr-->" + nonceStr + "packageValue-->" + packageValue);
                    sendPayred(appId, partnerId, prepayId, nonceStr, packageValue, sign, timeStamp);
                } else {
                    if ("Ukey不合法".equals(baseResponse.getMsg())) {
                        showProgress01("您的帐号已在其他设备登录！");
                        return;
                    } else {
                        showProgress(baseResponse.getMsg());
                    }
                }
            }
        });


    }
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

    /**
     * 显示字符串消息
     *
     * @param message
     */
    public void showProgress(String message) {
        // dialog = new CustomDialog(getActivity());
        dialog = new CustomDialog(this).builder()
                .setGravity(Gravity.CENTER).setTitle("提示", getResources().getColor(R.color.sd_color_black))//可以不设置标题颜色，默认系统颜色
                .setSubTitle(message);
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1000);
    }

    public void showProgress01(String message) {
        // dialog = new CustomDialog(getActivity());
        dialog = new CustomDialog(this).builder()
                .setGravity(Gravity.CENTER).setTitle("提示", getResources().getColor(R.color.sd_color_black))//可以不设置标题颜色，默认系统颜色
                .setSubTitle(message);
        dialog.show();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                dialog.dismiss();
//                //toActivityFinish(Login_RegisterActivity.class);
//                Intent intent = new Intent(mContext, Login_RegisterActivity.class);
//                startActivity(intent);
//                finish();
//                MyActivityManager.getInstance().finishAllActivity();
//            }
//        }, 1000);

    }
}
