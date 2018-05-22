package com.likeit.aqe365.activity.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.login.fragment.ForgetPwdFragment;
import com.likeit.aqe365.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

/**
 * 感控产品
 * A simple {@link Fragment} subclass.
 */
public class InfectionControlFragment extends BaseFragment {

    private String[] images = {"http://aqe365.wbteam.cn/attachment/images/1/2018/04/R4UUhZE4I4i48z5he5Z884T7Wxvo75.png", "http://aqe365.wbteam.cn/attachment/images/1/2018/04/R4UUhZE4I4i48z5he5Z884T7Wxvo75.png"};
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    @BindView(R.id.banner)
    ConvenientBanner mBanner;
    public static InfectionControlFragment newInstance() {
        Bundle args = new Bundle();
        InfectionControlFragment fragment = new InfectionControlFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int setContentView() {
        return R.layout.fragment_infection_control;
    }

    @Override
    protected void lazyLoad() {
        initUI();
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

    private void initUI() {
        setBackView();
        setTitle("感控产品");
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
    }


}
