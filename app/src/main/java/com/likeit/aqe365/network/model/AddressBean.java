package com.likeit.aqe365.network.model;

import java.io.Serializable;

/**
 * @author :renpan
 * @version :v1.0
 * @class :AddressDomain
 * @date :2016-08-15 11:00
 * @description:
 */
public class AddressBean implements Serializable {
    private String id;
    private String name;
    private String mobilePhone;
    private String address;

    /**
     * 0 为被选中
     * 1 是默认选中的
     */
    private String defaultFlag = "0";

    public AddressBean() {
    }

    public AddressBean(String id, String name, String mobilePhone, String defaultFlag, String address) {
        this.id = id;
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.defaultFlag = defaultFlag;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "AddressBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", address='" + address + '\'' +
                ", defaultFlag='" + defaultFlag + '\'' +
                '}';
    }
}
