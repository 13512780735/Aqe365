package com.likeit.aqe365.activity.sort.filter.adapter;


import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.sort.filter.bean.ShopSortBean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public class LeftAdapter extends BaseQuickAdapter<ShopSortBean,BaseViewHolder> {
    private int selectPos=0;
    public LeftAdapter(int layoutResId, List<ShopSortBean> data) {
        super(R.layout.item_filter_listview_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopSortBean bean) {
        if(selectPos==helper.getAdapterPosition()){
            helper.convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            helper.setTextColor(R.id.item_main_left_type, Color.parseColor("#FF4081"));
        }else{
            helper.convertView.setBackgroundColor(Color.parseColor("#f7f7f7"));
            helper.setTextColor(R.id.item_main_left_type, Color.parseColor("#333333"));
        }

        helper.setText(R.id.item_main_left_type,bean.getTitle());

    }
    public int getSelectPos() {
        return selectPos;
    }

    public void setSelectPos(int selectPos) {
        this.selectPos = selectPos;
    }
}
