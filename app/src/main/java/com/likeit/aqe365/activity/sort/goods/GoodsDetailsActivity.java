package com.likeit.aqe365.activity.sort.goods;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.main.MainActivity;
import com.likeit.aqe365.activity.people.adapter.GoodsIndentTabAdapter;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.EmptyEntity;
import com.likeit.aqe365.network.model.goods.GoodDetailModel;
import com.likeit.aqe365.network.model.goods.GoodsSalesModel;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SharedPreferencesUtils;
import com.likeit.aqe365.utils.SignUtils;
import com.likeit.aqe365.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class GoodsDetailsActivity extends BaseActivity {

    @BindView(R.id.back_view)
    LinearLayout mBackView;
    @BindView(R.id.indent_TabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.right_view)
    LinearLayout mRightView;
    @BindView(R.id.viewpager)
    NoScrollViewPager mViewpager;
    @BindView(R.id.tv_attention)
    TextView mTvAttention;
    @BindView(R.id.tv_shop)
    TextView mTvShop;
    @BindView(R.id.tv_cart)
    TextView mTvCart;
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;

    private ArrayList<String> mTitles;
    private ShopDialogFragment dialog;
    private Bundle bundle;
    private String id;
    private GoodDetailModel goodDetailModel;
    private String collect;
    private GoodsSalesModel goodsSalesModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        ButterKnife.bind(this);
        id = getIntent().getExtras().getString("id");
        LogUtils.d(id + "");
        mTitles = new ArrayList<>(Arrays.asList("商品", "详情", "参数", "评价"));
        initData();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("销毁");
    }

    private void initData() {
        loaddingDialog.show();
        String sign = SignUtils.getSign(this);
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        RetrofitUtil.getInstance().goodsDetails(token, signature, newtime, random, id, new Subscriber<BaseResponse<GoodDetailModel>>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {
                loaddingDialog.dismiss();
            }

            @Override
            public void onNext(BaseResponse<GoodDetailModel> baseResponse) {
                // loaddingDialog.dismiss();
                loaddingDialog.dismiss();
                LogUtils.d("111-->" + baseResponse.getCode() + "");
                if (baseResponse.code == 200) {
                    goodDetailModel = baseResponse.getData();
                    LogUtils.d(goodDetailModel.getDetail_tab().getId());
                    collect = goodDetailModel.getDetail_tab().getCollect();
                    //
                    //initUI();
                    initData01();

                } else {
                    showProgress(baseResponse.getMsg());
                }
            }
        });
    }

    /**
     * 获取商品规格
     */
    private void initData01() {
        String sign = SignUtils.getSign(this);
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        RetrofitUtil.getInstance().goodsSales(token, signature, newtime, random, id, new Subscriber<BaseResponse<GoodsSalesModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                loaddingDialog.dismiss();
                LogUtils.d("333-->" + e);
            }

            @Override
            public void onNext(BaseResponse<GoodsSalesModel> baseResponse) {
                loaddingDialog.dismiss();
                LogUtils.d("222-->" + baseResponse.getCode() + "");
                if (baseResponse.code == 200) {
                    goodsSalesModel = baseResponse.getData();
                } else {
                    showProgress(baseResponse.getMsg());
                }
                initUI();
            }
        });
    }

    private Drawable img;

    private void initUI() {
        LogUtils.d("初始化");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewpager);
        List<Fragment> mfragments = new ArrayList<Fragment>();
        GoodsDetails01Fragment goodsDetails01Fragment = new GoodsDetails01Fragment();
        GoodsDetails02Fragment goodsDetails02Fragment = new GoodsDetails02Fragment();
        GoodsDetails03Fragment goodsDetails03Fragment = new GoodsDetails03Fragment();
        GoodsDetails04Fragment goodsDetails04Fragment = new GoodsDetails04Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("goodDetailModel", goodDetailModel);
        bundle.putSerializable("goodsSalesModel", goodsSalesModel);
        goodsDetails01Fragment.setArguments(bundle);
        mfragments.add(goodsDetails01Fragment);
        mfragments.add(goodsDetails02Fragment);
        mfragments.add(goodsDetails03Fragment);
        mfragments.add(goodsDetails04Fragment);
        mViewpager.setAdapter(new GoodsIndentTabAdapter(getSupportFragmentManager(), mfragments, mTitles));
        mViewpager.setCurrentItem(0);
        if ("0".equals(collect)) {
            img = getResources().getDrawable(R.mipmap.icon_details_like);
            img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
            mTvAttention.setCompoundDrawables(null, img, null, null); //设置左图标

        } else if ("1".equals(collect)) {
            img = getResources().getDrawable(R.mipmap.icon_details_like_fill);
            img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
            mTvAttention.setCompoundDrawables(null, img, null, null); //设置左图标
        }
    }

    @OnClick({R.id.back_view, R.id.right_view, R.id.tv_attention, R.id.tv_shop, R.id.tv_cart, R.id.tv_add, R.id.tv_buy})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back_view:
                this.finish();
                break;
            case R.id.right_view:
                break;

            case R.id.tv_attention:
                if ("0".equals(collect)) {
                    img = getResources().getDrawable(R.mipmap.icon_details_like_fill);
                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                    mTvAttention.setCompoundDrawables(null, img, null, null); //设置左图标
                    collect = "1";

                } else if ("1".equals(collect)) {
                    img = getResources().getDrawable(R.mipmap.icon_details_like);
                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                    mTvAttention.setCompoundDrawables(null, img, null, null); //设置左图标
                    collect = "0";
                }
                String sign = SignUtils.getSign(this);
                String signs[] = sign.split("##");
                String signature = signs[0];
                String newtime = signs[1];
                String random = signs[2];
                RetrofitUtil.getInstance().goodsCollect(token, signature, newtime, random, id, collect, new Subscriber<BaseResponse<EmptyEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseResponse<EmptyEntity> baseResponse) {
                        LogUtils.d(baseResponse.getMsg());
                        if (baseResponse.code == 200) {
                            showToast(baseResponse.getMsg());
                        } else {
                            showToast(baseResponse.getMsg());
                        }
                    }
                });
                break;
            case R.id.tv_shop:
                break;
            case R.id.tv_cart:
                bundle = new Bundle();
                bundle.putString("flag", "1");
                toActivity(MainActivity.class, bundle);
                break;
            case R.id.tv_add://添加购物车
                dialog = new ShopDialogFragment();
                bundle = new Bundle();
                bundle.putString("keys", "1");
                bundle.putSerializable("goodDetailModel", goodDetailModel);
                bundle.putSerializable("goodsSalesModel", goodsSalesModel);
                dialog.setArguments(bundle);
                dialog.show(this.getSupportFragmentManager(), "ShopDialogFragment");
                break;
            case R.id.tv_buy://立即购买
            //    SharedPreferencesUtils.put(this, "indentFlag", "1");//表示从详情进入确认订单
                dialog = new ShopDialogFragment();
                bundle = new Bundle();
                bundle.putString("keys", "2");
                bundle.putSerializable("goodDetailModel", goodDetailModel);
                bundle.putSerializable("goodsSalesModel", goodsSalesModel);
                dialog.setArguments(bundle);
                dialog.show(this.getSupportFragmentManager(), "ShopDialogFragment");
                break;
        }
    }
}
