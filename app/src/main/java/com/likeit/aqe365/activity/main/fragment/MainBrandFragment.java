package com.likeit.aqe365.activity.main.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainBrandFragment extends BaseFragment {

    @BindView(R.id.banner)
    ConvenientBanner mBanner;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();

    private String[] images = {"http://aqe365.wbteam.cn/attachment/images/1/2018/05/q63612vY97x7S273y76725rs2V1753.jpg", "http://aqe365.wbteam.cn/attachment/images/1/2018/05/q63612vY97x7S273y76725rs2V1753.jpg"};
    public void initUI() {
        setTitle("品牌专区");
        mBanner.startTurning(4000);
        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, Arrays.asList(images)).setPageIndicator(new int[]{R.drawable.indicator_gray, R.drawable.indicator_red}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)  .setScrollDuration(1500);;


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

    public void initData() {

    }

    public void addListeners() {

    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_brand;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
        initData();
    }
}
