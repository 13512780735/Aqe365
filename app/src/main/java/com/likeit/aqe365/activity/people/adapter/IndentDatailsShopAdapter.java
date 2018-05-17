package com.likeit.aqe365.activity.people.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.network.model.CaseEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by admin on 2018/5/14.
 */

public class IndentDatailsShopAdapter extends BaseQuickAdapter<CaseEntity, BaseViewHolder> {
    public IndentDatailsShopAdapter(int layoutResId, List<CaseEntity> data) {
        super(R.layout.layout_indent_details_listview_items, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CaseEntity item) {
        ImageLoader.getInstance().displayImage(item.getUrl(), (ImageView) baseViewHolder.getView(R.id.iv_shop_avatar));
        baseViewHolder.setText(R.id.tv_shop_name, "观雅 氢氧化钙根管消毒材料Ⅱ型 碘仿糊剂");
        baseViewHolder.setText(R.id.tv_shop_price, "¥ 69.00");
        baseViewHolder.setText(R.id.tv_shop_size, "规格：" + "120ML");
        baseViewHolder.setText(R.id.tv_shop_number, "X" + "1");
    }
}
