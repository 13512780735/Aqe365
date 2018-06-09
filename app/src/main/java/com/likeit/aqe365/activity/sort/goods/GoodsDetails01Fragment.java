package com.likeit.aqe365.activity.sort.goods;


import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.likeit.aqe365.activity.home.fragment.DentistryShopFragment;
import com.likeit.aqe365.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;

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
    private View view;

    private String[] images = {"http://wx.aqe365.com/attachment/images/1/merch/118/lMryAgGe9v9VZNm3Y1EvErmvA6maZ3.jpg", "http://wx.aqe365.com/attachment/images/1/merch/118/pgS8vW78Gg4npmDUws4FG8s334gfPg.jpg"};
    private ArrayList<Integer> localImages = new ArrayList<Integer>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_goods_details01;
    }

    @Override
    protected void lazyLoad() {
        initUI();
    }

    private void initUI() {
        DisplayMetrics dm = getActivity().getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mBanner.getLayoutParams();
        params.width = w_screen;
        params.height = w_screen;
        mBanner.setLayoutParams(params);
        mBanner.startTurning(4000);
        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, Arrays.asList(images)).setPageIndicator(new int[]{R.drawable.indicator_gray, R.drawable.indicator_red}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).setScrollDuration(1500);
        mTvGoodsName.setText("松风FX-II加强型玻璃离子充填材料 30克+10ml");
        mTvGoodsNewPrice.setText("205");
        mTvGoodsIds.setText("注册证号：鄂械注准20162632237");
        mTvGoodsOldPrice.setText("¥ 4000.00");
        mTvExpress.setText("快递: "+"包邮");
        mTvAddress.setText("广东省 中山市");
        mTvNumber.setText("销量: "+"0 盒");
        mTvGoodsOldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
    }

    public class NetworkImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(getActivity()).load(data).into(imageView);
        }
    }


    @OnClick(R.id.tv_share)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_share:
                break;
        }
    }
}
