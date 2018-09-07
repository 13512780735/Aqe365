package com.likeit.aqe365.activity.sort.goods;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.likeit.aqe365.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsSalesFragment extends DialogFragment implements View.OnClickListener {


    public GoodsSalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goods_sales, container, false);
    }

    @Override
    public void onClick(View view) {

    }
}
