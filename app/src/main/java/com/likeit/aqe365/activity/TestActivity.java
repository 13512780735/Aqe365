package com.likeit.aqe365.activity;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.home.MainListItemsModel;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SignUtils;

import rx.Subscriber;

public class TestActivity extends BaseActivity {

    private String act;
    private String mobile;
    private String pwd;
    private ImageView imageView01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        imageView01 = findViewById(R.id.iv01);
        initData();
        findView(R.id.bt01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLight(imageView01, 80);
            }
        });
    }

    private void changeLight(ImageView imageView, int brightness) {
        ColorMatrix cMatrix = new ColorMatrix();
        cMatrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1, 0, 0, brightness, // 改变亮度
                0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});
        imageView.setColorFilter(new ColorMatrixColorFilter(cMatrix));
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
                LoaddingDismiss();

            }
        });
    }


}
