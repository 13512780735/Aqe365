package com.likeit.aqe365.activity.brand.model;

public class LogoBean {
    String id;
    String advimg;

    public String getAdvimg() {
        return advimg;
    }

    public void setAdvimg(String advimg) {
        this.advimg = advimg;
    }

    @Override
    public String toString() {
        return "brandLogoModel{" +
                "id='" + id + '\'' +
                ", advimg='" + advimg + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
