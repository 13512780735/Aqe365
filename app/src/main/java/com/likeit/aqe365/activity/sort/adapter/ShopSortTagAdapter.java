package com.likeit.aqe365.activity.sort.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.network.model.GoodCategory.GoodsCategoryModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * User: Liumj(liumengjie@365tang.cn)
 * Date: 2016-10-11
 * Time: 16:32
 * describe:  药品标签
 */
public class ShopSortTagAdapter extends BaseQuickAdapter<GoodsCategoryModel.ListBean.TwotierBean.GoodsBean, BaseViewHolder> {
    public ShopSortTagAdapter(int layoutResId, List<GoodsCategoryModel.ListBean.TwotierBean.GoodsBean> data) {
        super(R.layout.item_medical_tv, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GoodsCategoryModel.ListBean.TwotierBean.GoodsBean item) {
        baseViewHolder.setText(R.id.tv_name, item.getName());
        ImageLoader.getInstance().displayImage(item.getThumb(), (ImageView) baseViewHolder.getView(R.id.iv_avatar));
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
