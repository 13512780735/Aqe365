package com.likeit.aqe365.activity.brand.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.brand.model.BrandModel;
import com.likeit.aqe365.activity.sort.goods.GoodsDetailsActivity;
import com.likeit.aqe365.utils.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class BrandRecommendAdapter extends BaseQuickAdapter<BrandModel.LogocategoryBean.RecommendBean, BaseViewHolder> {

    public BrandRecommendAdapter(int layoutResId, List<BrandModel.LogocategoryBean.RecommendBean> data) {
        super(R.layout.brand_recommend_list_items, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final BrandModel.LogocategoryBean.RecommendBean item) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) baseViewHolder.getView(R.id.goods_url).getLayoutParams();
        params.width = w_screen / 2 - 20;
        params.height = w_screen / 2 - 20;
        ImageLoader.getInstance().displayImage(item.getThumb(), (ImageView) baseViewHolder.getView(R.id.goods_url));
        baseViewHolder.getView(R.id.goods_url).setLayoutParams(params);
        baseViewHolder.setText(R.id.tv_titles, item.getTitle());
        baseViewHolder.setText(R.id.goods_price, "¥ " + item.getMarketprice());
        baseViewHolder.setText(R.id.goods_old_price, "¥ " + item.getProductprice());
        ((TextView) baseViewHolder.getView(R.id.goods_old_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        baseViewHolder.setText(R.id.tv_sales, "销量:" + item.getShowsales());
        baseViewHolder.getView(R.id.tv_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cid = item.getId();
                Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", cid);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
                LogUtils.d("id-->" + cid);
            }
        });
    }
}