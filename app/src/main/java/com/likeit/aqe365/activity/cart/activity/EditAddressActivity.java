package com.likeit.aqe365.activity.cart.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.iron.library.linkage.LinkageDialog;
import com.github.iron.library.linkage.LinkageItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.listener.IEditTextChangeListener;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.EmptyEntity;
import com.likeit.aqe365.network.model.goods.ProvincesModel;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.EditTextSizeCheckUtil;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SignUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

public class EditAddressActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ed_recipients_name)
    EditText mEdRecipientsName;
    @BindView(R.id.ed_recipients_phone)
    EditText mEdRecipientsPhone;
    @BindView(R.id.tv_recipients_address)
    TextView mTvRecipientsAddress;
    @BindView(R.id.ll_recipients_address)
    LinearLayout mLlRecipientsAddress;
    @BindView(R.id.ed_recipients_detailed_address)
    EditText mEdRecipientsDetailedAddress;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    private int flag,position;
    private String id, address, realname, mobile, province, city, area;
    private LinkageDialog dialog;
    private List<LinkageItem> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        flag = getIntent().getExtras().getInt("flag");  //0  新建  1 编辑
        position = getIntent().getExtras().getInt("position");
        id = getIntent().getExtras().getString("id");
        address = getIntent().getExtras().getString("address");
        realname = getIntent().getExtras().getString("realname");
        mobile = getIntent().getExtras().getString("mobile");
        province = getIntent().getExtras().getString("province");
        city = getIntent().getExtras().getString("city");
        area = getIntent().getExtras().getString("area");
        initUI();
        initCityBtn();
        // initData();


    }

    private void initCityBtn() {
        //创建对话框
        final LinkageDialog dialog = new LinkageDialog.Builder(EditAddressActivity.this, 3).setLinkageData(getCityList())
                .setOnLinkageSelectListener(new LinkageDialog.IOnLinkageSelectListener() {
                    @Override
                    public void onLinkageSelect(LinkageItem... items) {
                        toastLinkageItem(items);
                    }
                }).build();

        //按钮点击
        findViewById(R.id.ll_recipients_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    private void initData() {
        String json = getJson("city.json");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ProvincesModel>>() {
        }.getType();
        List<ProvincesModel> citys = gson.fromJson(json, type);
        List<LinkageItem> cityList = new ArrayList<>();
        cityList.addAll(citys);
        // return cityList;
    }

    public String getJson(String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
//    private void init

    private void initUI() {
        setBackView();
        if (flag == 0) {
            setTitle("添加地址");
        } else if (flag == 1) {
            setTitle("编辑地址");
            mTvSave.setBackgroundResource(R.drawable.shape_round_blue_bg_5);
            setRightText("删除", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteAddress();
                }
            });
        }
        mEdRecipientsName.setText(realname);
        mEdRecipientsPhone.setText(mobile);
        mTvRecipientsAddress.setText(province + city + area);
        mEdRecipientsDetailedAddress.setText(address);
        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(mTvSave);
        textChangeListener.addAllEditText(mEdRecipientsPhone, mEdRecipientsName, mEdRecipientsDetailedAddress);
        EditTextSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    mTvSave.setBackgroundResource(R.drawable.shape_round_blue_bg_5);
                    mTvSave.setOnClickListener(EditAddressActivity.this);
                } else {
                    mTvSave.setBackgroundResource(R.drawable.shape_round_grey_bg_5);
                }
            }
        });
    }

    private void deleteAddress() {
        final String sign = SignUtils.getSign(this);
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        RetrofitUtil.getInstance().deleteAddress(token, signature, newtime, random, id, new Subscriber<BaseResponse<EmptyEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseResponse<EmptyEntity> baseResponse) {
                if (baseResponse.code == 200) {
                    showToast(baseResponse.getMsg());
                    Intent intent = new Intent();
                    intent.putExtra("result", "2");
                    intent.putExtra("position", position);
                    setResult(1001, intent);
                    finish();
                } else {
                    showToast(baseResponse.getMsg());
                }
            }
        });
    }

    @OnClick({R.id.tv_save})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_save:
                /**
                 * 0:添加地址
                 * 1：编辑地址
                 */
                if (flag == 0) {
                    addAdress();
                } else if (flag == 1) {
                    addAdress();
                }
                break;
        }
    }

    private void toastLinkageItem(LinkageItem... items) {
        StringBuilder str = new StringBuilder(" ");
        for (int i = 0; i < items.length && items[i] != null; i++) {
            str.append(items[i].getLinkageName());
            province = items[0].getLinkageName();
            city = items[1].getLinkageName();
            area = items[2].getLinkageName();
            //str.append(" ");
        }
        mTvRecipientsAddress.setText(province + city + area);
        //  Toast.makeText(this, province + city + area, Toast.LENGTH_SHORT).show();
    }


//    /**
//     * 编辑地址
//     */
//    private void editAddress() {
//        String sign = SignUtils.getSign(this);
//        String signs[] = sign.split("##");
//        String signature = signs[0];
//        String newtime = signs[1];
//        String random = signs[2];
//        realname=mEdRecipientsName.getText().toString().trim();
//        mobile=mEdRecipientsPhone.getText().toString().trim();
//        address=mEdRecipientsDetailedAddress.getText().toString().trim();
//    }

    /**
     * 添加地址
     */
    private void addAdress() {
        String sign = SignUtils.getSign(this);
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        realname = mEdRecipientsName.getText().toString().trim();
        mobile = mEdRecipientsPhone.getText().toString().trim();
        address = mEdRecipientsDetailedAddress.getText().toString().trim();
        RetrofitUtil.getInstance().setAddress(token, signature, newtime, random, id, address, realname, mobile, province, city, area, new Subscriber<BaseResponse<EmptyEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseResponse<EmptyEntity> baseResponse) {
                if (baseResponse.code == 200) {
                    showToast(baseResponse.getMsg());
                    Intent intent = new Intent();
                    intent.putExtra("result", "1");
                    intent.putExtra("position", position);
                    setResult(1001, intent);
                    finish();
                } else {
                    showToast(baseResponse.getMsg());
                }

            }
        });
    }

    public List<LinkageItem> getCityList() {
        String json = getJson("city.json");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ProvincesModel>>() {
        }.getType();
        List<ProvincesModel> citys = gson.fromJson(json, type);
        List<LinkageItem> cityList = new ArrayList<>();
        cityList.addAll(citys);
        return cityList;
    }
}
