package com.likeit.aqe365.activity.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.network.model.CaseEntity;

import java.util.List;

/**
 * Created by admin on 2018/5/23.
 */

public class CouponListAdapter extends BaseQuickAdapter<CaseEntity, BaseViewHolder> {
    public CouponListAdapter(int layoutResId, List<CaseEntity> data) {
        super(R.layout.layout_coupon_listview_items, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CaseEntity item) {
        baseViewHolder.setText(R.id.tv_price, "¥ " + 200);
        baseViewHolder.setText(R.id.tv_price_size, "满2000元可用");
        baseViewHolder.setText(R.id.tv_name, "200优惠劵");
        baseViewHolder.setText(R.id.tv_title, "消费满2000立减200");
        baseViewHolder.setText(R.id.tv_time, "永久有效");
        baseViewHolder.setText(R.id.tv_use, "立即领取");
    }
}
