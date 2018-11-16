package com.likeit.aqe365.network.model.goods;

public class PayIndentModel {

    /**
     * order : {"id":"1301","ordersn":"SH20180912150735982822","price":"0.00"}
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
         * id : 1301
         * ordersn : SH20180912150735982822
         * price : 0.00
         */

        private String id;
        private String ordersn;
        private String price;

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
    }
}
