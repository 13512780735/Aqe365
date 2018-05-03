/*
 Copyright © 2015, 2016 Jenly Yu <a href="mailto:jenly1314@gmail.com">Jenly</a>

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 	http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 */
package com.king.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;

/**
 * @author Jenly
 */
public abstract class BaseFragment extends Fragment implements BaseInterface {

    protected Context context;

    private Dialog dialog;

    private BaseProgressDialog progressDialog;

    protected ViewGroup container;

    protected View rootView;

    protected int curPage;

    protected boolean isStop;
    private CustomDialog dialog01;

    public ViewGroup getContainer() {
        return container;
    }

    public View getActivityRootView() {
        return getActivity().findViewById(android.R.id.content);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        this.container = container;
        rootView = inflater.inflate(inflaterRootView(), container, false);
        curPage = 1;
        initUI();
        initData();
        addListeners();

        if (rootView != null)
            return rootView;

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 设置显示右侧返回按钮
     */
    public void setBackView() {
        View backView = findView(R.id.back_view);
        if (backView == null) {
            return;
        }
        backView.setVisibility(View.VISIBLE);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    /**
     * 设置显示标题
     *
     * @param txt
     */
    public void setTitle(String txt) {
        TextView title =  findView(R.id.title);
        if (title == null) {
            return;
        }
        title.setVisibility(View.VISIBLE);
        title.setText(txt);
    }

    /**
     * 只显示右侧文字以及点击事件
     *
     * @param txt
     * @param onClickListener
     */
    public void setRightText(String txt, View.OnClickListener onClickListener) {
        TextView toolbar_righ_tv = findView(R.id.toolbar_righ_tv);
        if (toolbar_righ_tv == null) {
            return;
        }
        ImageView toolbar_righ_iv = findView(R.id.toolbar_righ_iv);
        if (toolbar_righ_iv == null) {
            return;
        }
        toolbar_righ_iv.setVisibility(View.GONE);
        toolbar_righ_tv.setVisibility(View.VISIBLE);
        toolbar_righ_tv.setText(txt);
        toolbar_righ_tv.setOnClickListener(onClickListener);
    }

    /**
     * 右侧只显示一个图片
     *
     * @param resId
     * @param onClickListener
     */
    public void setRightImage(int resId, View.OnClickListener onClickListener) {
        TextView toolbar_righ_tv =findView(R.id.toolbar_righ_tv);
        if (toolbar_righ_tv == null) {
            return;
        }
        toolbar_righ_tv.setVisibility(View.GONE);
        ImageView toolbar_righ_iv = findView(R.id.toolbar_righ_iv);
        if (toolbar_righ_iv == null) {
            return;
        }
        toolbar_righ_iv.setVisibility(View.VISIBLE);
        toolbar_righ_iv.setImageResource(resId);
        toolbar_righ_iv.setOnClickListener(onClickListener);
    }

    /**
     * 显示文字和图片，可以设置文字内容及字体颜色，图片资源
     *
     * @param txt
     * @param txtColor
     * @param resId
     * @param onClickListener
     */
    public void setRightTextAndImage(String txt, int txtColor, int resId, View.OnClickListener onClickListener) {
        TextView toolbar_righ_tv = findView(R.id.toolbar_righ_tv);
        if (toolbar_righ_tv == null) {
            return;
        }
        toolbar_righ_tv.setVisibility(View.VISIBLE);
        toolbar_righ_tv.setTextColor(txtColor);

        ImageView toolbar_righ_iv = findView(R.id.toolbar_righ_iv);
        if (toolbar_righ_iv == null) {
            return;
        }
        toolbar_righ_iv.setVisibility(View.VISIBLE);
        toolbar_righ_iv.setImageResource(resId);

        toolbar_righ_iv.setOnClickListener(onClickListener);
        toolbar_righ_tv.setOnClickListener(onClickListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }


    @Override
    public void onResume() {
        super.onResume();
        isStop = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        isStop = true;
        dismissProgressDialog();
    }

    @Override
    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        return super.getLayoutInflater(savedInstanceState);
    }

    protected View inflate(@LayoutRes int id) {
        return inflate(id, null);
    }

    protected View inflate(@LayoutRes int id, @Nullable ViewGroup root) {
        return LayoutInflater.from(context).inflate(id, root);
    }

    protected <T extends View> T findView(int resId) {
        return (T) rootView.findViewById(resId);
    }

    protected void setOnClickListener(@IdRes int id, View.OnClickListener onClicklistener) {
        findView(id).setOnClickListener(onClicklistener);
    }
    //-----------------------------------

    protected Intent getIntent() {
        return getActivity().getIntent();
    }

    protected Intent getIntent(Class<?> cls) {
        return new Intent(context, cls);
    }

    protected Intent getIntent(Class<?> cls, int flags) {
        Intent intent = getIntent(cls);
        intent.setFlags(flags);
        return intent;
    }

    protected void startActivity(Class<?> cls) {
        startActivity(getIntent(cls));
    }

    protected void startActivity(Class<?> cls, int flags) {
        startActivity(getIntent(cls, flags));
    }

    protected void startActivityFinish(Class<?> cls) {
        startActivity(cls);
        finish();
    }

    protected void startActivityFinish(Class<?> cls, int flags) {
        startActivity(cls, flags);
        finish();
    }

    protected void finish() {
        getActivity().finish();
    }

    protected void setResult(int resultCode) {
        setResult(resultCode, getIntent());
    }

    protected void setResult(int resultCode, Intent intent) {
        getActivity().setResult(resultCode, intent);
    }

    protected void setIntent(Intent newIntent) {
        getActivity().setIntent(newIntent);
    }

    //-----------------------------------


    public void replaceChildFragment(@IdRes int resId, Fragment fragment) {
        replaceFragment(getChildFragmentManager(), resId, fragment, false);
    }

    public void replaceFragment(@IdRes int resId, Fragment fragment) {
        replaceFragment(resId, fragment, false);
    }

    public void replaceFragment(@IdRes int resId, Fragment fragment, boolean isBackStack) {
        replaceFragment(getFragmentManager(), resId, fragment, isBackStack);
    }

    public void replaceFragment(FragmentManager fragmentManager, @IdRes int resId, Fragment fragment, boolean isBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(resId, fragment);
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    //-----------------------------------


    protected void showToast(@StringRes int resId) {
        if (resId != NONE)
            ToastUtils.showToast(context, resId);
    }

    protected void showLongToast(@StringRes int resId) {
        if (resId != NONE)
            ToastUtils.showToast(context, resId, Toast.LENGTH_LONG);
    }

    protected void showToast(CharSequence text) {
        //ToastUtils.showToast(context,text);
    }

    /**
     * 显示字符串消息
     *
     * @param message
     */
    public void showProgress(String message) {
        // dialog = new CustomDialog(getActivity());
        dialog01 = new CustomDialog(getActivity()).builder()
                .setGravity(Gravity.CENTER).setTitle("提示", getResources().getColor(R.color.sd_color_black))//可以不设置标题颜色，默认系统颜色
                .setSubTitle(message);
        dialog01.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1000);
    }

    protected void showLongToast(CharSequence text) {
        ToastUtils.showToast(context, text, Toast.LENGTH_LONG);
    }

    //-----------------------------------

    public boolean checkInput(TextView tv) {
        return checkInput(tv, NONE);
    }

    public boolean checkInput(TextView tv, @StringRes int resId) {
        return checkInput(tv, resId, false);
    }

    public boolean checkInput(TextView tv, @StringRes int resId, boolean isShake) {

        if (StringUtils.isBlank(tv.getText())) {
            if (isShake)
                startShake(tv, resId);
            else
                showToast(resId);
            return false;
        }

        return true;
    }

    public void startShake(View v, @StringRes int resId) {
        startShake(v);
        showToast(resId);
    }

    public void startShake(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake));
    }


    /**
     * 隐藏软键盘
     *
     * @param v
     */
    public void hideInputMethod(final EditText v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

    }

    /**
     * 显示软键盘
     *
     * @param v
     */
    public void showInputMethod(final EditText v) {

        v.requestFocus();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, 0);
    }

    //-----------------------------------

    public Dialog getProgressDialog() {
        return progressDialog;
    }

    public Dialog getDialog() {
        return dialog;
    }

    protected void dismissProgressDialog() {
        dismissDialog(progressDialog);
    }

    protected void dismissDialog() {
        dismissDialog(dialog);
    }

    protected void dismissDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    protected void dismissDialogFragment(DialogFragment dialogFragment) {
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }

    protected void showProgressDialog() {
        showProgressDialog(new ProgressBar(context));
    }

    protected void showProgressDialog(@LayoutRes int resId) {
        showProgressDialog(LayoutInflater.from(context).inflate(resId, null));
    }

    protected void showProgressDialog(View v) {
        dismissProgressDialog();
        progressDialog = BaseProgressDialog.newInstance(context);
        progressDialog.setContentView(v);
        progressDialog.show();
    }


    public void showDialogFragment(DialogFragment dialogFragment) {
        String tag = dialogFragment.getTag() != null ? dialogFragment.getTag() : dialogFragment.getClass().getSimpleName();
        showDialogFragment(dialogFragment, tag);
    }

    public void showDialogFragment(DialogFragment dialogFragment, String tag) {
        dialogFragment.show(getFragmentManager(), tag);
    }

    protected void showDialog(View contentView) {
        showDialog(context, contentView);
    }

    protected void showDialog(Context context, View contentView) {
        dismissDialog();
        dialog = new Dialog(context, R.style.dialog);
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(false);
        getDialogWindow(dialog);
        dialog.show();

    }

    protected void getDialogWindow(Dialog dialog) {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (getWidthPixels() * 0.9f);
        window.setAttributes(lp);
    }

    //-----------------------------------

    protected void asyncThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    //-----------------------------------

    protected DisplayMetrics getDisplayMetrics() {
        return getResources().getDisplayMetrics();
    }

    protected int getWidthPixels() {
        return getDisplayMetrics().widthPixels;
    }

    protected int getHeightPixels() {
        return getDisplayMetrics().heightPixels;
    }

    //-----------------------------------


    @LayoutRes
    public abstract int inflaterRootView();
}