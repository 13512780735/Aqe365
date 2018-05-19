package com.likeit.aqe365.activity.sort.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.sort.bean.GoodFilterItemBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * Created by admin on 2018/5/19.
 */

public class GoodFilterTagAdapter extends TagAdapter<GoodFilterItemBean> {
    private LayoutInflater mInflater;

    public GoodFilterTagAdapter(Context context, List<GoodFilterItemBean> datas) {
        super(datas);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(FlowLayout parent, int position, GoodFilterItemBean md) {
        TextView tv = (TextView) mInflater.inflate(R.layout.item_filter_tv,
                parent, false);
        if(md.isCheck()){
            tv.setBackgroundResource(R.drawable.shape_wiki_drug_check);
            tv.setTextColor(Color.parseColor("#FF4081"));
        }else{
            tv.setBackgroundResource(R.drawable.shape_wiki_drug_normal);
            tv.setTextColor(Color.parseColor("#333333"));
        }
        tv.setText(md.getName());
        return tv;
    }
}
