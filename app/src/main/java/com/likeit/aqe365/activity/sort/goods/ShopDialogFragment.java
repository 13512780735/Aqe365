package com.likeit.aqe365.activity.sort.goods;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.cart.activity.ConfirmOrderActivity;
import com.likeit.aqe365.utils.CustomDialog;
import com.likeit.aqe365.utils.StatusBarUtil;
import com.likeit.aqe365.utils.ToastUtils;
import com.likeit.aqe365.view.AmountView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopDialogFragment extends DialogFragment implements View.OnClickListener {


    private String keys;
    private TextView tvCertain;
    private TextView tv_price;
    private ImageView ivDelete;
    private AmountView mAmountView;
    private int goods_num = 1;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
       // super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int color = getResources().getColor(R.color.white);
        StatusBarUtil.setColor(getActivity(), color, 0);
        StatusBarUtil.setLightMode(getActivity());
        View view = inflater.inflate(R.layout.fragment_shop_dialog, container, false);
        keys = getArguments().getString("keys");//1.加入购物车 2.立即购买
        initView(view);
        initListener();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        DisplayMetrics dm = new DisplayMetrics();

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.5f;

        window.setAttributes(windowParams);
    }
    private void initListener() {
        tvCertain.setOnClickListener(this);
        ivDelete.setOnClickListener(this);
    }

    private void initView(View view) {
        tvCertain = (TextView) view.findViewById(R.id.tv_certain);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        ivDelete = (ImageView) view.findViewById(R.id.iv_delete);
        mAmountView = (AmountView) view.findViewById(R.id.amount_view);
        mAmountView.setGoods_storage(50);
        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                // Toast.makeText(getActivity(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
                goods_num = amount;
                Log.d("TAG", "goods_num-->" + amount);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_certain:
                if ("1".equals(keys)) {
                    ToastUtils.showToast(getActivity(), "加入购物车成功");
                    getDialog().dismiss();
                    //添加购物车
                } else if ("2".equals(keys)) {
                    Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                    getActivity().startActivity(intent);
                }
                break;
            case R.id.iv_delete:
                getDialog().dismiss();
                break;
        }
    }
}