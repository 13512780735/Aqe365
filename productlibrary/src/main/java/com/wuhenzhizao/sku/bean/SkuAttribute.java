package com.wuhenzhizao.sku.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wuhenzhizao on 2017/7/20.
 */
public class SkuAttribute implements Parcelable {
    private String key;
    private String value;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SkuAttribute() {
    }

    public SkuAttribute(String key, String value,String image) {
        this.key = key;
        this.value = value;
        this.value = value;
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SkuAttribute{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
    /* @Override
    public String toString() {
        return "SkuAttribute{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.value);
        dest.writeString(this.image);
    }

    protected SkuAttribute(Parcel in) {
        this.key = in.readString();
        this.value = in.readString();
        this.image  = in.readString();
    }

    public static final Creator<SkuAttribute> CREATOR = new Creator<SkuAttribute>() {
        @Override
        public SkuAttribute createFromParcel(Parcel source) {
            return new SkuAttribute(source);
        }

        @Override
        public SkuAttribute[] newArray(int size) {
            return new SkuAttribute[size];
        }
    };
}
