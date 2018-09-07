package com.likeit.aqe365.activity.people.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.network.model.CaseEntity;
import com.likeit.aqe365.network.model.Indent.IndentListModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by admin on 2018/5/17.
 */

public class IndentShopListAdapter extends BaseQuickAdapter<IndentListModel.ListBean.GoodsBean, BaseViewHolder> {
    public IndentShopListAdapter(int layoutResId, List<IndentListModel.ListBean.GoodsBean> data) {
        super(R.layout.layout_indent_shop_listitems, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, IndentListModel.ListBean.GoodsBean item) {
        ImageLoader.getInstance().displayImage(item.getThumb(), (ImageView) baseViewHolder.getView(R.id.iv_shop_avatar));
        baseViewHolder.setText(R.id.tv_shop_name, item.getTitle());
        baseViewHolder.setText(R.id.tv_shop_price, "¥ " + item.getPrice());
        baseViewHolder.setText(R.id.tv_shop_size, "规格：" + item.getOptionname());
        baseViewHolder.setText(R.id.tv_shop_number, "X" + item.getTotal());
    }
}
