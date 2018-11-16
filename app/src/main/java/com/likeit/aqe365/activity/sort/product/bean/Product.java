package com.likeit.aqe365.activity.sort.product.bean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wuhenzhizao.sku.bean.Sku;

import java.util.List;

/**
 * Created by liufei on 2017/11/30.
 */

@SuppressLint("ParcelCreator")
public class Product implements Parcelable {


    private static String json;
    String id;
    String title;
    String productprice;
    String marketprice;
    String total;
    String thumb;


//    private String id;
//    private String name;
//    private String status;
//    private String mainImage;
//    private long sellingPrice;
//    private long originPrice;
//    private String currencyUnit;
//    private String measurementUnit;
//    private int saleQuantity;
//    private int stockQuantity;


    private List<Sku> skus;

    public static Product get(Context context) {

        //  String json = context.getString(R.string.product);
        return new Gson().fromJson(json, new TypeToken<Product>() {
        }.getType());
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(String marketprice) {
        this.marketprice = marketprice;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
