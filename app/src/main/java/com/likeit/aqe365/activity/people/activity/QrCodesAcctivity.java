package com.likeit.aqe365.activity.people.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class QrCodesAcctivity extends BaseActivity {
    public ImageView imageView = null;

    public Bitmap mBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_codes_acctivity);
        setBackView();
        setTitle("邀请二维码");
//        setRightImage(getResources().getDrawable(R.mipmap.icon_share), new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        initUI();
    }

    private void initUI() {
        imageView = (ImageView) findViewById(R.id.image_content);
        mBitmap = CodeUtils.createImage("1", 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        imageView.setImageBitmap(mBitmap);
    }
}
