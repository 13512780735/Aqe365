package com.likeit.aqe365.network.model.goods;

public class PayIndentModel {

    /**
     * order : {"id":"1211","ordersn":"ME20180905152558264292","price":"32.00","title":"澳泉医销网订单"}
     */

    private OrderBean order;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public static class OrderBean {
        /**
         * id : 1211
         * ordersn : ME20180905152558264292
         * price : 32.00
         * title : 澳泉医销网订单
         */

        private String id;
        private String ordersn;
        private String price;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrdersn() {
            return ordersn;
        }

        public void setOrdersn(String ordersn) {
            this.ordersn = ordersn;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
