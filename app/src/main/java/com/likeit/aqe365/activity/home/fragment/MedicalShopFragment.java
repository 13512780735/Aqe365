package com.likeit.aqe365.activity.home.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.view.MyGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 医用耗材
 * A simple {@link Fragment} subclass.
 */
public class MedicalShopFragment  extends BaseFragment {

    @BindView(R.id.banner)
    ConvenientBanner mBanner;
    @BindView(R.id.GridView)
    MyGridView mGridView;
    private String[] images = {"http://aqe365.wbteam.cn/attachment/images/1/2018/04/J2yz5R1gZM871pR55167K5GR4ygSRZ.png", "http://aqe365.wbteam.cn/attachment/images/1/2018/04/J2yz5R1gZM871pR55167K5GR4ygSRZ.png"};
    private ArrayList<Integer> localImages = new ArrayList<Integer>();

    /**
     * 品牌精选
     */

    private int[] icon = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;


    public static MedicalShopFragment newInstance() {
        Bundle args = new Bundle();
        MedicalShopFragment fragment = new MedicalShopFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int setContentView() {
        return R.layout.fragment_medical_shop;
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
        setTitle("医用耗材");
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
        /**
         * 精品
         */
        dataList = new ArrayList<>();
        getData();
        String[] from = {"img", "name"};
        final int[] to = {R.id.iv_people_avatar};
        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.layout_brand_gridview_items, from, to);
        //配置适配器
        mGridView.setAdapter(simpleAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showProgress("点击了！");
            }
        });

    }  private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            dataList.add(map);
        }
        return dataList;
    }


}
