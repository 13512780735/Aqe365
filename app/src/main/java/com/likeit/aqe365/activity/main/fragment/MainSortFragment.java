package com.likeit.aqe365.activity.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.sort.adapter.LeftAdapter;
import com.likeit.aqe365.activity.sort.adapter.RightAdapter;
import com.likeit.aqe365.activity.sort.bean.ShopSortBean;
import com.likeit.aqe365.activity.sort.bean.ShopSortItemBean;
import com.likeit.aqe365.activity.sort.bean.ShopSortListBean;
import com.likeit.aqe365.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainSortFragment extends BaseFragment {


//    @BindView(R.id.main_left_rv)
//    RecyclerView mMainLeftRv;
//    @BindView(R.id.main_right_rv)
//    RecyclerView mMainRightRv;


    private List<ShopSortBean> shopSortBeanList;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private List<ShopSortListBean> listBeanList;
    private RecyclerView mLeftRvRecyclerView;
    private RecyclerView mRightRvRecyclerView;
    @Override
    protected int setContentView() {
        return R.layout.fragment_main_sort;
    }

    @Override
    protected void lazyLoad() {
        mLeftRvRecyclerView = (RecyclerView) findViewById(R.id.main_left_rv);
        mRightRvRecyclerView = (RecyclerView) findViewById(R.id.main_right_rv);
        initData();
        leftAdapter=new LeftAdapter(R.layout.item_main_left,shopSortBeanList);
        rightAdapter=new RightAdapter(R.layout.item_main_right,listBeanList);
        mLeftRvRecyclerView.setAdapter(leftAdapter);
        mRightRvRecyclerView.setAdapter(rightAdapter);

        mLeftRvRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRightRvRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      mLeftRvRecyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ShopSortBean shopSortBean = shopSortBeanList.get(i);
                listBeanList.clear();
                listBeanList.addAll(shopSortBean.getmList());
                leftAdapter.setSelectPos(i);
                leftAdapter.notifyDataSetChanged();
                rightAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });
    }


    public void initData() {
        shopSortBeanList = new ArrayList<>();
        listBeanList = new ArrayList<>();

        ShopSortBean d1 = new ShopSortBean();
        d1.setTitle("牙科商城");
        ShopSortBean d2 = new ShopSortBean();
        d2.setTitle("医用耗材");
        ShopSortBean d3 = new ShopSortBean();
        d3.setTitle("感控产品");

        ShopSortListBean l1 = new ShopSortListBean();

        l1.setType("车针、磨头、扩锉类");


        ShopSortListBean l2 = new ShopSortListBean();

        l2.setType("口内辅助材料类");
        ShopSortListBean l3 = new ShopSortListBean();

        l3.setType("感控产品");

        ShopSortItemBean b1 = new ShopSortItemBean();
        b1.setName("车针");
        ShopSortItemBean b2 = new ShopSortItemBean();
        b2.setName("磨头");
        ShopSortItemBean b3 = new ShopSortItemBean();
        b3.setName("口腔辅助治疗");
        ShopSortItemBean b4 = new ShopSortItemBean();
        b4.setName("口腔护理");
        ShopSortItemBean b5 = new ShopSortItemBean();
        b5.setName("无纺布");
        ShopSortItemBean b6 = new ShopSortItemBean();
        b6.setName("灭菌袋");

        List<ShopSortItemBean> list1 = new ArrayList<>();
        List<ShopSortItemBean> list2 = new ArrayList<>();
        List<ShopSortItemBean> list3 = new ArrayList<>();
        list1.add(b1);
        list1.add(b2);
        list2.add(b3);
        list2.add(b4);
        list3.add(b5);
        list3.add(b6);

        l1.setmList(list1);
        l2.setmList(list2);
        l3.setmList(list3);
        List<ShopSortListBean> li1 = new ArrayList<>();
        List<ShopSortListBean> li2 = new ArrayList<>();
        List<ShopSortListBean> li3 = new ArrayList<>();

        li1.add(l1);
        li1.add(l2);
        li1.add(l3);
        li2.add(l1);
        li2.add(l2);
        li3.add(l3);

        d1.setmList(li1);
        d2.setmList(li2);
        d3.setmList(li3);


        shopSortBeanList.add(d1);
        shopSortBeanList.add(d2);
        shopSortBeanList.add(d3);


        listBeanList.addAll(shopSortBeanList.get(0).getmList());
    }

    public void addListeners() {

    }



}
