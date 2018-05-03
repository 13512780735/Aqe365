package com.likeit.aqe365.activity.login.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.king.base.BaseFragment;
import com.likeit.aqe365.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterProtocolFragment extends BaseFragment {


    private TextView tv_confirm;

    public static RegisterProtocolFragment newInstance() {
        Bundle bundle = new Bundle();
        RegisterProtocolFragment fragment = new RegisterProtocolFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int inflaterRootView() {
        return R.layout.fragment_register_protocol;
    }

    @Override
    public void initUI() {
        setBackView();
        setTitle("注册协议");
        tv_confirm = findView(R.id.tv_confirm);

    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
