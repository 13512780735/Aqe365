package com.likeit.aqe365.activity.cart.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.network.model.CaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/17.
 */

public class CartShopListAdatper extends BaseQuickAdapter<CaseEntity, BaseViewHolder> {
    private List<CaseEntity> datas;
    private CartShopItemsAdapter mAdapter;


    public CartShopListAdatper(int layoutResId, List<CaseEntity> data) {
        super(R.layout.layout_cart_shoplist_items, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CaseEntity item) {
        datas=new ArrayList<>();
        initData();
        mAdapter = new CartShopItemsAdapter(R.layout.layout_cart_shopitems_view, datas);
        baseViewHolder.setText(R.id.tv_indent_name, "澳泉医销网" + item.getUrl() + "号店");
        baseViewHolder.setText(R.id.tv_total_number, "共 " + datas.size() + " 件商品，合计: ");
        baseViewHolder.setText(R.id.tv_total_price, "¥ 1000.00");
        RecyclerView mRecyclerView = baseViewHolder.getView(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        for (int i = 0; i <2; i++) {
            CaseEntity caseEntity = new CaseEntity();
            caseEntity.setUrl(i + "");
            datas.add(caseEntity);
        }
    }


}
