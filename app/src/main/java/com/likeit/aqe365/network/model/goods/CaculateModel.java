package com.likeit.aqe365.network.model.goods;

public class CaculateModel {

    /**
     * realprice : 427.5
     * isdiscountprice : 22.5
     */

    private String realprice;
    private String isdiscountprice;
    private String dispatch_price;

    public String getDispatch_price() {
        return dispatch_price;
    }

    public void setDispatch_price(String dispatch_price) {
        this.dispatch_price = dispatch_price;
    }

    /**
     * discountprice : 0
     */

    private String discountprice;

    public String getRealprice() {
        return realprice;
    }

    public void setRealprice(String realprice) {
        this.realprice = realprice;
    }

    public String getIsdiscountprice() {
        return isdiscountprice;
    }

    public void setIsdiscountprice(String isdiscountprice) {
        this.isdiscountprice = isdiscountprice;
    }

    public String getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(String discountprice) {
        this.discountprice = discountprice;
    }
}
