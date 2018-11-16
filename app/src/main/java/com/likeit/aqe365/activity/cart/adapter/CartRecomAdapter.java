package com.likeit.aqe365.activity.cart.adapter;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.network.model.cart.CartListModel;
import com.likeit.aqe365.utils.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class CartRecomAdapter extends BaseQuickAdapter<CartListModel.RecomsBean.ListBean, BaseViewHolder> {
    public CartRecomAdapter(int layoutResId, List<CartListModel.RecomsBean.ListBean> data) {
        super(R.layout.cart_recom_items, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CartListModel.RecomsBean.ListBean item) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) baseViewHolder.getView(R.id.goods_url).getLayoutParams();
            params.width = w_screen / 3 - 30;
            params.height = w_screen / 3 - 30;
        baseViewHolder.getView(R.id.goods_url).setLayoutParams(params);
        ImageLoader.getInstance().displayImage(item.getThumb(), (ImageView) baseViewHolder.getView(R.id.goods_url));
        baseViewHolder.setText(R.id.tv_titles, item.getTitle());
        baseViewHolder.setText(R.id.goods_price, "¥ " + item.getMarketprice());
        baseViewHolder.getView(R.id.tv_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.d("点击了");
            }
        });
    }
}
