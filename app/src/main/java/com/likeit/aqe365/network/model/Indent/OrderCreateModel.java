package com.likeit.aqe365.network.model.Indent;

import java.util.List;

public class OrderCreateModel {


    /**
     * address : {"id":"816","realname":"11","mobile":"13655488742","province":"辽宁省","city":"大连市","area":"沙河口区","address":"11"}
     * goods_list : [{"shopname":"中山市芯澳医疗产品有限公司","shopid":0,"goods":[{"goodsid":"377","title":"日本松风 Beautifil Flow光固化树脂 2ml/支 F02 另含5支注射头","optionid":"","optiontitle":"","minbuy":"0","totalmaxbuy":"10639","unit":"","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/118/Xo9W9wWHAW2hXbr66eHIoI62EHEou6.jpg","marketprice":"150.00","total":"1","isdiscountprice":"0"}]}]
     * total : 1
     * totalprice :
     * deductcredit : 0
     * deductmoney : 0
     * include_dispath :
     * endprice :
     * goodsprice : 150
     * dispatch_price : 0
     */

    private AddressBean address;
    private String total;
    private String totalprice;
    private String deductcredit;
    private String deductmoney;
    private String include_dispath;
    private String endprice;
    private String goodsprice;
    private String dispatch_price;
    private List<GoodsListBean> goods_list;

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getDeductcredit() {
        return deductcredit;
    }

    public void setDeductcredit(String deductcredit) {
        this.deductcredit = deductcredit;
    }

    public String getDeductmoney() {
        return deductmoney;
    }

    public void setDeductmoney(String deductmoney) {
        this.deductmoney = deductmoney;
    }

    public String getInclude_dispath() {
        return include_dispath;
    }

    public void setInclude_dispath(String include_dispath) {
        this.include_dispath = include_dispath;
    }

    public String getEndprice() {
        return endprice;
    }

    public void setEndprice(String endprice) {
        this.endprice = endprice;
    }

    public String getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(String goodsprice) {
        this.goodsprice = goodsprice;
    }

    public String getDispatch_price() {
        return dispatch_price;
    }

    public void setDispatch_price(String dispatch_price) {
        this.dispatch_price = dispatch_price;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class AddressBean {
        /**
         * id : 816
         * realname : 11
         * mobile : 13655488742
         * province : 辽宁省
         * city : 大连市
         * area : 沙河口区
         * address : 11
         */

        private String id;
        private String realname;
        private String mobile;
        private String province;
        private String city;
        private String area;
        private String address;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class GoodsListBean {
        /**
         * shopname : 中山市芯澳医疗产品有限公司
         * shopid : 0
         * goods : [{"goodsid":"377","title":"日本松风 Beautifil Flow光固化树脂 2ml/支 F02 另含5支注射头","optionid":"","optiontitle":"","minbuy":"0","totalmaxbuy":"10639","unit":"","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/118/Xo9W9wWHAW2hXbr66eHIoI62EHEou6.jpg","marketprice":"150.00","total":"1","isdiscountprice":"0"}]
         */

        private String shopname;
        private int shopid;
        private List<GoodsBean> goods;

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public int getShopid() {
            return shopid;
        }

        public void setShopid(int shopid) {
            this.shopid = shopid;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * goodsid : 377
             * title : 日本松风 Beautifil Flow光固化树脂 2ml/支 F02 另含5支注射头
             * optionid :
             * optiontitle :
             * minbuy : 0
             * totalmaxbuy : 10639
             * unit :
             * thumb : http://aoquan.maimaitoo.com/attachment/images/1/merch/118/Xo9W9wWHAW2hXbr66eHIoI62EHEou6.jpg
             * marketprice : 150.00
             * total : 1
             * isdiscountprice : 0
             */

            private String goodsid;
            private String title;
            private String optionid;
            private String optiontitle;
            private String minbuy;
            private String totalmaxbuy;
            private String unit;
            private String thumb;
            private String marketprice;
            private String total;
            private String isdiscountprice;

            public String getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(String goodsid) {
                this.goodsid = goodsid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getOptionid() {
                return optionid;
            }

            public void setOptionid(String optionid) {
                this.optionid = optionid;
            }

            public String getOptiontitle() {
                return optiontitle;
            }

            public void setOptiontitle(String optiontitle) {
                this.optiontitle = optiontitle;
            }

            public String getMinbuy() {
                return minbuy;
            }

            public void setMinbuy(String minbuy) {
                this.minbuy = minbuy;
            }

            public String getTotalmaxbuy() {
                return totalmaxbuy;
            }

            public void setTotalmaxbuy(String totalmaxbuy) {
                this.totalmaxbuy = totalmaxbuy;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getIsdiscountprice() {
                return isdiscountprice;
            }

            public void setIsdiscountprice(String isdiscountprice) {
                this.isdiscountprice = isdiscountprice;
            }
        }
    }
}
