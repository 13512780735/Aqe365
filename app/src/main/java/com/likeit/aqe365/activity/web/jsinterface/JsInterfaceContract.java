package com.likeit.aqe365.activity.web.jsinterface;

import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.likeit.aqe365.activity.web.base.BasePresenterInterface;
import com.likeit.aqe365.activity.web.base.BaseView;

interface JsInterfaceContract {
    interface View extends BaseView<Presenter> {
        /**
         * 渲染页面
         *
         * @param url HTML链接
         */
        void renderUrl(@NonNull final String url);

        /**
         * 执行JS
         *
         * @param js js代码
         */
        void execJavaScript(@NonNull final String js);

    }

    interface Presenter extends BasePresenterInterface {
        /**
         * 点击按钮1
         */
        void clickBtn1();

        /**
         * 点击按钮2
         */
        void clickBtn2();
    }
}
