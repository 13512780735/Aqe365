package com.likeit.aqe365.activity.sort.adapter;


import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.network.model.goods.GoodDetailModel;
import com.likeit.aqe365.utils.LogUtils;

import java.util.List;

public class GoodsDetailsCouponAdapter extends BaseQuickAdapter<GoodDetailModel.DetailSaleBean.CouponBean, BaseViewHolder> {
    public GoodsDetailsCouponAdapter(int layoutResId, List<GoodDetailModel.DetailSaleBean.CouponBean> data) {
        super(R.layout.layout_coupon_items, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GoodDetailModel.DetailSaleBean.CouponBean item) {
     baseViewHolder.setText(R.id.coupon_title,"￥ "+item.getEnough());
     baseViewHolder.setText(R.id.coupon_enough,item.getTitle());
     baseViewHolder.addOnClickListener(R.id.coupon_down);
     baseViewHolder.getView(R.id.coupon_down).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             LogUtils.d("点击了");
         }
     });
    }
}
