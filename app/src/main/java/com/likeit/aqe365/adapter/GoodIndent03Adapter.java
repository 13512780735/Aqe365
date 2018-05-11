package com.likeit.aqe365.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.network.model.CaseEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class GoodIndent03Adapter extends BaseQuickAdapter<CaseEntity, BaseViewHolder> {
    public GoodIndent03Adapter(int layoutResId, List<CaseEntity> data) {
        super(R.layout.goods_indent_items, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CaseEntity item) {
        baseViewHolder.setText(R.id.tv_indent_number, "订单号：" + "SH20180124064250464768");
        baseViewHolder.setText(R.id.tv_indent_status, "待收货");
        baseViewHolder.setText(R.id.tv_indent_name, "奥泉医销网");
        ImageLoader.getInstance().displayImage(item.getUrl(), (ImageView) baseViewHolder.getView(R.id.iv_shop_avatar));
        baseViewHolder.setText(R.id.tv_shop_name, "观雅 氢氧化钙根管消毒材料Ⅱ型 碘仿糊剂");
        baseViewHolder.setText(R.id.tv_shop_price, "¥ 69.00");
        baseViewHolder.setText(R.id.tv_shop_size, "规格：" + "120ML");
        baseViewHolder.setText(R.id.tv_shop_number, "X" + "1");
        baseViewHolder.setText(R.id.tv_total_number, "共 " + "1" + " 个商品,合计");
        baseViewHolder.setText(R.id.tv_total_price, "¥ 69.00");
        baseViewHolder.getView(R.id.ll_indent_button).setVisibility(View.VISIBLE);
        baseViewHolder.getView(R.id.tv_check_wuLiu).setVisibility(View.VISIBLE);
        baseViewHolder.getView(R.id.tv_confirm_goods).setVisibility(View.VISIBLE);
        baseViewHolder.setText(R.id.tv_confirm_goods, "确认收货");
        baseViewHolder.setText(R.id.tv_check_wuLiu, "查看物流");
        baseViewHolder.addOnClickListener(R.id.tv_check_wuLiu);
        baseViewHolder.addOnClickListener(R.id.tv_confirm_goods);
        baseViewHolder.addOnClickListener(R.id.rl_indent_details);
    }

}
