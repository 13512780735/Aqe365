package com.likeit.aqe365.activity.sort.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.sort.filter.GoodListActivity;
import com.likeit.aqe365.network.model.GoodCategory.GoodsCategoryModel;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.ToastUtils;

import java.util.List;

/**
 * User: Liumj(liumengjie@365tang.cn)
 * Date: 2016-10-11
 * Time: 15:51
 * describe:  右边适配器
 */
public class RightAdapter extends BaseQuickAdapter<GoodsCategoryModel.ListBean.TwotierBean, BaseViewHolder> {

    public RightAdapter(int layoutResId, List<GoodsCategoryModel.ListBean.TwotierBean> data) {
        super(R.layout.item_main_right, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GoodsCategoryModel.ListBean.TwotierBean listBean) {
        helper.setText(R.id.item_main_right_title, listBean.getName());
        // TagFlowLayout flowlayout = helper.getView(R.id.item_main_right_taglayout);
        RecyclerView mRecyclerView = helper.getView(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        final List<GoodsCategoryModel.ListBean.TwotierBean.GoodsBean> drugItemBeen = listBean.getGoods();
        final ShopSortTagAdapter drugAdapter = new ShopSortTagAdapter(R.layout.item_medical_tv, drugItemBeen);
        mRecyclerView.setAdapter(drugAdapter);
        drugAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsCategoryModel.ListBean.TwotierBean.GoodsBean goodsBean = drugItemBeen.get(position);
                LogUtils.d("position--》" + position);
                LogUtils.d("name--》" + goodsBean.getName());
                String cid = goodsBean.getId();
                Intent intent = new Intent(mContext, GoodListActivity.class);
                intent.putExtra("cid", cid);
                mContext.startActivity(intent);
            }
        });
//		flowlayout.setAdapter(drugAdapter);
//		flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//			@Override
//			public boolean onTagClick(View view, int position, FlowLayout parent) {
//				ShopSortItemBean drugItemBean = drugItemBeen.get(position);
//				for (ShopSortItemBean b:
//				     drugItemBeen) {
//					b.setCheck(false);
//				}
//				drugItemBean.setCheck(true);
//				drugAdapter.notifyDataChanged();
//				Snackbar.make(helper.convertView, "点击了"+drugItemBean.getName(), Snackbar.LENGTH_SHORT).show();
//				return false;
//			}
//		});


    }
}
