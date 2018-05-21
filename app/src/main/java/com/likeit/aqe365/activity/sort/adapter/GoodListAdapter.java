package com.likeit.aqe365.activity.sort.adapter;

import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.network.model.CaseEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public class GoodListAdapter extends BaseQuickAdapter<CaseEntity, BaseViewHolder> {
    public GoodListAdapter(int layoutResId, List<CaseEntity> data) {
        super(R.layout.good_listview_items, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CaseEntity item) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) baseViewHolder.getView(R.id.goods_listview_avatar).getLayoutParams();
        params.width = w_screen /2-10;
        params.height = w_screen /2-10;
        baseViewHolder.getView(R.id.goods_listview_avatar).setLayoutParams(params);
        ImageLoader.getInstance().displayImage("", (ImageView) baseViewHolder.getView(R.id.goods_listview_avatar));
        baseViewHolder.setText(R.id.tv_name, "全效多酶清洗液 AQ-404S");
        baseViewHolder.setText(R.id.tv_size, "500ml/瓶");
        baseViewHolder.setText(R.id.tv_price, "¥ " + "88");
        baseViewHolder.setText(R.id.tv_old_price, "¥ " + "100");
        ((TextView) baseViewHolder.getView(R.id.tv_old_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        baseViewHolder.setText(R.id.tv_sales, "销量：" + "88");
    }
}
