package com.likeit.aqe365.activity.main.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.constants.Constants;
import com.likeit.aqe365.utils.StatusBarUtil;
import com.likeit.aqe365.view.MyGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainBrandFragment extends BaseFragment {

    @BindView(R.id.banner)
    ConvenientBanner mBanner;
    @BindView(R.id.GridView)
    MyGridView mGridView;
    @BindView(R.id.GridView01)
    MyGridView mGridView01;
    /**
     * banner
     */
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private String[] images = {"http://aqe365.wbteam.cn/attachment/images/1/2018/05/q63612vY97x7S273y76725rs2V1753.jpg", "http://aqe365.wbteam.cn/attachment/images/1/2018/05/q63612vY97x7S273y76725rs2V1753.jpg"};
    /**
     * 品牌精选
     */

    private int[] icon = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;

    /**
     * 推荐品牌
     */
    private int[] icon01 = {R.mipmap.icon_01, R.mipmap.icon_02,
            R.mipmap.icon_03, R.mipmap.icon_04, R.mipmap.icon_05, R.mipmap.icon_06};
    private List<Map<String, Object>> dataList01;
    private SimpleAdapter simpleAdapter01;

    public void initUI() {
        setTitle("品牌专区");
        mBanner.startTurning(4000);
        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, Arrays.asList(images)).setPageIndicator(new int[]{R.drawable.indicator_gray, R.drawable.indicator_red}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)  .setScrollDuration(1500);;
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
        /**
         * 推荐
         */
        dataList01 = new ArrayList<>();
        getData01();
        String[] from01 = {"img", "name"};
        final int[] to01 = {R.id.iv_people_avatar};
        simpleAdapter01 = new SimpleAdapter(getActivity(), dataList01, R.layout.layout_brand_gridview_items, from01, to01);
        //配置适配器
        mGridView01.setAdapter(simpleAdapter01);
        mGridView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showProgress("点击了！");
            }
        });

    }
    private List<Map<String, Object>> getData01() {
        for (int i = 0; i < icon01.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon01[i]);
            dataList01.add(map);
        }
        return dataList01;
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            dataList.add(map);
        }
        return dataList;
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
