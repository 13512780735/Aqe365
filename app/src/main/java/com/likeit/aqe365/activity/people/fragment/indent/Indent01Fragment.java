package com.likeit.aqe365.activity.people.fragment.indent;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.people.activity.IndentDetailsActivity;
import com.likeit.aqe365.activity.people.adapter.GoodIndent01Adapter;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.Indent.IndentListModel;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SignUtils;
import com.likeit.aqe365.wxapi.PayActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class Indent01Fragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private GoodIndent01Adapter mAdapter;

    private int pageNum = 1;
    private static final int PAGE_SIZE = 1;//为什么是6呢？
    private boolean isErr = true;
    private boolean mLoadMoreEndGone = false; //是否加载更多完毕
    private int mCurrentCounter = 0;
    int TOTAL_COUNTER = 0;
    private Bundle bundle;
    private IndentListModel indentListModel;

    @Override
    protected int setContentView() {
        return R.layout.fragment_indent01;
    }

    @Override
    protected void lazyLoad() {
        initUI();
    }

    private void initUI() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initAdapter();
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rl_indent_details://订单详情
                        bundle = new Bundle();
                        bundle.putInt("status", 1);
                        toActivity(IndentDetailsActivity.class, bundle);
                        break;
                    case R.id.tv_cancel_indent://取消订单
                        break;
                    case R.id.tv_del_indent://删除订单
                        break;
                    case R.id.tv_pay://支付订单
                        String IndentId = data.get(position).getOrdersn();
                        String money = data.get(position).getPrice();
                        bundle = new Bundle();
                        bundle.putString("tid", IndentId);
                        bundle.putString("money", money);
                        toActivity(PayActivity.class, bundle);
                        break;
                    case R.id.tv_check_wuLiu://查看无聊
                        break;
                    case R.id.tv_confirm_goods://确认售后
                        break;
                }
            }
        });
    }

    private void initAdapter() {

        mAdapter = new GoodIndent01Adapter(R.layout.goods_indent_items, data);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.disableLoadMoreIfNotFullPage();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        initData(pageNum, false);
        mCurrentCounter = mAdapter.getData().size();
    }

    private List<IndentListModel.ListBean> data = new ArrayList<>();

    public void initData(int pageNum, final boolean isloadmore) {
        LoaddingShow();
        String sign = SignUtils.getSign(getActivity());
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        RetrofitUtil.getInstance().Orderform(token, signature, newtime, random, "5", String.valueOf(pageNum), new Subscriber<BaseResponse<IndentListModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LoaddingDismiss();
                LogUtils.d("Indent01Fragment" + e);
            }

            @Override
            public void onNext(BaseResponse<IndentListModel> baseResponse) {
                LoaddingDismiss();
                if (baseResponse.code == 200) {
                    indentListModel = baseResponse.getData();
                    List<IndentListModel.ListBean> list = indentListModel.getList();

                    if (list != null && list.size() > 0) {
                        if (!isloadmore) {
                            data = list;
                        } else {
                            data.addAll(list);
                        }
                        mAdapter.setNewData(data);
                        mAdapter.notifyDataSetChanged();
                    } else {

                        mAdapter.setEmptyView(R.layout.notdata_view);
                    }

                } else {
                    showProgress(baseResponse.getMsg());
                }
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        mSwipeRefreshLayout.setEnabled(false);
        TOTAL_COUNTER = Integer.valueOf(indentListModel.getTotal());
        if (mAdapter.getData().size() < PAGE_SIZE) {
            mAdapter.loadMoreEnd(true);
        } else {
            if (mCurrentCounter >= TOTAL_COUNTER) {
                mAdapter.loadMoreEnd(mLoadMoreEndGone);
            } else {
                if (isErr) {
                    pageNum += 1;
                    initData(pageNum, true);
                    //    mAdapter.addData(data);
                    mCurrentCounter = mAdapter.getData().size();
                    mAdapter.loadMoreComplete();
                } else {
                    isErr = true;
                    // Toast.makeText(getContext(), "错误", Toast.LENGTH_LONG).show();
                    mAdapter.loadMoreFail();
                }
            }
            mSwipeRefreshLayout.setEnabled(true);
        }
    }

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);//禁止加载
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // mAdapter.setNewData(data);
                isErr = true;
                mCurrentCounter = PAGE_SIZE;
                pageNum = 1;//页数置为1 才能继续重新加载
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.setEnableLoadMore(true);//启用加载
            }
        }, 2000);
    }
}
