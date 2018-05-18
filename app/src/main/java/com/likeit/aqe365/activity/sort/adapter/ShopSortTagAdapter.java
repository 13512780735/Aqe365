package com.likeit.aqe365.activity.sort.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.sort.bean.ShopSortItemBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * User: Liumj(liumengjie@365tang.cn)
 * Date: 2016-10-11
 * Time: 16:32
 * describe:  药品标签
 */
public class ShopSortTagAdapter extends BaseQuickAdapter<ShopSortItemBean,BaseViewHolder> {
	public ShopSortTagAdapter(int layoutResId, List<ShopSortItemBean> data) {
		super(R.layout.item_medical_tv, data);
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, ShopSortItemBean item) {
		baseViewHolder.setText(R.id.tv_name,item.getName());
		ImageLoader.getInstance().displayImage("", (ImageView) baseViewHolder.getView(R.id.iv_avatar));
	}


//	private LayoutInflater mInflater;
//
//	public ShopSortTagAdapter(Context context, List<ShopSortItemBean> datas) {
//		super(datas);
//		mInflater = LayoutInflater.from(context);
//	}
//
//	@Override
//	public View getView(FlowLayout parent, int position, ShopSortItemBean md) {
//		TextView tv = (TextView) mInflater.inflate(R.layout.item_medical_tv,
//				parent, false);
//		TextView tv01= (TextView) mInflater.inflate(R.id.tv_name,parent,false);
//		if(md.isCheck()){
//			tv.setBackgroundResource(R.drawable.shape_wiki_drug_check);
//			tv.setTextColor(Color.parseColor("#FF4081"));
//		}else{
//			tv.setBackgroundResource(R.drawable.shape_wiki_drug_normal);
//			tv.setTextColor(Color.parseColor("#333333"));
//		}
//		tv.setText(md.getName());
//		return tv;
//	}

}
