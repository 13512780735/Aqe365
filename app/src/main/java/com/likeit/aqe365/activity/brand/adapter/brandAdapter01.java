package com.likeit.aqe365.activity.brand.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.brand.model.BrandModel;
import com.likeit.aqe365.activity.brand.model.LogoBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class brandAdapter01 extends BaseQuickAdapter<LogoBean, BaseViewHolder> {
    public brandAdapter01(int layoutResId, List<LogoBean> data) {
        super(R.layout.layout_brand_gridview_items, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogoBean item) {
        ImageLoader.getInstance().displayImage(item.getAdvimg(), (ImageView) helper.getView(R.id.iv_people_avatar));
    }
}