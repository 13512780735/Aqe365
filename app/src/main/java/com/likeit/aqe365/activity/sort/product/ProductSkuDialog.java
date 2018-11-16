package com.likeit.aqe365.activity.sort.product;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.cart.activity.ConfirmOrderActivity;
import com.likeit.aqe365.activity.sort.product.bean.Product;
import com.likeit.aqe365.databinding.DialogProductSkuBinding;
import com.likeit.aqe365.activity.sort.product.image.GImageLoader;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.EmptyEntity;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.LoaddingDialog;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SharedPreferencesUtils;
import com.likeit.aqe365.utils.SignUtils;
import com.likeit.aqe365.utils.ToastUtils;
import com.wuhenzhizao.sku.bean.Sku;
import com.wuhenzhizao.sku.bean.SkuAttribute;
import com.wuhenzhizao.sku.view.OnSkuListener;
import com.wuhenzhizao.titlebar.utils.AppUtils;

import java.util.List;

import rx.Subscriber;

/**
 * Created by liufei on 2017/11/30.
 */
public class ProductSkuDialog extends Dialog {
    private DialogProductSkuBinding binding;
    private Context context;
    private Product product;
    private List<Sku> skuList;
    private Callback callback;
    private String key;
    private Sku selectedSku;
    private String priceFormat;
    private String stockQuantityFormat;
    private String keys;
    private LoaddingDialog loaddingDialog;
    private String token;
    private String optionid;

    public ProductSkuDialog(@NonNull Context context) {
        this(context, R.style.CommonBottomDialogStyle);
    }

    public ProductSkuDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d("初始化1");
        LogUtils.d("keys-->" + SharedPreferencesUtils.getString(getContext(), "keys"));
        token=SharedPreferencesUtils.getString(getContext(),"token");
        keys = SharedPreferencesUtils.getString(getContext(), "keys");//1.加入购物车 2.立即购买 0.点击规格
    }

    private void initView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_product_sku, null, false);
        setContentView(binding.getRoot());

        binding.ibSkuClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.btnSkuQuantityMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = binding.etSkuQuantityInput.getText().toString();
                if (TextUtils.isEmpty(quantity)) {
                    return;
                }
                int quantityInt = Integer.parseInt(quantity);
                if (quantityInt > 1) {
                    String newQuantity = String.valueOf(quantityInt - 1);
                    binding.etSkuQuantityInput.setText(newQuantity);
                    binding.etSkuQuantityInput.setSelection(newQuantity.length());
                    updateQuantityOperator(quantityInt - 1);
                }
            }
        });

        binding.btnSkuQuantityPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantityNumber = 0;
                String quantity = binding.etSkuQuantityInput.getText().toString();
                if (TextUtils.isEmpty(quantity) || "0".equals(product.getTotal())) {
                    return;
                }
                int quantityInt = Integer.parseInt(quantity);
                if (skuList != null) {
                    quantityNumber = Integer.valueOf(selectedSku.getStockQuantity());
                } else {
                    quantityNumber = Integer.valueOf(product.getTotal());
                }

                if (quantityInt < quantityNumber) {
                    String newQuantity = String.valueOf(quantityInt + 1);
                    binding.etSkuQuantityInput.setText(newQuantity);
                    binding.etSkuQuantityInput.setSelection(newQuantity.length());
                    updateQuantityOperator(quantityInt + 1);
                }
            }
        });
        binding.etSkuQuantityInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId != EditorInfo.IME_ACTION_DONE || selectedSku == null) {
                    return false;
                }
                String quantity = binding.etSkuQuantityInput.getText().toString();
                if (TextUtils.isEmpty(quantity)) {
                    return false;
                }
                int quantityInt = Integer.parseInt(quantity);
                if (quantityInt <= 1) {
                    binding.etSkuQuantityInput.setText("1");
                    binding.etSkuQuantityInput.setSelection(1);
                    updateQuantityOperator(1);
                } else if (quantityInt >= Integer.valueOf(selectedSku.getStockQuantity())) {
                    String newQuantity = String.valueOf(selectedSku.getStockQuantity());
                    binding.etSkuQuantityInput.setText(newQuantity);
                    binding.etSkuQuantityInput.setSelection(newQuantity.length());
                    updateQuantityOperator(Integer.valueOf(selectedSku.getStockQuantity()));
                } else {
                    binding.etSkuQuantityInput.setSelection(quantity.length());
                    updateQuantityOperator(quantityInt);
                }
                return false;
            }
        });
        binding.scrollSkuList.setListener(new OnSkuListener() {
            @Override
            public void onUnselected(SkuAttribute unselectedAttribute) {
                selectedSku = null;
                GImageLoader.displayUrl(context, binding.ivSkuLogo, product.getThumb());
                binding.tvSkuQuantity.setText(String.format(stockQuantityFormat, product.getTotal()));
                String firstUnselectedAttributeName = binding.scrollSkuList.getFirstUnelectedAttributeName();
                binding.tvSkuInfo.setText("请选择：" + firstUnselectedAttributeName);
                binding.btnSubmit.setEnabled(false);
                String quantity = binding.etSkuQuantityInput.getText().toString();
                if (!TextUtils.isEmpty(quantity)) {
                    updateQuantityOperator(Integer.valueOf(quantity));
                } else {
                    updateQuantityOperator(0);
                }
            }

            @Override
            public void onSelect(SkuAttribute selectAttribute) {
                String firstUnselectedAttributeName = binding.scrollSkuList.getFirstUnelectedAttributeName();
                binding.tvSkuInfo.setText("请选择：" + firstUnselectedAttributeName);
            }

            @Override
            public void onSkuSelected(Sku sku) {
                selectedSku = sku;
                if ("".equals(selectedSku.getImage())) {
                    GImageLoader.displayUrl(context, binding.ivSkuLogo, product.getThumb());
                } else {
                    GImageLoader.displayUrl(context, binding.ivSkuLogo, selectedSku.getImage());
                }

                List<SkuAttribute> attributeList = selectedSku.getAttributes();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < attributeList.size(); i++) {
                    if (i != 0) {
                        builder.append("　");
                    }
                    SkuAttribute attribute = attributeList.get(i);
                    builder.append("\"" + attribute.getValue() + "\"");
                }
                binding.tvSkuInfo.setText("已选：" + builder.toString());
                binding.tvSkuQuantity.setText(String.format(stockQuantityFormat, selectedSku.getStockQuantity()));
                binding.btnSubmit.setEnabled(true);
                String quantity = binding.etSkuQuantityInput.getText().toString();
                if (!TextUtils.isEmpty(quantity)) {
                    updateQuantityOperator(Integer.valueOf(quantity));
                } else {
                    updateQuantityOperator(0);
                }
            }
        });
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // LogUtils.d("key-->" + key);

                int quantityNumber = 0;
                String quantity = binding.etSkuQuantityInput.getText().toString();
                if (TextUtils.isEmpty(quantity)) {
                    return;
                }
                int quantityInt = Integer.parseInt(quantity);
                if (skuList != null) {
                    quantityNumber = Integer.valueOf(selectedSku.getStockQuantity());
                } else {
                    quantityNumber = Integer.valueOf(product.getTotal());
                }
                if (quantityInt > 0 && quantityInt <= quantityNumber) {
                    if (skuList != null) {
                        callback.onAdded(selectedSku, quantityInt);
                        optionid = selectedSku.getId();
                    } else {
                        callback.onAdded(null, quantityInt);
                        optionid = "";
                    }
                    if ("0".equals(keys)) {
                        dismiss();
                    } else if ("1".equals(keys)) {
                        addCrat(optionid,quantityInt);
                    } else if ("2".equals(keys)) {
                        LogUtils.d("id-->"+product.getId()+"optionid-->"+optionid);
                        Intent intent = new Intent(getContext(), ConfirmOrderActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("id", product.getId());
                        bundle.putString("optionid", optionid);
                        bundle.putString("total", String.valueOf(quantityInt));
                        bundle.putString("cartids", "");
                        bundle.putString("cartnum", "");
                        bundle.putString("indentFlag", "1");
                        bundle.putString("goodsIds", "");
                        intent.putExtras(bundle);
                        getContext().startActivity(intent);
                        dismiss();
                    }

                } else {
                    Toast.makeText(getContext(), "商品数量超出库存，请修改数量", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addCrat(String optionid, int quantityInt) {
        loaddingDialog = new LoaddingDialog(getContext());
        loaddingDialog.show();
        String sign = SignUtils.getSign(getContext());
        String signs[] = sign.split("##");
        String signature = signs[0];
        String newtime = signs[1];
        String random = signs[2];
        RetrofitUtil.getInstance().addcart(token, signature, newtime, random, product.getId(), String.valueOf(quantityInt), optionid, new Subscriber<BaseResponse<EmptyEntity>>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {
                loaddingDialog.dismiss();
            }

            @Override
            public void onNext(BaseResponse<EmptyEntity> baseResponse) {
                loaddingDialog.dismiss();
                if (baseResponse.code == 200) {
                    ToastUtils.showToast(getContext(), "加入购物车成功");
                    dismiss();
                } else {
                    ToastUtils.showToast(getContext(), baseResponse.getMsg());
                }
            }
        });
    }

    public void setData(final Product product, Callback callback) {
        this.product = product;
        this.skuList = product.getSkus();
        this.callback = callback;

        priceFormat = context.getString(R.string.comm_price_format);
        stockQuantityFormat = context.getString(R.string.product_detail_sku_stock);
        LogUtils.d("product-->" + product);
        LogUtils.d("key222-->" + key);
        if (skuList != null) {
            updateSkuData();
            updateQuantityOperator(1);
        }
        updataView();
        updateQuantityOperator(1);
    }

    private void updataView() {
        GImageLoader.displayUrl(context, binding.ivSkuLogo, product.getThumb());
        binding.tvSkuSellingPrice.setText(String.format(priceFormat, product.getMarketprice()));
        binding.tvSkuQuantity.setText(String.format(stockQuantityFormat, product.getTotal()));
    }

    private void updateSkuData() {
        binding.scrollSkuList.setSkuList(product.getSkus());

        Sku firstSku = product.getSkus().get(0);
        if (Integer.valueOf(firstSku.getStockQuantity()) > 0) {
            selectedSku = firstSku;
            // 选中第一个sku
            binding.scrollSkuList.setSelectedSku(selectedSku);

            GImageLoader.displayUrl(context, binding.ivSkuLogo, selectedSku.getImage());
            binding.tvSkuSellingPrice.setText(String.format(priceFormat, selectedSku.getOriginPrice()));
            // binding.tvSkuSellingPriceUnit.setText("/" + "件");
            binding.tvSkuQuantity.setText(String.format(stockQuantityFormat, selectedSku.getStockQuantity()));
            binding.btnSubmit.setEnabled(Integer.valueOf(selectedSku.getStockQuantity()) > 0);
            List<SkuAttribute> attributeList = selectedSku.getAttributes();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < attributeList.size(); i++) {
                if (i != 0) {
                    builder.append("　");
                }
                SkuAttribute attribute = attributeList.get(i);
                //   GImageLoader.displayUrl(context, binding.ivSkuLogo, selectedSku.getAttributes().get(i).getImage());
                builder.append("\"" + attribute.getValue() + "\"");
            }
            binding.tvSkuInfo.setText("已选：" + builder.toString());
        } else {
            GImageLoader.displayUrl(context, binding.ivSkuLogo, product.getThumb());
            binding.tvSkuSellingPrice.setText(String.format(priceFormat, product.getMarketprice()));
            // binding.tvSkuSellingPriceUnit.setText("/" + "件");
            binding.tvSkuQuantity.setText(String.format(stockQuantityFormat, product.getTotal()));
            binding.btnSubmit.setEnabled(false);
            binding.tvSkuInfo.setText("请选择：" + skuList.get(0).getAttributes().get(0).getKey());
        }
    }

    private void updateQuantityOperator(int newQuantity) {
        if (selectedSku == null) {
            if ("0".equals(product.getTotal())) {
                binding.btnSkuQuantityMinus.setEnabled(false);
                binding.btnSkuQuantityPlus.setEnabled(false);
                binding.etSkuQuantityInput.setEnabled(false);
                binding.btnSubmit.setEnabled(false);
            } else {
                if (newQuantity <= 1) {
                    binding.btnSkuQuantityMinus.setEnabled(false);
                    binding.btnSkuQuantityPlus.setEnabled(true);
                } else if (newQuantity >= Integer.valueOf(product.getTotal())) {
                    binding.btnSkuQuantityMinus.setEnabled(true);
                    binding.btnSkuQuantityPlus.setEnabled(false);
                } else {
                    binding.btnSkuQuantityMinus.setEnabled(true);
                    binding.btnSkuQuantityPlus.setEnabled(true);
                }
                binding.etSkuQuantityInput.setEnabled(true);
            }

        } else {
            if (newQuantity <= 1) {
                binding.btnSkuQuantityMinus.setEnabled(false);
                binding.btnSkuQuantityPlus.setEnabled(true);
            } else if (newQuantity >= Integer.valueOf(selectedSku.getStockQuantity())) {
                binding.btnSkuQuantityMinus.setEnabled(true);
                binding.btnSkuQuantityPlus.setEnabled(false);
            } else {
                binding.btnSkuQuantityMinus.setEnabled(true);
                binding.btnSkuQuantityPlus.setEnabled(true);
            }
            binding.etSkuQuantityInput.setEnabled(true);
        }

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 解决键盘遮挡输入框问题
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.getDecorView().setPadding(0, 0, 0, 0);
        // KeyboardConflictCompat.assistWindow(getWindow());
        AppUtils.transparencyBar(getWindow());
    }


    public interface Callback {
        void onAdded(Sku sku, int quantity);
    }
}
