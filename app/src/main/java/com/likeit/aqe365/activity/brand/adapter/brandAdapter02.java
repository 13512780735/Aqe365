package com.likeit.aqe365.activity.brand.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.brand.model.BrandModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class brandAdapter02 extends RecyclerView.Adapter<brandAdapter02.ViewHolder> {

    private Context context;
    private List<BrandModel.LogocategoryBean> data;

    public brandAdapter02(Context context, List<BrandModel.LogocategoryBean> data) {
        this.context = context;
        this.data = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_brand_gridview_items, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ImageLoader.getInstance().displayImage(data.get(position).getAdvertise1(), holder.ivAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.e("这里是点击每一行item的响应事件", "" + position + item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAvatar;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_people_avatar);

        }
    }
}