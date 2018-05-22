package com.likeit.aqe365.activity.sort.filter.bean;

import java.util.List;

/**
 * Created by admin on 2018/5/22.
 */

public class ShopRightListBean {
    private String type;
    private List<ShopSortItemBean> mList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ShopSortItemBean> getmList() {
        return mList;
    }

    public void setmList(List<ShopSortItemBean> mList) {
        this.mList = mList;
    }

}
