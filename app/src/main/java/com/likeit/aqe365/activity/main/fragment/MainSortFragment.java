package com.likeit.aqe365.activity.main.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.sort.adapter.LeftAdapter;
import com.likeit.aqe365.activity.sort.adapter.RightAdapter;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.GoodCategory.GoodsCategoryModel;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SignUtils;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainSortFragment extends BaseFragment {


//    @BindView(R.id.main_left_rv)
//    RecyclerView mMainLeftRv;
//    @BindView(R.id.main_right_rv)
//    RecyclerView mMainRightRv;


    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private RecyclerView mLeftRvRecyclerView;
    private RecyclerView mRightRvRecyclerView;
    private GoodsCategoryModel goodsCategoryModel;
    private List<GoodsCategoryModel.ListBean.TwotierBean> listBeanList;
    List<GoodsCategoryModel.ListBean> goodsCategoryBeanList;
    private ArrayList<GoodsCategoryModel.ListBean.TwotierBean.GoodsBean> goodsBeans;

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_sort;
    }

    @Override
    protected void lazyLoad() {
        goodsCategoryBeanList = new ArrayList<>();
        listBeanList = new ArrayList<>();
        goodsBeans = new ArrayList<>();
        mLeftRvRecyclerView = (RecyclerView) findViewById(R.id.main_left_rv);
        mRightRvRecyclerView = (RecyclerView) findViewById(R.id.main_right_rv);
        //initData();
        initData();
        loaddingDialog.show();
        initUI();

    }

    private void initUI() {
        leftAdapter = new LeftAdapter(R.layout.item_main_left, goodsCategoryBeanList);
        rightAdapter = new RightAdapter(R.layout.item_main_right, listBeanList);
        mLeftRvRecyclerView.setAdapter(leftAdapter);
        mRightRvRecyclerView.setAdapter(rightAdapter);

        mLeftRvRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRightRvRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLeftRvRecyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GoodsCategoryModel.ListBean shopSortBean = goodsCategoryBeanList.get(i);
                listBeanList.clear();
                listBeanList.addAll(shopSortBean.getTwotier());
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

    private void initData() {
        String sign = SignUtils.getSign(getActivity());
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];

        RetrofitUtil.getInstance().GoodsCategory(token, signature, newtime, random, new Subscriber<BaseResponse<GoodsCategoryModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
        loaddingDialog.dismiss();
            }

            @Override
            public void onNext(BaseResponse<GoodsCategoryModel> baseResponse) {
                loaddingDialog.dismiss();
                if (baseResponse.code == 200) {
                    LogUtils.d("MainSortFragment-->" + baseResponse.getData());

                    goodsCategoryModel = baseResponse.getData();
                    goodsCategoryBeanList = goodsCategoryModel.getList();
                    for (int i = 0; i < goodsCategoryBeanList.size(); i++) {
                        listBeanList.addAll(goodsCategoryBeanList.get(i).getTwotier());
                    }
                    leftAdapter.setNewData(goodsCategoryBeanList);
                    rightAdapter.setNewData(listBeanList);
                    leftAdapter.notifyDataSetChanged();
                    rightAdapter.notifyDataSetChanged();
                    LogUtils.d("goodsCategoryBeanList--》" + listBeanList.get(1).getName());

                }
            }
        });
    }



    public void addListeners() {

    }


}
