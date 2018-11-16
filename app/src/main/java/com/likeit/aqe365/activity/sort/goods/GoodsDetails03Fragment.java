package com.likeit.aqe365.activity.sort.goods;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.sort.adapter.GoodListAdapter;
import com.likeit.aqe365.activity.sort.bean.CategoryListItemsModel;
import com.likeit.aqe365.activity.sort.product.bean.Product;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.network.model.goods.GoodDetailModel;
import com.likeit.aqe365.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsDetails03Fragment extends BaseFragment {

    private GoodDetailModel goodDetailModel;
    private Product product;
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    private List<GoodDetailModel.ParameterBean> data = new ArrayList<>();
    private GoodDetailsArgumentsAdapter mAdapter;

    @Override
    protected int setContentView() {
        return R.layout.fragment_goods_details03;
    }

    @Override
    protected void lazyLoad() {
        goodDetailModel = (GoodDetailModel) getArguments().getSerializable("goodDetailModel");
        product = (Product) getArguments().getParcelable("product");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        data = goodDetailModel.getParameter();
        initAdapter();

    }

    private void initAdapter() {
        mAdapter = new GoodDetailsArgumentsAdapter(R.layout.good_details_argument_items, data);
        // mAdapter.setOnLoadMoreListener(getActivity(), mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public class GoodDetailsArgumentsAdapter extends BaseQuickAdapter<GoodDetailModel.ParameterBean, BaseViewHolder> {
        public GoodDetailsArgumentsAdapter(int layoutResId, List<GoodDetailModel.ParameterBean> data) {
            super(R.layout.good_details_argument_items, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, GoodDetailModel.ParameterBean item) {
            baseViewHolder.setText(R.id.tv_name, item.getTitle());
            baseViewHolder.setText(R.id.tv_value, item.getValue());

        }
    }
}
