package com.likeit.aqe365.network.util;


import com.likeit.aqe365.activity.brand.model.BrandModel;
import com.likeit.aqe365.activity.brand.model.BrandRefreshModel;
import com.likeit.aqe365.activity.sort.bean.CartDeleteModel;
import com.likeit.aqe365.activity.sort.bean.CategoryListItemsModel;
import com.likeit.aqe365.network.ApiService;
import com.likeit.aqe365.network.consts.Consts;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.EmptyEntity;
import com.likeit.aqe365.network.model.GoodCategory.GoodsCategoryModel;
import com.likeit.aqe365.network.model.Indent.IndentListModel;
import com.likeit.aqe365.network.model.Indent.OrderCreateModel;
import com.likeit.aqe365.network.model.LoginRegisterModel;
import com.likeit.aqe365.network.model.ThirdLoginModel;
import com.likeit.aqe365.network.model.cart.CartListModel;
import com.likeit.aqe365.network.model.goods.AddressModel;
import com.likeit.aqe365.network.model.goods.CaculateModel;
import com.likeit.aqe365.network.model.goods.GoodDetailModel;
import com.likeit.aqe365.network.model.goods.GoodsSalesModel;
import com.likeit.aqe365.network.model.goods.PayIndentModel;
import com.likeit.aqe365.network.model.goods.ProvincesModel;
import com.likeit.aqe365.network.model.home.MainListItemsModel;
import com.likeit.aqe365.network.model.pay.AlipayPayAModel;
import com.likeit.aqe365.network.model.pay.WechatPayModel;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author nanchen
 * @fileName RetrofitRxDemoo
 * @packageName com.nanchen.retrofitrxdemoo.util
 * @date 2016/12/12  10:38
 */

public class RetrofitUtil {

    public static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private ApiService mApiService;

    private static RetrofitUtil mInstance;

    /**
     * 私有构造方法
     */
    private RetrofitUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build())
                .baseUrl(Consts.APP_HOST)
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public static RetrofitUtil getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }

    /**
     * 用于用户登录
     *
     * @param subscriber
     */
    public void getUsersLogin(String mobile, String pwd, String signature, String newtime, String random, Subscriber<BaseResponse<LoginRegisterModel>> subscriber) {
        mApiService.UserLogin(mobile, pwd, signature, newtime, random)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 用于用户注册
     *
     * @param subscriber
     */
    public void getUsersRegister(String mobile, String pwd, int verifycode, String signature, String newtime, String random, Subscriber<BaseResponse<LoginRegisterModel>> subscriber) {
        mApiService.UserRegister(mobile, pwd, verifycode, signature, newtime, random)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 短信接口
     *
     * @param subscriber
     */
    public void getVerifycode(String mobile, String temp, String signature, String newtime, String random, Subscriber<BaseResponse<EmptyEntity>> subscriber) {
        mApiService.GetVerifycode(mobile, temp, signature, newtime, random)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 用户修改密码与找回密码
     *
     * @param subscriber
     */
    public void UserChangePwd(String mobile, String pwd, String verifycode, String pwdtrue, String signature, String newtime, String random, Subscriber<BaseResponse<EmptyEntity>> subscriber) {
        mApiService.UserChangePwd(mobile, pwd, verifycode, pwdtrue, signature, newtime, random)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 商品分類
     *
     * @param subscriber
     */
    public void GoodsCategory(String token, String signature, String newtime, String random, Subscriber<BaseResponse<GoodsCategoryModel>> subscriber) {
        mApiService.GoodsCategory(token, signature, newtime, random)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 商品列表
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param subscriber
     */

    public void CategoryList(String token, String cid, String signature, String newtime, String random, String page, String isnew, String ishot, String isrecommand, String isdiscount, String istime, String issendfree, String keyword,
                             Subscriber<BaseResponse<CategoryListItemsModel>> subscriber) {
        mApiService.CategoryList(token, cid, signature, newtime, random, page, isnew, ishot, isrecommand, isdiscount, istime, issendfree, keyword)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 首頁列表
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param subscriber
     */

    public void MainList(String token, String signature, String newtime, String random,
                         Subscriber<BaseResponse<MainListItemsModel>> subscriber) {
        mApiService.MainList(token, signature, newtime, random)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 我的订单
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param status
     * @param page
     * @param subscriber
     */
    public void Orderform(String token, String signature, String newtime, String random, String status, String page,
                          Subscriber<BaseResponse<IndentListModel>> subscriber) {
        mApiService.orderform(token, signature, newtime, random, status, page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 商品详情
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param id
     * @param subscriber
     */

    public void goodsDetails(String token, String signature, String newtime, String random, String id,
                             Subscriber<BaseResponse<GoodDetailModel>> subscriber) {
        mApiService.goodsDetails(token, signature, newtime, random, id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 商品收藏
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param id
     * @param status
     * @param subscriber
     */
    public void goodsCollect(String token, String signature, String newtime, String random, String id, String status,
                             Subscriber<BaseResponse<EmptyEntity>> subscriber) {
        mApiService.goodsCollect(token, signature, newtime, random, id, status)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 商品规格
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param id
     * @param subscriber
     */
    public void goodsSales(String token, String signature, String newtime, String random, String id,
                           Subscriber<BaseResponse<GoodsSalesModel>> subscriber) {
        mApiService.goodsSales(token, signature, newtime, random, id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 獲取地址列表
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param subscriber
     */
    public void getAddressList(String token, String signature, String newtime, String random,
                               Subscriber<BaseResponse<AddressModel>> subscriber) {
        mApiService.getAddressList(token, signature, newtime, random)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 设置默认地址
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param id
     * @param subscriber
     */
    public void setDefaultAddress(String token, String signature, String newtime, String random, String id,
                                  Subscriber<BaseResponse<EmptyEntity>> subscriber) {
        mApiService.setDefaultAddress(token, signature, newtime, random, id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取省市区
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param subscriber
     */
    public void getProvinces(String token, String signature, String newtime, String random,
                             Subscriber<BaseResponse<ProvincesModel>> subscriber) {
        mApiService.getProvinces(token, signature, newtime, random)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 删除地址
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param id
     * @param subscriber
     */
    public void deleteAddress(String token, String signature, String newtime, String random, String id,
                              Subscriber<BaseResponse<EmptyEntity>> subscriber) {
        mApiService.deleteAddress(token, signature, newtime, random, id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 添加编辑地址
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param id
     * @param address
     * @param realname
     * @param mobile
     * @param province
     * @param city
     * @param area
     * @param subscriber
     */
    public void setAddress(String token, String signature, String newtime, String random, String id, String address, String realname, String mobile, String province, String city, String area,
                           Subscriber<BaseResponse<EmptyEntity>> subscriber) {
        mApiService.setAddress(token, signature, newtime, random, id, address, realname, mobile, province, city, area)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 确认订单
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param id
     * @param optionid
     * @param total
     * @param subscriber
     */
    public void OrderCreate(String token, String signature, String newtime, String random, String id, String optionid, String total,
                            Subscriber<BaseResponse<OrderCreateModel>> subscriber) {
        mApiService.OrderCreate(token, signature, newtime, random, id, optionid, total)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 确认订单数量选择
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param goodsid
     * @param optionid
     * @param total
     * @param addressid
     * @param subscriber
     */
    public void getCaculate(String token, String signature, String newtime, String random, String goodsid, String optionid, String total, String addressid,
                            Subscriber<BaseResponse<CaculateModel>> subscriber) {
        mApiService.getCaculate(token, signature, newtime, random, goodsid, optionid, total, addressid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 生成订单（商品详情）
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param goodsid
     * @param optionid
     * @param total
     * @param addressid
     * @param subscriber
     */
    public void submitorder(String token, String signature, String newtime, String random, String goodsid, String optionid, String total, String addressid,
                            Subscriber<BaseResponse<PayIndentModel>> subscriber) {
        mApiService.submitorder(token, signature, newtime, random, goodsid, optionid, total, addressid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 支付宝支付
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param tid
     * @param money
     * @param subscriber
     */
    public void appAlipay(String token, String signature, String newtime, String random, String tid, String money,
                          Subscriber<BaseResponse<AlipayPayAModel>> subscriber) {
        mApiService.appAlipay(token, signature, newtime, random, tid, money)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 微信支付
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param tid
     * @param money
     * @param subscriber
     */
    public void appWechat(String token, String signature, String newtime, String random, String tid, String money,
                          Subscriber<BaseResponse<WechatPayModel>> subscriber) {
        mApiService.appWechat(token, signature, newtime, random, tid, money)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 添加购物车
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param id
     * @param total
     * @param optionid
     * @param subscriber
     */
    public void addcart(String token, String signature, String newtime, String random, String id, String total, String optionid,
                        Subscriber<BaseResponse<EmptyEntity>> subscriber) {
        mApiService.addcart(token, signature, newtime, random, id, total, optionid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 购物车列表
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param subscriber
     */
    public void getCartList(String token, String signature, String newtime, String random,
                            Subscriber<BaseResponse<CartListModel>> subscriber) {
        mApiService.getCartList(token, signature, newtime, random)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 删除购物车
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param id
     * @param subscriber
     */
    public void removeCart(String token, String signature, String newtime, String random, String ids,
                           Subscriber<BaseResponse<CartDeleteModel>> subscriber) {
        mApiService.removeCart(token, signature, newtime, random, ids)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 购物车确认订单
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param cartids
     * @param cartnum
     * @param subscriber
     */
    public void CreateCartOrder(String token, String signature, String newtime, String random, String cartids, String cartnum,
                                Subscriber<BaseResponse<OrderCreateModel>> subscriber) {
        mApiService.CreateCartOrder(token, signature, newtime, random, cartids, cartnum)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 购物车生成订单
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param cartids
     * @param cartoption
     * @param carttotal
     * @param addressid
     * @param subscriber
     */
    public void CreateCartSubmitorder(String token, String signature, String newtime, String random, String cartids, String cartoption, String carttotal, String addressid
            ,
                                      Subscriber<BaseResponse<PayIndentModel>> subscriber) {
        mApiService.CreateCartSubmitorder(token, signature, newtime, random, cartids, cartoption, carttotal, addressid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 判断是否关联帐号
     *
     * @param openid
     * @param type
     * @param subscriber
     */
    public void ThirdLogin(String openid, String type
            ,
                           Subscriber<BaseResponse<LoginRegisterModel>> subscriber) {
        mApiService.ThirdLogin(openid, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 关联帐号
     *
     * @param openid
     * @param type
     * @param moblie
     * @param pwd
     * @param subscriber
     */
    public void snsBind(String openid, String type, String mobile, String pwd,
                        Subscriber<BaseResponse<LoginRegisterModel>> subscriber) {
        mApiService.snsBind(openid, type, mobile, pwd)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 品牌列表
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param subscriber
     */
    public void getBrands(String token, String signature, String newtime, String random,
                          Subscriber<BaseResponse<BrandModel>> subscriber) {
        mApiService.getBrands(token, signature, newtime, random)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 刷新品牌
     * @param token
     * @param signature
     * @param newtime
     * @param random
     * @param num
     * @param subscriber
     */
    public void brandsRefresh(String token, String signature, String newtime, String random, String num,
                          Subscriber<BaseResponse<BrandRefreshModel>>subscriber) {
        mApiService.brandsRefresh(token, signature, newtime, random, num)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private <T> void toSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
