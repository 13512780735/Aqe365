package com.likeit.aqe365.activity.sort.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.network.model.goods.GoodsSalesModel;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class GoodsSalesAdapter extends BaseQuickAdapter<GoodsSalesModel.ListBean, BaseViewHolder> {
    public GoodsSalesAdapter() {
        super(R.layout.goods_sales_items);
    }

    private TagItemOnClick tagItemOnClick;

    public interface TagItemOnClick {
        void onItemClick(View view, int position, int positions);
    }

    public void setTagItemOnClickListener(TagItemOnClick listener) {
        tagItemOnClick = listener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GoodsSalesModel.ListBean item) {
        final TagFlowLayout mFlowLayout = helper.getView(R.id.flow_layout_hot);
        helper.setText(R.id.tv_type, item.getName());
        final List<String> listString = new ArrayList<>();
        for (int i = 0; i < item.getSpec().size(); i++) {
            listString.add(item.getSpec().get(i).getTitle());
        }
        final LayoutInflater mInflater = LayoutInflater.from(mContext);
        mFlowLayout.setAdapter(new TagAdapter<String>(listString) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_shop_group_text,
                        mFlowLayout, false);
                tv.setText(s);
                if (item.getSpec().get(position).isSelect() && item.getSpec().get(position).isCanSelect()) {
                    tv.setBackgroundResource(R.drawable.bg_btn_base);
                    tv.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    tv.setBackgroundResource(R.drawable.bg_btn_gray);
                    tv.setTextColor(Color.parseColor("#565656"));
                }
                if (item.getSpec().get(position).isCanSelect()) {
                    tv.setEnabled(true);

                } else {
                    tv.setEnabled(false);

                }
                return tv;
            }
        });
        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (tagItemOnClick != null) {
                    tagItemOnClick.onItemClick(view, position, helper.getPosition());
                }
                return true;
            }
        });
        mFlowLayout.setMaxSelectCount(1);
        helper.addOnClickListener(R.id.flow_layout_hot);
    }
}
