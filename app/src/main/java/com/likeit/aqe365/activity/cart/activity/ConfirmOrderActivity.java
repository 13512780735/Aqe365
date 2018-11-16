package com.likeit.aqe365.activity.cart.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.cart.adapter.CartShopItemsAdapter;
import com.likeit.aqe365.base.BaseActivity;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.Indent.OrderCreateModel;
import com.likeit.aqe365.network.model.goods.CaculateModel;
import com.likeit.aqe365.network.model.goods.PayIndentModel;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SignUtils;
import com.likeit.aqe365.view.AmountView;
import com.likeit.aqe365.wxapi.PayActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

public class ConfirmOrderActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address_default)
    LinearLayout mLlAddressDefault;//有地址显示
    @BindView(R.id.rl_address_default)
    RelativeLayout mRlAddressDefault;//无地址时
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_go_to_pay)
    TextView mTvGoToPay;
    @BindView(R.id.ed_message)
    EditText mEdMessage;
    @BindView(R.id.tv_invoice)
    TextView mTvInvoice;
    @BindView(R.id.iv_coupon)
    ImageView mIvCoupon;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @BindView(R.id.tv_expressage)
    TextView mTvExpressage;
    @BindView(R.id.tv_total_number)
    TextView tv_total_number;
    @BindView(R.id.tv_total_price01)
    TextView tv_total_price01;
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    private List<OrderCreateModel.GoodsListBean> data;
    private CartShopListAdatper mAdapter;
    private String id, optionid, total, cartids, cartnum;
    private OrderCreateModel.AddressBean addressBean;
    private String goods_num;
    private String indentFlag;
    private OrderCreateModel orderCreateModel;
    String addressId = null;
    private String goodsIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        id = getIntent().getExtras().getString("id");
        optionid = getIntent().getExtras().getString("optionid");
        total = getIntent().getExtras().getString("total");
        cartids = getIntent().getExtras().getString("cartids");
        cartnum = getIntent().getExtras().getString("cartnum");
        goodsIds = getIntent().getExtras().getString("goodsIds");
        indentFlag = getIntent().getExtras().getString("indentFlag");//1为商品详情：2，为购物车
        data = new ArrayList<>();
        initUI();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (data.size() > 0) {
            data.clear();
            refresh();
        } else {
            refresh();
        }
    }

    private void refresh() {
        if ("1".equals(indentFlag)) {
            initData1();
        } else if ("2".equals(indentFlag)) {
            initData2();
        }
    }

    /**
     * 购物车确认订单
     */
    private void initData2() {
        loaddingDialog.show();
        final String sign = SignUtils.getSign(this);
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        RetrofitUtil.getInstance().CreateCartOrder(token, signature, newtime, random, cartids, cartnum, new Subscriber<BaseResponse<OrderCreateModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                loaddingDialog.dismiss();
                LogUtils.d("错误-->"+e);
            }

            @Override
            public void onNext(BaseResponse<OrderCreateModel> baseResponse) {
                loaddingDialog.dismiss();
                LogUtils.d("订单--》" + baseResponse.getData().getGoods_list());
                if (baseResponse.code == 200) {
                    orderCreateModel = baseResponse.getData();
                    addressBean = orderCreateModel.getAddress();
                    if (addressBean == null) {
                        mLlAddressDefault.setVisibility(View.GONE);
                        mRlAddressDefault.setVisibility(View.VISIBLE);
                    } else {
                        mRlAddressDefault.setVisibility(View.GONE);
                        mLlAddressDefault.setVisibility(View.VISIBLE);
                        addressId = addressBean.getId();
                        initAddress();
                    }
                    data = orderCreateModel.getGoods_list();
                    mAdapter = new CartShopListAdatper(R.layout.layout_cart_shoplist_items, data);
                    mRecyclerView.setAdapter(mAdapter);
                    //mAdapter.setNewData(data);
                    mAdapter.notifyDataSetChanged();
                } else {
                    showProgress(baseResponse.getMsg());
                }
            }
        });
    }

    /**
     * 商品详情确认订单
     */
    private void initData1() {
        loaddingDialog.show();
        final String sign = SignUtils.getSign(this);
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        RetrofitUtil.getInstance().OrderCreate(token, signature, newtime, random, id, optionid, total, new Subscriber<BaseResponse<OrderCreateModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                loaddingDialog.dismiss();
                LogUtils.d("错误--》" + e);
            }

            @Override
            public void onNext(BaseResponse<OrderCreateModel> baseResponse) {
                loaddingDialog.dismiss();
                LogUtils.d("订单--》" + baseResponse.getData().getGoods_list());
                if (baseResponse.code == 200) {
                    orderCreateModel = baseResponse.getData();
                    addressBean = orderCreateModel.getAddress();
                    if (addressBean == null) {
                        mLlAddressDefault.setVisibility(View.GONE);
                        mRlAddressDefault.setVisibility(View.VISIBLE);
                    } else {
                        mRlAddressDefault.setVisibility(View.GONE);
                        mLlAddressDefault.setVisibility(View.VISIBLE);
                        addressId = addressBean.getId();
                        initAddress();
                    }
                    data = orderCreateModel.getGoods_list();
                    mAdapter = new CartShopListAdatper(R.layout.layout_cart_shoplist_items, data);
                    mRecyclerView.setAdapter(mAdapter);
                    //mAdapter.setNewData(data);
                    mAdapter.notifyDataSetChanged();
                } else {
                    showProgress(baseResponse.getMsg());
                }

            }
        });
    }

    private void initAddress() {

        mTvName.setText(addressBean.getRealname());
        mTvPhone.setText(addressBean.getMobile());
        mTvAddress.setText(addressBean.getProvince() + addressBean.getCity() + addressBean.getArea() + addressBean.getAddress());
        double goodPrice = Double.valueOf(orderCreateModel.getGoodsprice());
        double Isdiscountprice = Double.valueOf(orderCreateModel.getGoods_list().get(0).getGoods().get(0).getIsdiscountprice());
        mTvInvoice.setText("发票信息    " + "不开发票");
        mTvTotalPrice.setText("￥ " + goodPrice);
        mTvPrice.setText("¥ " + orderCreateModel.getEndprice());
        mTvExpressage.setText("¥ " + orderCreateModel.getDispatch_price());
        // tv_total_number.setText("共 " + "1" + " 件商品，合计: ");
        tv_total_price01.setText("¥ " + goodPrice);
        tv_total_number.setText("共 " + orderCreateModel.getTotal() + " 件商品，合计: ");
//                tv_total_price.setText("¥ " + goodPrice);

    }

    private void initUI() {
        setBackView();
        setTitle("确认订单");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // initData();
    }

    @OnClick({R.id.ll_address_default, R.id.rl_address_default, R.id.tv_go_to_pay})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ll_address_default:
                toActivity(SelectAddressActivity.class);
                break;
            case R.id.rl_address_default:
                toActivity(SelectAddressActivity.class);
                break;
            case R.id.tv_go_to_pay:
                if ("1".equals(indentFlag)) {
                    createIndent();
                } else if ("2".equals(indentFlag)) {
                    createIndent01();
                }
                break;
        }
    }

    /**
     * 购物车生成订单
     */
    private void createIndent01() {

        LogUtils.d("addressId-->"+addressId);
        LogUtils.d("cartids-->"+goodsIds);
        LogUtils.d("cartnum-->"+cartnum);
        LogUtils.d("optionid-->"+optionid);
        if (addressId == null) {
            showProgress("请选择地址");
            return;
        }
        loaddingDialog.show();
        final String sign = SignUtils.getSign(mContext);
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        RetrofitUtil.getInstance().CreateCartSubmitorder(token, signature, newtime, random, goodsIds, optionid, cartnum, addressId, new Subscriber<BaseResponse<PayIndentModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                loaddingDialog.dismiss();
                LogUtils.d("错误--》"+e);
            }

            @Override
            public void onNext(BaseResponse<PayIndentModel> baseResponse) {
              LogUtils.d("Orde-->"+baseResponse.getData().getOrder());
                loaddingDialog.dismiss();
                if (baseResponse.code == 200) {
                    PayIndentModel payIndentModel = baseResponse.getData();
                    LogUtils.d("indentId-->" + payIndentModel.getOrder().getOrdersn());
                    LogUtils.d("money-->" + payIndentModel.getOrder().getPrice());
                    Bundle bundle = new Bundle();
                    bundle.putString("tid", payIndentModel.getOrder().getOrdersn());
                    bundle.putString("money", payIndentModel.getOrder().getPrice());
                    toActivity(PayActivity.class, bundle);
                }
            }
        });
    }

    /**
     * 商品详情生成订单
     */
    private void createIndent() {
        if (addressId == null) {
            showProgress("请选择地址");
            return;
        }
        loaddingDialog.show();
        final String sign = SignUtils.getSign(mContext);
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        RetrofitUtil.getInstance().submitorder(token, signature, newtime, random, id, optionid, total, addressId, new Subscriber<BaseResponse<PayIndentModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                loaddingDialog.dismiss();
            }

            @Override
            public void onNext(BaseResponse<PayIndentModel> baseResponse) {
                loaddingDialog.dismiss();
                if (baseResponse.code == 200) {
                    PayIndentModel payIndentModel = baseResponse.getData();
                    LogUtils.d("indentId-->" + payIndentModel.getOrder().getOrdersn());
                    LogUtils.d("money-->" + payIndentModel.getOrder().getPrice());
                    Bundle bundle = new Bundle();
                    bundle.putString("tid", payIndentModel.getOrder().getOrdersn());
                    bundle.putString("money", payIndentModel.getOrder().getPrice());
                    toActivity(PayActivity.class, bundle);
                }
            }
        });
    }

    public class CartShopListAdatper extends BaseQuickAdapter<OrderCreateModel.GoodsListBean, BaseViewHolder> {
        private List<OrderCreateModel.GoodsListBean.GoodsBean> datas;
        private CartShopItemsAdapter mAdapter;
        private AmountView mAmountView;
        private String goodPrice;

        public CartShopListAdatper(int layoutResId, List<OrderCreateModel.GoodsListBean> data) {
            super(R.layout.layout_cart_shoplist_items, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, OrderCreateModel.GoodsListBean item) {
            datas = item.getGoods();
            LogUtils.d("indentFlag-->" + indentFlag);
            RecyclerView mRecyclerView = baseViewHolder.getView(R.id.RecyclerView);
            LinearLayout llIndent = baseViewHolder.getView(R.id.ll_indent_items);
            baseViewHolder.setText(R.id.tv_indent_name, item.getShopname());
            if ("1".equals(indentFlag)) {
                mRecyclerView.setVisibility(View.GONE);
                llIndent.setVisibility(View.VISIBLE);
                goods_num = datas.get(0).getTotal();
                goodPrice = datas.get(0).getMarketprice();
                mAmountView = baseViewHolder.getView(R.id.amount_view);
                ImageLoader.getInstance().displayImage(datas.get(0).getThumb(), (ImageView) baseViewHolder.getView(R.id.iv_shop_avatar));
                baseViewHolder.setText(R.id.tv_shop_name, datas.get(0).getTitle());
//                tv_total_number.setText("共 " + goods_num + " 件商品，合计: ");
//                tv_total_price.setText("¥ " + goodPrice);
                mAmountView.setGoods_storage(50);
                mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                    @Override
                    public void onAmountChange(View view, int amount) {
                        // Toast.makeText(getActivity(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
                        goods_num = String.valueOf(amount);
                        Log.d("TAG", "goods_num-->" + amount);
                        if (addressId == null) {
                            showProgress("请选择地址");
                            return;
                        }
                        initPrice();
                        tv_total_number.setText("共 " + goods_num + " 件商品，合计: ");
                        // tv_total_price.setText("¥ " + Double.valueOf(goodPrice) * Double.valueOf(goods_num));
                        tv_total_price01.setText("¥ " + Double.valueOf(goodPrice) * Double.valueOf(goods_num));

                    }
                });

            } else if ("2".equals(indentFlag)) {
                llIndent.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
//                tv_total_number.setText("共 " + goods_num + " 件商品，合计: ");
//                tv_total_price.setText("¥ " + goodPrice);
                mAdapter = new CartShopItemsAdapter(R.layout.layout_cart_shopitems_view, datas);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                mRecyclerView.setAdapter(mAdapter);
            }


        }

        private void initPrice() {
            loaddingDialog.show();
            final String sign = SignUtils.getSign(mContext);
            String signs[] = sign.split("##");
            String signature = signs[0];
            String newtime = signs[1];
            String random = signs[2];
            RetrofitUtil.getInstance().getCaculate(token, signature, newtime, random, id, optionid, goods_num, addressId, new Subscriber<BaseResponse<CaculateModel>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    loaddingDialog.dismiss();
                }

                @Override
                public void onNext(BaseResponse<CaculateModel> baseResponse) {
                    loaddingDialog.dismiss();
                    if (baseResponse.code == 200) {
                        mTvTotalPrice.setText("￥ " + Double.valueOf(goodPrice) * Double.valueOf(goods_num));
                        tv_total_price01.setText("￥ " + Double.valueOf(goodPrice) * Double.valueOf(goods_num));
                        mTvPrice.setText("¥ " + baseResponse.getData().getRealprice());
                        mTvExpressage.setText("¥ " + baseResponse.getData().getDispatch_price());
                    }
                }
            });

        }

    }

}
