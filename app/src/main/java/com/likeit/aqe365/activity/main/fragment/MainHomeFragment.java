package com.likeit.aqe365.activity.main.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.likeit.aqe365.R;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.constants.Constants;
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
public class MainHomeFragment extends BaseFragment {


    @BindView(R.id.banner)
    ConvenientBanner mBanner;
    @BindView(R.id.MyGridView)
    MyGridView mGrideView;
    @BindView(R.id.notice_msg_tv)
    TextView tvNotice;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();

    private String[] images = {"http://aqe365.wbteam.cn/attachment/images/1/2018/05/q63612vY97x7S273y76725rs2V1753.jpg", "http://aqe365.wbteam.cn/attachment/images/1/2018/05/q63612vY97x7S273y76725rs2V1753.jpg"};

    private int[] icon = {R.mipmap.icon_home_01, R.mipmap.icon_home_02,
            R.mipmap.icon_home_03,R.mipmap.icon_home_04, R.mipmap.icon_home_05, R.mipmap.icon_home_06, R.mipmap.icon_home_07, R.mipmap.icon_home_08, R.mipmap.icon_home_09, R.mipmap.icon_home_10};
    private String[] iconName = {"牙科商城", "医用耗材", "感控产品", "积分商城", "活动专场", "新人福利", "领劵中心", "试用中心","我的足迹","推荐有礼"};
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;

    public void initUI() {
        mBanner.startTurning(4000);
        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, Arrays.asList(images)).setPageIndicator(new int[]{R.drawable.indicator_gray, R.drawable.indicator_red}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)  .setScrollDuration(1500);;

        dataList = new ArrayList<>();
        getData();
        String[] from = {"img", "name"};
        final int[] to = {R.id.iv_people_avatar, R.id.tv_people_name};
        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.layout_people_gridview_items, from, to);
        //配置适配器
        mGrideView.setAdapter(simpleAdapter);
        mGrideView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0: //我的社区
//                        break;
//                    case 1:// 积分商城
//                        break;
//                    case 2://我的邀请
//                        break;
//                    case 3://邀请二维码
//                        break;
//                    case 4://发票服务
//                        break;
//                    case 5://报表统计
//                        break;
//                    case 6://意见反馈
//                        break;
//                    case 7://常购商品
//                        break;
//                }
            }
        });
        tvNotice.setSelected(true);
        tvNotice.setText("澳泉医销网多商户商城公告测试,澳泉医销网多商户商城公告测试");
    }
    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("name", iconName[i]);
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
        return R.layout.fragment_main_home;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
        initData();
    }

}
