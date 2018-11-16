package com.likeit.aqe365.activity.sort;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.sort.bean.GoodFilterItemBean;
import com.likeit.aqe365.utils.StatusBarUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodFilterFragment extends DialogFragment {

    private OnDialogListener mlistener;
    private TagFlowLayout mFlowLayout;
    private String[] mValues = new String[]
            {
                    "推荐商品", "新品上市", "热卖商品",
                    "促销商品", "卖家包邮", "限时抢购"
            };
    private ArrayList<GoodFilterItemBean> list1;
    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//      getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.fragment_good_filter, container, false);
        int color = getResources().getColor(R.color.white);
        StatusBarUtil.setColor(getActivity(), color, 0);
        StatusBarUtil.setLightMode(getActivity());
        list1 = new ArrayList<>();
        initData();
        initUI(view);
        return view;
    }


    private void initData() {
    }

    private void initUI(final View view) {
        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        mFlowLayout = view.findViewById(R.id.item_main_right_taglayout);
        mFlowLayout.setAdapter(new TagAdapter<String>(mValues){

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
//        final GoodFilterTagAdapter mAdapter = new GoodFilterTagAdapter(getActivity(), list1);
//
//        flowlayout.setAdapter(mAdapter);
//        flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//            @Override
//            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                GoodFilterItemBean GoodFilterItemBean = list1.get(position);
//                for (GoodFilterItemBean b :
//                        list1) {
//                    b.setCheck(false);
//                }
//                GoodFilterItemBean.setCheck(true);
//                mAdapter.notifyDataChanged();
//                //Snackbar.make(helper.convertView, "点击了" + GoodFilterItemBean.getName(), Snackbar.LENGTH_SHORT).show();
//                return false;
//            }
//        });

    }

    public interface OnDialogListener {
        void onDialogClick(String person);
    }

    public void setOnDialogListener(OnDialogListener dialogListener) {
        this.mlistener = dialogListener;
    }
}
