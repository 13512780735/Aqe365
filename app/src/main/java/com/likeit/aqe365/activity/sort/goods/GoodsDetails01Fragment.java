package com.likeit.aqe365.activity.sort.goods;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.sort.adapter.GoodsDetailsCouponAdapter;
import com.likeit.aqe365.activity.sort.product.ProductSkuDialog;
import com.likeit.aqe365.activity.sort.product.bean.Product;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.network.model.goods.GoodDetailModel;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SharedPreferencesUtils;
import com.likeit.aqe365.view.custom_scrollview.HorizontalPageLayoutManager;
import com.likeit.aqe365.view.custom_scrollview.MyRecyclerView;
import com.likeit.aqe365.view.custom_scrollview.PagingScrollHelper;
import com.wuhenzhizao.sku.bean.Sku;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import onekeyshare.OnekeyShare;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsDetails01Fragment extends BaseFragment {


    @BindView(R.id.banner_goods_picture)
    ConvenientBanner mBanner;
    @BindView(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @BindView(R.id.tv_goods_ids)
    TextView mTvGoodsIds;
    @BindView(R.id.tv_share)
    TextView mTvShare;
    @BindView(R.id.tv_goods_new_price)
    TextView mTvGoodsNewPrice;
    @BindView(R.id.tv_goods_old_price)
    TextView mTvGoodsOldPrice;
    @BindView(R.id.tv_express)
    TextView mTvExpress;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.horizontal_RecyclerView)
    MyRecyclerView mHorRecyclerView;
    @BindView(R.id.ll_sales)
    LinearLayout mLlSales;
    @BindView(R.id.ll_coupon)
    LinearLayout ll_coupon;
    @BindView(R.id.tv_sales)
    TextView mTvSales;

    private GoodDetailModel goodDetailModel;
    private List<GoodDetailModel.DetailTabBean.ThumbBean> adList;
    private List<GoodDetailModel.DetailTabBean.ThumbBean> networkImage = new ArrayList<>();
    private HorizontalPageLayoutManager horizontalPageLayoutManager = null;
    PagingScrollHelper scrollHelper = new PagingScrollHelper();
    private GoodsDetailsCouponAdapter mHorAdapter;
    private List<GoodDetailModel.DetailSaleBean.CouponBean> couponList;
    private ProductSkuDialog dialog;
    private Bundle bundle;
    private Product product;

    @Override
    protected int setContentView() {
        return R.layout.fragment_goods_details01;
    }

    @Override
    protected void lazyLoad() {
        goodDetailModel = (GoodDetailModel) getArguments().getSerializable("goodDetailModel");
        product = (Product) getArguments().getParcelable("product");
        if (goodDetailModel != null) {
            adList = goodDetailModel.getDetail_tab().getThumb();
            couponList = goodDetailModel.getDetail_sale().getCoupon();
        }
        if (couponList == null) {
            ll_coupon.setVisibility(View.GONE);
        }
        initUI();
        initBanner();
        if (adList != null && adList.size() > 0) {
            networkImage = adList;

        }
    }

    private void initBanner() {
        LogUtils.d(adList.get(0).getImgurl());
        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, adList).setPageIndicator(new int[]{R.drawable.indicator_gray, R.drawable.indicator_red}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).setScrollDuration(1500);
    }

    private void initUI() {
        DisplayMetrics dm = getActivity().getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mBanner.getLayoutParams();
        params.width = w_screen;
        params.height = w_screen;
        mBanner.setLayoutParams(params);
        mBanner.startTurning(4000);
        if (goodDetailModel.getDetail_sale().getDetail_tab().getShare().getHideshare().equals("1")) {
            mTvShare.setVisibility(View.GONE);
        } else {
            mTvShare.setVisibility(View.VISIBLE);
        }
        mTvGoodsName.setText(goodDetailModel.getDetail_tab().getTitle());
        mTvGoodsNewPrice.setText(goodDetailModel.getDetail_tab().getMarketprice());
        mTvGoodsIds.setText("注册证号：" + goodDetailModel.getDetail_tab().getRegistnum());
        mTvGoodsOldPrice.setText(goodDetailModel.getDetail_tab().getProductprice());
        mTvExpress.setText("快递: " + goodDetailModel.getDetail_tab().getDispatchprice());
        mTvAddress.setText(goodDetailModel.getDetail_tab().getArea());
        mTvNumber.setText("销量: " + goodDetailModel.getDetail_tab().getSales() + " 盒");
        mTvGoodsOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        horizontalPageLayoutManager = new HorizontalPageLayoutManager(1, 3);
        //滚动adapter
        mHorAdapter = new GoodsDetailsCouponAdapter(R.layout.layout_coupon_items, couponList);
        mHorRecyclerView.setAdapter(mHorAdapter);
        mHorAdapter.notifyDataSetChanged();
        scrollHelper.setUpRecycleView(mHorRecyclerView);
        RecyclerView.LayoutManager layoutManager = null;
        layoutManager = horizontalPageLayoutManager;
        if (layoutManager != null) {
            mHorRecyclerView.setLayoutManager(layoutManager);
            scrollHelper.updateLayoutManger();
        }
    }


    public class NetworkImageHolderView implements Holder<GoodDetailModel.DetailTabBean.ThumbBean> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, GoodDetailModel.DetailTabBean.ThumbBean data) {
            Glide.with(getActivity()).load(data.getImgurl()).into(imageView);
        }
    }


    @OnClick({R.id.tv_share, R.id.ll_sales, R.id.ll_coupon})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_share:
                showShare();
                break;
            case R.id.ll_coupon://点击更多优惠卷
                toActivity(CouponListActivity.class);
                break;
            case R.id.ll_sales://选择规格
                SharedPreferencesUtils.put(getContext(), "keys", "0");
                showSkuDialog();
                break;
        }
    }

    /**
     * 分享
     */
    private void showShare() {
        Resources res = getActivity().getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("經理是傻冒");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
       // oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        oks.setImageData(bmp);
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(getActivity());
    }

    private void showSkuDialog() {
        if (dialog == null) {
            dialog = new ProductSkuDialog(getActivity());
            dialog.setData(product, new ProductSkuDialog.Callback() {
                @Override
                public void onAdded(Sku sku, int quantity) {
                    Log.d("Sku-->", sku + "quantity-->" + quantity);
                    if (sku == null) {
                        mTvSales.setText("数量：" + quantity);
                    } else {
                        String title = "";
                        for (int i = 0; i < sku.getAttributes().size(); i++) {
                            String temp = sku.getAttributes().get(i).getValue();
                            title += temp + " ,";
                        }
                        String titles = title.substring(0, title.length() - 1);
                        mTvSales.setText("规格：" + titles + "数量：" + quantity);
                    }
                }
            });
        }
        dialog.show();
    }
}
