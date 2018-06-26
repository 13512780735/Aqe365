package com.likeit.aqe365.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.utils.AppManager;
import com.likeit.aqe365.utils.StatusBarUtil;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ZxingActivity extends AppCompatActivity {
    @BindView(R.id.back_view)
    LinearLayout mBackView;
    @BindView(R.id.title)
    TextView mTitle;
    private CaptureFragment captureFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        AppManager.getAppManager().addActivity(this);
        int color = getResources().getColor(R.color.white);
        StatusBarUtil.setColor(this, color, 0);
        StatusBarUtil.setLightMode(this);
        ButterKnife.bind(this);
        captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();

        initView();
    }

    public static boolean isOpen = false;

    private void initView() {
        mBackView.setVisibility(View.VISIBLE);
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText("二维码扫描");
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear1);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    CodeUtils.isLightEnable(true);
                    isOpen = true;
                } else {
                    CodeUtils.isLightEnable(false);
                    isOpen = false;
                }

            }
        });
    }


    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            ZxingActivity.this.setResult(RESULT_OK, resultIntent);
            ZxingActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            ZxingActivity.this.setResult(RESULT_OK, resultIntent);
            ZxingActivity.this.finish();
        }
    };

    @OnClick(R.id.back_view)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back_view:
                onBackPressed();
                break;
        }
    }
}
