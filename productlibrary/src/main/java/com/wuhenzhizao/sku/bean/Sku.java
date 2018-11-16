package com.wuhenzhizao.sku.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by wuhenzhizao on 2017/3/6.
 */
public class Sku implements Parcelable {
    //    "id":"617",
//            "originPrice":"2.00",
//            "stockQuantity":"888",
//            "attributes":
    String id;
    String originPrice;
    String stockQuantity;
    String image;

    List<SkuAttribute> attributes;
    private boolean inStock;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public String getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<SkuAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<SkuAttribute> attributes) {
        this.attributes = attributes;
    }

    public static Creator<Sku> getCREATOR() {
        return CREATOR;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.originPrice);
        dest.writeByte(this.inStock ? (byte) 1 : (byte) 0);
        dest.writeString(this.stockQuantity);
        dest.writeTypedList(this.attributes);
        dest.writeString(this.image);
    }

    protected Sku(Parcel in) {
        this.id = in.readString();
        this.originPrice = in.readString();
        this.inStock = in.readByte() != 0;
        this.stockQuantity = in.readString();
        this.image = in.readString();
        this.attributes = in.createTypedArrayList(SkuAttribute.CREATOR);
    }

    public static final Creator<Sku> CREATOR = new Creator<Sku>() {
        @Override
        public Sku createFromParcel(Parcel source) {
            return new Sku(source);
        }

        @Override
        public Sku[] newArray(int size) {
            return new Sku[size];
        }
    };
}
