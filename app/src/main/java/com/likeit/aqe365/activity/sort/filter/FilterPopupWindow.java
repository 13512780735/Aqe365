package com.likeit.aqe365.activity.sort.filter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Set;

/**
 * Created by admin on 2018/5/21.
 */

public class FilterPopupWindow extends PopupWindow implements View.OnClickListener {

    private TagFlowLayout mFlowLayout;
    private String[] mValues = new String[]
            {
                    "推荐商品", "新品上市", "热卖商品",
                    "促销商品", "卖家包邮", "限时抢购"
            };
    private TextView tvCancel, tvConfirm;

    public FilterPopupWindow(View parent, final Context context) {
        super(context);

        View view = View.inflate(context, R.layout.good_filter_popwindows_items, null);
        //设置PopupWindows 显示动画
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        setOutsideTouchable(false);
        setContentView(view);
        setBackgroundDrawable(null);
        update();
        initUI(view, context);

    }

    private void initUI(View view, Context context) {
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvConfirm = view.findViewById(R.id.tv_confirm);
        mFlowLayout = view.findViewById(R.id.item_main_right_taglayout);
        final LayoutInflater mInflater = LayoutInflater.from(context);
        mFlowLayout.setAdapter(new TagAdapter<String>(mValues) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_filter_tv,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }

            @Override
            public boolean setSelected(int position, String s) {
                return super.setSelected(position, s);
            }
        });

        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                //Toast.makeText(getActivity(), mValues[position], Toast.LENGTH_SHORT).show();
                //view.setVisibility(View.GONE);
                return true;
            }
        });


        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                // getActivity().setTitle("choose:" + selectPosSet.toString());
            }
        });
        tvCancel.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_confirm:
                dismiss();
                break;
        }
    }
}
