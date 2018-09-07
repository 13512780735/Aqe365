package com.likeit.aqe365.network.model.Indent;

import java.util.List;

public class IndentListModel {

    /**
     * list : [{"ordersn":"ME20180731091130876268","merchid":"97","id":"1189","status":5,"price":"0.01","merchname":"澳泉医销网","goods":[{"price":0.01,"title":"测试用","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/97/YB8N6KZ1CknN4zh24r84FJ1CGCpIGB.png","total":"1","optionname":""}],"statusstr":"取消交易","sum":1},{"ordersn":"ME20180731091022277741","merchid":"97","id":"1188","status":"1","price":"0.01","merchname":"澳泉医销网","goods":[{"price":0.01,"title":"测试用","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/97/YB8N6KZ1CknN4zh24r84FJ1CGCpIGB.png","total":"1","optionname":""}],"statusstr":"待发货","sum":1},{"ordersn":"ME20180731090956584182","merchid":"97","id":"1187","status":"2","price":"0.01","merchname":"澳泉医销网","goods":[{"price":0.01,"title":"测试用","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/97/YB8N6KZ1CknN4zh24r84FJ1CGCpIGB.png","total":"1","optionname":""}],"statusstr":"待收货","sum":1},{"ordersn":"ME20180731090530481168","merchid":"97","id":"1186","status":"2","price":"0.01","merchname":"澳泉医销网","goods":[{"price":0.01,"title":"测试用","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/97/YB8N6KZ1CknN4zh24r84FJ1CGCpIGB.png","total":"1","optionname":""}],"statusstr":"待收货","sum":1},{"ordersn":"ME20180731090301427543","merchid":"97","id":"1184","status":5,"price":"0.01","merchname":"澳泉医销网","goods":[{"price":0.01,"title":"测试用","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/97/YB8N6KZ1CknN4zh24r84FJ1CGCpIGB.png","total":"1","optionname":""}],"statusstr":"取消交易","sum":1},{"ordersn":"ME20180731084304442444","merchid":"97","id":"1182","status":5,"price":"0.01","merchname":"澳泉医销网","goods":[{"price":0.01,"title":"测试用","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/97/YB8N6KZ1CknN4zh24r84FJ1CGCpIGB.png","total":"1","optionname":""}],"statusstr":"取消交易","sum":1},{"ordersn":"ME20180730110647841640","merchid":"97","id":"1175","status":5,"price":"0.01","merchname":"澳泉医销网","goods":[{"price":0.01,"title":"测试用","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/97/YB8N6KZ1CknN4zh24r84FJ1CGCpIGB.png","total":"1","optionname":""}],"statusstr":"取消交易","sum":1},{"ordersn":"ME20180730110526815784","merchid":"97","id":"1174","status":5,"price":"0.01","merchname":"澳泉医销网","goods":[{"price":0.01,"title":"测试用","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/97/YB8N6KZ1CknN4zh24r84FJ1CGCpIGB.png","total":"1","optionname":""}],"statusstr":"取消交易","sum":1},{"ordersn":"ME20180730110421816242","merchid":"97","id":"1173","status":"1","price":"0.01","merchname":"澳泉医销网","goods":[{"price":0.01,"title":"测试用","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/97/YB8N6KZ1CknN4zh24r84FJ1CGCpIGB.png","total":"1","optionname":""}],"statusstr":"待发货","sum":1},{"ordersn":"ME20180725152313842434","merchid":"97","id":"1167","status":"1","price":"0.01","merchname":"澳泉医销网","goods":[{"price":0.01,"title":"测试用","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/97/YB8N6KZ1CknN4zh24r84FJ1CGCpIGB.png","total":"1","optionname":""}],"statusstr":"待发货","sum":1}]
     * total : 165
     */

    private String total;
    private List<ListBean> list;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * ordersn : ME20180731091130876268
         * merchid : 97
         * id : 1189
         * status : 5
         * price : 0.01
         * merchname : 澳泉医销网
         * goods : [{"price":0.01,"title":"测试用","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/97/YB8N6KZ1CknN4zh24r84FJ1CGCpIGB.png","total":"1","optionname":""}]
         * statusstr : 取消交易
         * sum : 1
         */

        private String ordersn;
        private String merchid;
        private String id;
        private int status;
        private String price;
        private String merchname;
        private String statusstr;
        private int sum;
        private List<GoodsBean> goods;

        public String getOrdersn() {
            return ordersn;
        }

        public void setOrdersn(String ordersn) {
            this.ordersn = ordersn;
        }

        public String getMerchid() {
            return merchid;
        }

        public void setMerchid(String merchid) {
            this.merchid = merchid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMerchname() {
            return merchname;
        }

        public void setMerchname(String merchname) {
            this.merchname = merchname;
        }

        public String getStatusstr() {
            return statusstr;
        }

        public void setStatusstr(String statusstr) {
            this.statusstr = statusstr;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * price : 0.01
             * title : 测试用
             * thumb : http://aoquan.maimaitoo.com/attachment/images/1/merch/97/YB8N6KZ1CknN4zh24r84FJ1CGCpIGB.png
             * total : 1
             * optionname :
             */

            private double price;
            private String title;
            private String thumb;
            private String total;
            private String optionname;

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getOptionname() {
                return optionname;
            }

            public void setOptionname(String optionname) {
                this.optionname = optionname;
            }
        }
    }
}
