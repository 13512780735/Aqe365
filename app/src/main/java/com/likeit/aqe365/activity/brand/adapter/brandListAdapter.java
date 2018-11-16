package com.likeit.aqe365.activity.brand.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.brand.model.BrandModel;
import com.likeit.aqe365.activity.sort.goods.GoodsDetailsActivity;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.view.custom_scrollview.MyRecyclerView;

import java.util.List;

public class brandListAdapter extends BaseQuickAdapter<BrandModel.LogocategoryBean, BaseViewHolder> {
    private MyRecyclerView mRecyclerView;
    private BrandRecommendAdapter mAdapter;
    private List<BrandModel.LogocategoryBean.RecommendBean> data;

    public brandListAdapter(int layoutResId, List<BrandModel.LogocategoryBean> data) {
        super(R.layout.brand_recomend_items, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BrandModel.LogocategoryBean item) {
        baseViewHolder.setText(R.id.tv_name, item.getName() + "·品牌商品推荐");
        mRecyclerView = baseViewHolder.getView(R.id.mRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        gridLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new BrandRecommendAdapter(R.layout.brand_recommend_list_items, data);
        data = item.getRecommend();
        mAdapter.setNewData(data);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//            }
//        });
    }
}
