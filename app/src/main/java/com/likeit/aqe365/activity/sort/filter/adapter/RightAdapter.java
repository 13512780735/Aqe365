package com.likeit.aqe365.activity.sort.filter.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.sort.filter.bean.ShopSortItemBean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public class RightAdapter extends BaseQuickAdapter<ShopSortItemBean, BaseViewHolder> {
    private int selectPos=-1;
    public RightAdapter(int layoutResId, List<ShopSortItemBean> data) {
        super(R.layout.item_filter_listview_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopSortItemBean bean) {
        if(selectPos==helper.getAdapterPosition()){
           // helper.setVisible(R.id.item_main_left_bg,true);
            helper.convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            helper.setTextColor(R.id.item_main_left_type, Color.parseColor("#FF4081"));
        }else{
            helper.convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            helper.setTextColor(R.id.item_main_left_type, Color.parseColor("#333333"));
          //  helper.setVisible(R.id.item_main_left_bg,false);
        }

        helper.setText(R.id.item_main_left_type,bean.getName());
    }
    public int getSelectPos() {
        return selectPos;
    }

    public void setSelectPos(int selectPos) {
        this.selectPos = selectPos;
    }
}
