package com.likeit.aqe365.activity.home.adapter;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.network.model.home.MainListItemsModel;
import com.likeit.aqe365.utils.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class HomeHorGoodsAdapter extends BaseQuickAdapter<MainListItemsModel.RecommendBean.GoodsBean, BaseViewHolder> {
    public HomeHorGoodsAdapter(int layoutResId, List<MainListItemsModel.RecommendBean.GoodsBean> data) {
        super(R.layout.home_goods_list_items_gridview_items, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainListItemsModel.RecommendBean.GoodsBean item) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) helper.getView(R.id.goods_url).getLayoutParams();
        params.width = w_screen / 3 - 30;
        params.height = w_screen / 3 - 30;
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
