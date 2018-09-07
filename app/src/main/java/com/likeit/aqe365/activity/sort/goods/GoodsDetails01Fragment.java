package com.likeit.aqe365.activity.sort.goods;


import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
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
import com.likeit.aqe365.activity.home.adapter.HomeHorGoodsAdapter;
import com.likeit.aqe365.activity.home.fragment.DentistryShopFragment;
import com.likeit.aqe365.activity.sort.adapter.GoodsDetailsCouponAdapter;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.listener.OnSalesSelectListener;
import com.likeit.aqe365.network.model.goods.GoodDetailModel;
import com.likeit.aqe365.network.model.goods.GoodsSalesModel;
import com.likeit.aqe365.network.model.home.MainListItemsModel;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.view.custom_scrollview.HorizontalPageLayoutManager;
import com.likeit.aqe365.view.custom_scrollview.MyRecyclerView;
import com.likeit.aqe365.view.custom_scrollview.PagingScrollHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.tv_sales)
    TextView mTvSales;

    //    private String[] images = {"http://wx.aqe365.com/attachment/images/1/merch/118/lMryAgGe9v9VZNm3Y1EvErmvA6maZ3.jpg", "http://wx.aqe365.com/attachment/images/1/merch/118/pgS8vW78Gg4npmDUws4FG8s334gfPg.jpg"};
//    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private GoodDetailModel goodDetailModel;
    private List<GoodDetailModel.DetailTabBean.ThumbBean> adList;
    private List<GoodDetailModel.DetailTabBean.ThumbBean> networkImage = new ArrayList<>();
    private HorizontalPageLayoutManager horizontalPageLayoutManager = null;
    PagingScrollHelper scrollHelper = new PagingScrollHelper();
    private GoodsDetailsCouponAdapter mHorAdapter;
    private List<GoodDetailModel.DetailSaleBean.CouponBean> couponList;
    private ShopDialogFragment dialog;
    private Bundle bundle;
    private GoodsSalesModel goodsSalesModel;

    @Override
    protected int setContentView() {
        return R.layout.fragment_goods_details01;
    }

    @Override
    protected void lazyLoad() {
        goodDetailModel = (GoodDetailModel) getArguments().getSerializable("goodDetailModel");
        goodsSalesModel = (GoodsSalesModel) getArguments().getSerializable("goodsSalesModel");
        if (goodDetailModel != null) {
            adList = goodDetailModel.getDetail_tab().getThumb();
            couponList = goodDetailModel.getDetail_sale().getCoupon();
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
        if (goodDetailModel.getDetail_sale().getDetail_tab().getShare().getHideshare().equals("0")) {
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


    @OnClick({R.id.tv_share, R.id.ll_sales})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_share:
                break;
            case R.id.ll_sales://选择规格
                dialog = new ShopDialogFragment();
                bundle = new Bundle();
                bundle.putString("keys", "0");
                bundle.putSerializable("goodDetailModel", goodDetailModel);
                bundle.putSerializable("goodsSalesModel", goodsSalesModel);
                dialog.setArguments(bundle);
                dialog.show(getActivity().getSupportFragmentManager(), "ShopDialogFragment");
                dialog.setOnLoginInforCompleted(new OnSalesSelectListener() {
                    @Override
                    public void selectSalesListener(String title, String id, String number) {
                        LogUtils.d("ids-->" + title + "goodsId-->" + id + "countNum-->" + number);
                        if (title == null) {
                            mTvSales.setText("数量：" + number);
                        }
                        mTvSales.setText("规格：" + title + "数量：" + number);
                    }
                });
                break;
        }
    }
}
