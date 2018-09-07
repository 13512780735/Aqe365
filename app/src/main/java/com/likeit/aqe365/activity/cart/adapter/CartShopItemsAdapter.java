package com.likeit.aqe365.activity.cart.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.network.model.Indent.OrderCreateModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by admin on 2018/5/17.
 */

public class CartShopItemsAdapter extends BaseQuickAdapter<OrderCreateModel.GoodsListBean.GoodsBean, BaseViewHolder> {
    public CartShopItemsAdapter(int layoutResId, List<OrderCreateModel.GoodsListBean.GoodsBean> data) {
        super(R.layout.layout_cart_shopitems_view, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderCreateModel.GoodsListBean.GoodsBean item) {
        ImageLoader.getInstance().displayImage(item.getThumb(), (ImageView) baseViewHolder.getView(R.id.iv_shop_avatar));
        baseViewHolder.setText(R.id.tv_shop_name, item.getTitle());
        baseViewHolder.setText(R.id.tv_shop_price, "¥ " +item.getMarketprice());
        baseViewHolder.setText(R.id.tv_shop_size, "规格：" + item.getOptiontitle());
        baseViewHolder.setText(R.id.tv_shop_number, "X" + item.getTotal());
    }
}
