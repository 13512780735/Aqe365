package com.likeit.aqe365.network;

import com.likeit.aqe365.activity.brand.model.BrandModel;
import com.likeit.aqe365.activity.brand.model.BrandRefreshModel;
import com.likeit.aqe365.activity.sort.bean.CartDeleteModel;
import com.likeit.aqe365.activity.sort.bean.CategoryListItemsModel;
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

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by admin on 2018/5/11.
 */

public interface ApiService {

    /**
     * 商品规格
     */
    public static final String Goods_Sales= Consts.APP_HOST+ "app.goods.andetail_sales";
    /**
     * 用户登录接口
     *
     * @param mobile
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST("app.account.login")
    Observable<BaseResponse<LoginRegisterModel>> UserLogin(@Field("mobile") String mobile,
                                                           @Field("pwd") String pwd,
                                                           @Field("signature") String signature,
                                                           @Field("newtime") String newtime,
                                                           @Field("random") String random
    );


    /**
     * 用户注册接口
     *
     * @param mobile
     * @param pwd
     * @param verifycode
     * @return
     */
    @FormUrlEncoded
    @POST("app.account.register")
    Observable<BaseResponse<LoginRegisterModel>> UserRegister(@Field("mobile") String mobile,
                                                              @Field("pwd") String pwd,
                                                              @Field("verifycode") int verifycode,
                                                              @Field("signature") String signature,
                                                              @Field("newtime") String newtime,
                                                              @Field("random") String random
    );

    /**
     * 短信接口
     * (sms_forget:找回密码) (sms_reg:注册用户)
     *
     * @param mobile
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST("app.account.verifycode")
    Observable<BaseResponse<EmptyEntity>> GetVerifycode(@Field("mobile") String mobile,
                                                        @Field("temp") String pwd,
                                                        @Field("signature") String signature,
                                                        @Field("newtime") String newtime,
                                                        @Field("random") String random
    );

    /**
     * 用户修改密码与找回密码
     *
     * @param mobile
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST("app.account.appchangepwd")
    Observable<BaseResponse<EmptyEntity>> UserChangePwd(@Field("mobile") String mobile,
                                                        @Field("pwd") String pwd,
                                                        @Field("verifycode") String verifycode,
                                                        @Field("pwdtrue") String pwdtrue,
                                                        @Field("signature") String signature,
                                                        @Field("newtime") String newtime,
                                                        @Field("random") String random1
    );

    /**
     * 商品分類
     *
     * @param mid
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @return
     */
    @FormUrlEncoded
    @POST("app.goods.appcategory")
    Observable<BaseResponse<GoodsCategoryModel>> GoodsCategory(@Field("token") String token,
                                                               @Field("signature") String signature,
                                                               @Field("newtime") String newtime,
                                                               @Field("random") String random1
    );

    /**
     * 商品列表
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @return
     */
    @FormUrlEncoded
    @POST("app.goods.categorylist")
    Observable<BaseResponse<CategoryListItemsModel>> CategoryList(@Field("token") String token,
                                                                  @Field("cid") String cid,
                                                                  @Field("signature") String signature,
                                                                  @Field("newtime") String newtime,
                                                                  @Field("random") String random1,
                                                                  @Field("page") String page,
                                                                  @Field("isnew") String isnew,
                                                                  @Field("ishot") String ishot,
                                                                  @Field("isrecommand") String isrecommand,
                                                                  @Field("isdiscount") String isdiscount,
                                                                  @Field("istime") String istime,
                                                                  @Field("issendfree") String issendfree,
                                                                  @Field("keyword") String keyword
    );

    /**
     * 首頁數據
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @return
     */
    @FormUrlEncoded
    @POST("app.index.mainlist")
    Observable<BaseResponse<MainListItemsModel>> MainList(@Field("token") String token,
                                                          @Field("signature") String signature,
                                                          @Field("newtime") String newtime,
                                                          @Field("random") String random1

    );

    /**
     * 我的订单
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param status
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("app.myinfo.orderform")
    Observable<BaseResponse<IndentListModel>> orderform(@Field("token") String token,
                                                        @Field("signature") String signature,
                                                        @Field("newtime") String newtime,
                                                        @Field("random") String random1,
                                                        @Field("status") String status,
                                                        @Field("page") String page

    );

    /**
     * 商品详情
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("app.goods.getgoodsdetail")
    Observable<BaseResponse<GoodDetailModel>> goodsDetails(@Field("token") String token,
                                                           @Field("signature") String signature,
                                                           @Field("newtime") String newtime,
                                                           @Field("random") String random1,
                                                           @Field("id") String id

    );

    /**
     * 商品收藏
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param id
     * @param status
     * @return
     */
    @FormUrlEncoded
    @POST("app.goods.collect")
    Observable<BaseResponse<EmptyEntity>> goodsCollect(@Field("token") String token,
                                                       @Field("signature") String signature,
                                                       @Field("newtime") String newtime,
                                                       @Field("random") String random1,
                                                       @Field("id") String id,
                                                       @Field("status") String status

    );

    /**
     * 商品规格
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("app.goods.andetail_sales")
    Observable<BaseResponse<GoodsSalesModel>> goodsSales(@Field("token") String token,
                                                         @Field("signature") String signature,
                                                         @Field("newtime") String newtime,
                                                         @Field("random") String random1,
                                                         @Field("id") String id

    );

    /**
     * 获取地址列表
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @return
     */
    @FormUrlEncoded
    @POST("app.member.address.getlist")
    Observable<BaseResponse<AddressModel>> getAddressList(@Field("token") String token,
                                                          @Field("signature") String signature,
                                                          @Field("newtime") String newtime,
                                                          @Field("random") String random1
    );

    /**
     * 设置默认地址
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("app.member.address.setdefault")
    Observable<BaseResponse<EmptyEntity>> setDefaultAddress(@Field("token") String token,
                                                            @Field("signature") String signature,
                                                            @Field("newtime") String newtime,
                                                            @Field("random") String random1,
                                                            @Field("id") String id
    );

    /**
     * 获取省市区
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @return
     */
    @FormUrlEncoded
    @POST("app.member.address.getprovinces")
    Observable<BaseResponse<ProvincesModel>> getProvinces(@Field("token") String token,
                                                          @Field("signature") String signature,
                                                          @Field("newtime") String newtime,
                                                          @Field("random") String random1
    );

    /**
     * 删除地址
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("app.member.address.setdelete")
    Observable<BaseResponse<EmptyEntity>> deleteAddress(@Field("token") String token,
                                                        @Field("signature") String signature,
                                                        @Field("newtime") String newtime,
                                                        @Field("random") String random1,
                                                        @Field("id") String id
    );

    /**
     * 添加编辑地址
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param id
     * @param address
     * @param realname
     * @param mobile
     * @param province
     * @param city
     * @param area
     * @return
     */
    @FormUrlEncoded
    @POST("app.member.address.setsubmit")
    Observable<BaseResponse<EmptyEntity>> setAddress(@Field("token") String token,
                                                     @Field("signature") String signature,
                                                     @Field("newtime") String newtime,
                                                     @Field("random") String random1,
                                                     @Field("id") String id,
                                                     @Field("address") String address,
                                                     @Field("realname") String realname,
                                                     @Field("mobile") String mobile,
                                                     @Field("province") String province,
                                                     @Field("city") String city,
                                                     @Field("area") String area);

    /**
     * 商品详情确认订单
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param id
     * @param optionid
     * @param total
     * @return
     */
    @FormUrlEncoded
    @POST("app.order.create")
    Observable<BaseResponse<OrderCreateModel>> OrderCreate(@Field("token") String token,
                                                           @Field("signature") String signature,
                                                           @Field("newtime") String newtime,
                                                           @Field("random") String random1,
                                                           @Field("id") String id,
                                                           @Field("optionid") String optionid,
                                                           @Field("total") String total
    );

    /**
     * 确认订单数量选择
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param goodsid
     * @param optionid
     * @param total
     * @param addressid
     * @return
     */


    @FormUrlEncoded
    @POST("app.order.create.getcaculate")
    Observable<BaseResponse<CaculateModel>> getCaculate(@Field("token") String token,
                                                        @Field("signature") String signature,
                                                        @Field("newtime") String newtime,
                                                        @Field("random") String random1,
                                                        @Field("goodsid") String goodsid,
                                                        @Field("optionid") String optionid,
                                                        @Field("total") String total,
                                                        @Field("addressid") String addressid
    );

    /**
     * 生成订单（商品详情）
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param goodsid
     * @param optionid
     * @param total
     * @param addressid
     * @return
     */
    @FormUrlEncoded
    @POST("app.order.create.submitorder")
    Observable<BaseResponse<PayIndentModel>> submitorder(@Field("token") String token,
                                                         @Field("signature") String signature,
                                                         @Field("newtime") String newtime,
                                                         @Field("random") String random1,
                                                         @Field("goodsid") String goodsid,
                                                         @Field("optionid") String optionid,
                                                         @Field("total") String total,
                                                         @Field("addressid") String addressid
    );

    /**
     * 支付宝支付
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param tid
     * @param money
     * @return
     */
    @FormUrlEncoded
    @POST("app.order.pay.appAlipay")
    Observable<BaseResponse<AlipayPayAModel>> appAlipay(@Field("token") String token,
                                                        @Field("signature") String signature,
                                                        @Field("newtime") String newtime,
                                                        @Field("random") String random1,
                                                        @Field("tid") String tid,
                                                        @Field("money") String money

    );

    /**
     * 微信支付
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param tid
     * @param money
     * @return
     */
    @FormUrlEncoded
    @POST("app.order.pay.appWechat")
    Observable<BaseResponse<WechatPayModel>> appWechat(@Field("token") String token,
                                                       @Field("signature") String signature,
                                                       @Field("newtime") String newtime,
                                                       @Field("random") String random1,
                                                       @Field("tid") String tid,
                                                       @Field("money") String money

    );

    /**
     * 添加购物车
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param id
     * @param total
     * @param optionid
     * @return
     */
    @FormUrlEncoded
    @POST("app.member.cart.addcart")
    Observable<BaseResponse<EmptyEntity>> addcart(@Field("token") String token,
                                                  @Field("signature") String signature,
                                                  @Field("newtime") String newtime,
                                                  @Field("random") String random1,
                                                  @Field("id") String id,
                                                  @Field("total") String total,
                                                  @Field("optionid") String optionid

    );

    /**
     * 购物车列表
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @return
     */
    @FormUrlEncoded
    @POST("app.member.cart.getcart")
    Observable<BaseResponse<CartListModel>> getCartList(@Field("token") String token,
                                                        @Field("signature") String signature,
                                                        @Field("newtime") String newtime,
                                                        @Field("random") String random1

    );

    /**
     * 删除购物车
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("app.member.cart.removecart")
    Observable<BaseResponse<CartDeleteModel>> removeCart(@Field("token") String token,
                                                         @Field("signature") String signature,
                                                         @Field("newtime") String newtime,
                                                         @Field("random") String random1,
                                                         @Field("ids") String ids

    );

    /**
     * 购物车确认订单
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param cartids
     * @param cartnum
     * @return
     */
    @FormUrlEncoded
    @POST("app.order.create")
    Observable<BaseResponse<OrderCreateModel>> CreateCartOrder(@Field("token") String token,
                                                               @Field("signature") String signature,
                                                               @Field("newtime") String newtime,
                                                               @Field("random") String random1,
                                                               @Field("cartids") String cartids,
                                                               @Field("cartnum") String cartnum

    );

    /**
     * 购物车生成订单
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param cartids
     * @param cartoption
     * @param carttotal
     * @param addressid
     * @return
     */
    @FormUrlEncoded
    @POST("app.order.create.submitorder")
    Observable<BaseResponse<PayIndentModel>> CreateCartSubmitorder(@Field("token") String token,
                                                                   @Field("signature") String signature,
                                                                   @Field("newtime") String newtime,
                                                                   @Field("random") String random1,
                                                                   @Field("cartid") String cartids,
                                                                   @Field("cartoption") String cartoption,
                                                                   @Field("carttotal") String carttotal,
                                                                   @Field("addressid") String addressid

    );

    /**
     * 判断是否关联帐号
     */
    @FormUrlEncoded
    @POST("app.account.snslogin")
    Observable<BaseResponse<LoginRegisterModel>> ThirdLogin(@Field("openid") String openid,
                                                            @Field("type") String type

    );

    /**
     * 关联帐号
     */
    @FormUrlEncoded
    @POST("app.account.snsbind")
    Observable<BaseResponse<LoginRegisterModel>> snsBind(@Field("openid") String openid,
                                                         @Field("type") String type,
                                                         @Field("mobile") String mobile,
                                                         @Field("pwd") String pwd

    );

    /**
     * 品牌列表
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @return
     */
    @FormUrlEncoded
    @POST("app.brands.brands.getbrands")
    Observable<BaseResponse<BrandModel>> getBrands(@Field("token") String token,
                                                   @Field("signature") String signature,
                                                   @Field("newtime") String newtime,
                                                   @Field("random") String random1

    );

    /**
     * 品牌刷新
     *
     * @param token
     * @param signature
     * @param newtime
     * @param random1
     * @param num
     * @return
     */
    @FormUrlEncoded
    @POST("app.brands.brands.transform")
    Observable<BaseResponse<BrandRefreshModel>> brandsRefresh(@Field("token") String token,
                                                              @Field("signature") String signature,
                                                              @Field("newtime") String newtime,
                                                              @Field("random") String random1,
                                                              @Field("num") String num

    );

    @FormUrlEncoded
    @POST("app.sale.my.getcouponlist")
    Observable<BaseResponse<BrandRefreshModel>> getCouponlist(@Field("token") String token,
                                                              @Field("signature") String signature,
                                                              @Field("newtime") String newtime,
                                                              @Field("random") String random1

    );
}
