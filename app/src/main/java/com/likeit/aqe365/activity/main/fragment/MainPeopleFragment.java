package com.likeit.aqe365.activity.main.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.king.base.BaseFragment;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.FrameActivity;
import com.likeit.aqe365.constants.Constants;
import com.likeit.aqe365.view.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPeopleFragment extends BaseFragment {


    private MyGridView mGridView;
    private int[] icon = {R.mipmap.icon_people_my_forum, R.mipmap.icon_people_integral_mall,
            R.mipmap.icon_people_my_invitation, R.mipmap.icon_people_qr_code, R.mipmap.icon_people_invoice_service, R.mipmap.icon_people_statistics, R.mipmap.icon_people_feedback, R.mipmap.icon_people_regular_purchase};
    private String[] iconName = {"我的社区", "积分商城", "我的邀请", "邀请二维码", "发票服务", "报表统计", "意见反馈", "常购商品"};
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;

    public MainPeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public int inflaterRootView() {
        return R.layout.fragment_main_people;
    }

    @Override
    public void initUI() {
        mGridView = findView(R.id.MyGridView);

    }

    @Override
    public void initData() {
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        String[] from = {"img", "name"};
        final int[] to = {R.id.iv_people_avatar, R.id.tv_people_name};
        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.layout_people_gridview_items, from, to);
        //配置适配器
        mGridView.setAdapter(simpleAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (view.getId()) {
                    case 0: //我的社区
                        break;
                    case 1:// 积分商城
                        break;
                    case 2://我的邀请
                        break;
                    case 3://邀请二维码
                        break;
                    case 4://发票服务
                        break;
                    case 5://报表统计
                        startFrameActivity(Constants.FRAGMENT_FORGET_PWD);
                        break;
                    case 6://意见反馈
                        break;
                    case 7://常购商品
                        break;
                }
            }
        });
    }
    private void startFrameActivity(int keyFragment) {
        Intent intent = new Intent(getActivity(), FrameActivity.class);
        intent.putExtra(KEY_FRAGMENT, keyFragment);
        startActivity(intent);
    }
    @Override
    public void addListeners() {

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
}
