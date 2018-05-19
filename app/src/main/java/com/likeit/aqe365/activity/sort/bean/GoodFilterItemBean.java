package com.likeit.aqe365.activity.sort.bean;

/**
 * Created by admin on 2018/5/19.
 */

public class GoodFilterItemBean {
    private String name;
    private int pic;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
