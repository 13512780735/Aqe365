package com.likeit.aqe365.activity.people.bean;

public class ShopAttentionBean {
    private String url;
    private String name;
    private String number;
    private Boolean checked;
    public boolean isChoosed;

    public ShopAttentionBean(String name, String url, String number, Boolean checked) {
        this.name = name;
        this.url = url;
        this.number = number;
        this.checked = checked;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }


}
