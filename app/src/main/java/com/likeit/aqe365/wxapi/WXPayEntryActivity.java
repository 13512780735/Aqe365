package com.likeit.aqe365.wxapi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.utils.CustomDialog;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private IWXAPI api;
    String payKey;
    private String key;
    private String weixinKey;
    private WXPayEntryActivity mContext;
    private CustomDialog dialog;
    private String paykey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        mContext=this;
        // String WX_APPID = "wx307bf5fa134ffacd";
        String WX_APPID = "wx5132fa74303fb155";
        api = WXAPIFactory.createWXAPI(this, WX_APPID);
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        Toast.makeText(getApplicationContext(), "onReq", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int code = resp.errCode;
            Log.d("TAg", code + "");
            Log.d("TAG", +resp.getType() + "");
            if (code == 0) {
                new AlertDialog.Builder(this).setMessage("支付订单成功！").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).setTitle("微信支付结果").setCancelable(false).show();

            } else if (code == -2) {
                new AlertDialog.Builder(this).setMessage("取消支付").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        onBackPressed();
                    }
                }).setTitle("微信支付结果").setCancelable(false).show();

            } else {
                new AlertDialog.Builder(this).setMessage("交易出错").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        onBackPressed();
                    }
                }).setTitle("微信支付结果").setCancelable(false).show();
            }

        }

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

}