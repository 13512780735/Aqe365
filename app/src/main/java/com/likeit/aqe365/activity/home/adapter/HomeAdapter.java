package com.likeit.aqe365.activity.home.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.sort.goods.GoodsDetailsActivity;
import com.likeit.aqe365.network.model.home.MainListItemsModel;
import com.likeit.aqe365.utils.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<MainListItemsModel.GoodsgroupBean, BaseViewHolder> {
    private RecyclerView mRecyclerView;

    private HomeGoodsListAdapter mAdapter;
    private String listStyle;

    public HomeAdapter(int layoutResId, List<MainListItemsModel.GoodsgroupBean> data) {
        super(R.layout.home_goods_list_items, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainListItemsModel.GoodsgroupBean item) {
        mRecyclerView = helper.getView(R.id.RecyclerViewF);
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        helper.setText(R.id.goodlist_title, item.getModuletitle());
        listStyle = item.getListstyle();
        if ("block three".equals(listStyle)) {
            final List<MainListItemsModel.GoodsgroupBean.GoodsBeanX> goodsData = item.getGoods();
            mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
            mAdapter = new HomeGoodsListAdapter(R.layout.home_goods_list_items_gridview_items, goodsData);
            mAdapter.setNewData(goodsData);
            mAdapter.notifyDataSetChanged();
            //  initAdapter();
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String cid = goodsData.get(position).getId();
                    Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", cid);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                    LogUtils.d("cid2-->" + cid);
                }
            });
        } else if ("block".equals(listStyle)) {
            final List<MainListItemsModel.GoodsgroupBean.GoodsBeanX> goodsData = item.getGoods();
            mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            mAdapter = new HomeGoodsListAdapter(R.layout.home_goods_list_items_gridview_items, goodsData);
            mAdapter.setNewData(goodsData);
            mAdapter.notifyDataSetChanged();
            //     initAdapter();
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String cid = goodsData.get(position).getId();
                    Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", cid);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                    LogUtils.d("cid2-->" + cid);
                }
            });
        }
    }

    private void initAdapter() {

    }

    public class HomeGoodsListAdapter extends BaseQuickAdapter<MainListItemsModel.GoodsgroupBean.GoodsBeanX, BaseViewHolder> {

        public HomeGoodsListAdapter(int layoutResId, List<MainListItemsModel.GoodsgroupBean.GoodsBeanX> data) {
            super(R.layout.home_goods_list_items_gridview_items, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MainListItemsModel.GoodsgroupBean.GoodsBeanX item) {
            DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
            int w_screen = dm.widthPixels;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) helper.getView(R.id.goods_url).getLayoutParams();
            if ("block three".equals(listStyle)) {
                params.width = w_screen / 3 - 30;
                params.height = w_screen / 3 - 30;
            } else if ("block".equals(listStyle)) {
                params.width = w_screen / 2 - 20;
                params.height = w_screen / 2 - 20;
            }
            helper.getView(R.id.goods_url).setLayoutParams(params);
            ImageLoader.getInstance().displayImage(item.getThumb(), (ImageView) helper.getView(R.id.goods_url));
            helper.setText(R.id.tv_titles, item.getTitle());
            helper.setText(R.id.goods_price, "¥ " + item.getMarketprice());
            helper.getView(R.id.tv_buy).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LogUtils.d("点击了");
                }
            });
        }

    }
}