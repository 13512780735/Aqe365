package com.likeit.aqe365.activity.sort;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class GoodListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.search_content_et)
    TextView mSearchContentEt;
    @BindView(R.id.search_layout)
    LinearLayout mSearchLayout;
    @BindView(R.id.message_img)
    ImageView mMessageImg;
    @BindView(R.id.rb01)
    RadioButton mRb01;
    @BindView(R.id.rb02)
    RadioButton mRb02;
    @BindView(R.id.rb03)
    RadioButton mRb03;
    @BindView(R.id.rb04)
    RadioButton mRb04;
    @BindView(R.id.goodlist_RadioGroup)
    RadioGroup mGoodlistRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_list);
        initUI();
    }

    private void initUI() {
        mGoodlistRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb01:
                        break;
                    case R.id.rb02:
                        break;
                    case R.id.rb03:
                        break;
                    case R.id.rb04:

                        GoodFilterFragment goodFilterFragment = new GoodFilterFragment();
                        goodFilterFragment.show(getSupportFragmentManager(), "HeightFragment");
//                        goodFilterFragment.setOnDialogListener(new OnDialogListener() {
//                            @Override
//                            public void onDialogClick(String person) {
//                                duration = person;
//                                tvDuration.setText(duration + "小时");
//                            }
//                        });
                        break;
                }
            }
        });

    }

    @OnClick({R.id.iv_back, R.id.message_img})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.message_img:
                break;
        }
    }
}
