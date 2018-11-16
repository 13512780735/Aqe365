package com.likeit.aqe365.network.model.goods;

import java.io.Serializable;
import java.util.List;

public class GoodDetailModel implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * detail_tab : {"id":"252","title":"丝瑞妮 漱口水","thumb":[{"imgurl":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/W77844ecjc8J87ECc8cFeI8t81H1iJ.jpg","linkurl":""},{"imgurl":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/rV1KwlqoVVXNwbvb1CWKpNQoMwKCzx.jpg","linkurl":""},{"imgurl":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/nUII8tCuTri8f18KQ7VTv1VT1zujqv.jpg","linkurl":""}],"productprice":"15.00","marketprice":"12.00","goodssn":"","merchid":"117","sales":"10","collect":"0","dispatchprice":8,"stock":"886","registnum":"","area":" "}
     * detail_sale : {"detail_tab":{"share":{"hideshare":"0","share":"分享","share_link":"","share_icon":"icon-share"}},"coupon":[{"id":"6","title":"新用户专享","enough":"199.00","usecredit2":null},{"id":"7","title":"通用券部分商品除外","enough":"399.00","usecredit2":null},{"id":"13","title":"30元全场通用卷","enough":"1500.00","usecredit2":null}]}
     * parameter : [{"title":"产品名称","value":"丝瑞妮 漱口水"}]
     * particulars : http://aoquan.maimaitoo.com/app/./index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&r=goods.appparticulars&id=252
     */

    private DetailTabBean detail_tab;
    private DetailSaleBean detail_sale;
    private String particulars;
    private List<ParameterBean> parameter;

    public DetailTabBean getDetail_tab() {
        return detail_tab;
    }

    public void setDetail_tab(DetailTabBean detail_tab) {
        this.detail_tab = detail_tab;
    }

    public DetailSaleBean getDetail_sale() {
        return detail_sale;
    }

    public void setDetail_sale(DetailSaleBean detail_sale) {
        this.detail_sale = detail_sale;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public List<ParameterBean> getParameter() {
        return parameter;
    }

    public void setParameter(List<ParameterBean> parameter) {
        this.parameter = parameter;
    }

    public static class DetailTabBean {
        /**
         * id : 252
         * title : 丝瑞妮 漱口水
         * thumb : [{"imgurl":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/W77844ecjc8J87ECc8cFeI8t81H1iJ.jpg","linkurl":""},{"imgurl":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/rV1KwlqoVVXNwbvb1CWKpNQoMwKCzx.jpg","linkurl":""},{"imgurl":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/nUII8tCuTri8f18KQ7VTv1VT1zujqv.jpg","linkurl":""}]
         * productprice : 15.00
         * marketprice : 12.00
         * goodssn :
         * merchid : 117
         * sales : 10
         * collect : 0
         * dispatchprice : 8
         * stock : 886
         * registnum :
         * area :
         */

        private String id;
        private String title;
        private String productprice;
        private String marketprice;
        private String goodssn;
        private String merchid;
        private String sales;
        private String collect;
        private int dispatchprice;
        private String stock;
        private String registnum;
        private String area;
        private List<ThumbBean> thumb;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getProductprice() {
            return productprice;
        }

        public void setProductprice(String productprice) {
            this.productprice = productprice;
        }

        public String getMarketprice() {
            return marketprice;
        }

        public void setMarketprice(String marketprice) {
            this.marketprice = marketprice;
        }

        public String getGoodssn() {
            return goodssn;
        }

        public void setGoodssn(String goodssn) {
            this.goodssn = goodssn;
        }

        public String getMerchid() {
            return merchid;
        }

        public void setMerchid(String merchid) {
            this.merchid = merchid;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getCollect() {
            return collect;
        }

        public void setCollect(String collect) {
            this.collect = collect;
        }

        public int getDispatchprice() {
            return dispatchprice;
        }

        public void setDispatchprice(int dispatchprice) {
            this.dispatchprice = dispatchprice;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getRegistnum() {
            return registnum;
        }

        public void setRegistnum(String registnum) {
            this.registnum = registnum;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public List<ThumbBean> getThumb() {
            return thumb;
        }

        public void setThumb(List<ThumbBean> thumb) {
            this.thumb = thumb;
        }

        public static class ThumbBean {
            /**
             * imgurl : http://aoquan.maimaitoo.com/attachment/images/1/merch/117/W77844ecjc8J87ECc8cFeI8t81H1iJ.jpg
             * linkurl :
             */

            private String imgurl;
            private String linkurl;

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getLinkurl() {
                return linkurl;
            }

            public void setLinkurl(String linkurl) {
                this.linkurl = linkurl;
            }
        }
    }

    public static class DetailSaleBean implements Serializable{
        /**
         * detail_tab : {"share":{"hideshare":"0","share":"分享","share_link":"","share_icon":"icon-share"}}
         * coupon : [{"id":"6","title":"新用户专享","enough":"199.00","usecredit2":null},{"id":"7","title":"通用券部分商品除外","enough":"399.00","usecredit2":null},{"id":"13","title":"30元全场通用卷","enough":"1500.00","usecredit2":null}]
         */

        private DetailTabBeanX detail_tab;
        private List<CouponBean> coupon;

        public DetailTabBeanX getDetail_tab() {
            return detail_tab;
        }

        public void setDetail_tab(DetailTabBeanX detail_tab) {
            this.detail_tab = detail_tab;
        }

        public List<CouponBean> getCoupon() {
            return coupon;
        }

        public void setCoupon(List<CouponBean> coupon) {
            this.coupon = coupon;
        }

        public static class DetailTabBeanX implements Serializable{
            /**
             * share : {"hideshare":"0","share":"分享","share_link":"","share_icon":"icon-share"}
             */

            private ShareBean share;

            public ShareBean getShare() {
                return share;
            }

            public void setShare(ShareBean share) {
                this.share = share;
            }

            public static class ShareBean implements Serializable{
                /**
                 * hideshare : 0
                 * share : 分享
                 * share_link :
                 * share_icon : icon-share
                 */

                private String hideshare;
                private String share;
                private String share_link;
                private String share_icon;

                public String getHideshare() {
                    return hideshare;
                }

                public void setHideshare(String hideshare) {
                    this.hideshare = hideshare;
                }

                public String getShare() {
                    return share;
                }

                public void setShare(String share) {
                    this.share = share;
                }

                public String getShare_link() {
                    return share_link;
                }

                public void setShare_link(String share_link) {
                    this.share_link = share_link;
                }

                public String getShare_icon() {
                    return share_icon;
                }

                public void setShare_icon(String share_icon) {
                    this.share_icon = share_icon;
                }
            }
        }

        public static class CouponBean implements Serializable{
            /**
             * id : 6
             * title : 新用户专享
             * enough : 199.00
             * usecredit2 : null
             */

            private String id;
            private String title;
            private String enough;
            private Object usecredit2;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getEnough() {
                return enough;
            }

            public void setEnough(String enough) {
                this.enough = enough;
            }

            public Object getUsecredit2() {
                return usecredit2;
            }

            public void setUsecredit2(Object usecredit2) {
                this.usecredit2 = usecredit2;
            }
        }
    }

    public static class ParameterBean implements Serializable{
        /**
         * title : 产品名称
         * value : 丝瑞妮 漱口水
         */

        private String title;
        private String value;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
