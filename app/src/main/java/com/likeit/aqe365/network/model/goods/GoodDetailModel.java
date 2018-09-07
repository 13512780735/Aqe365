package com.likeit.aqe365.network.model.goods;

import java.io.Serializable;
import java.util.List;

public class GoodDetailModel implements Serializable {

    /**
     * detail_tab : {"id":"377","title":"日本松风 Beautifil Flow光固化树脂 2ml/支 F02 另含5支注射头","thumb":[{"imgurl":"http://aoquan.maimaitoo.com/attachment/images/1/merch/118/Xo9W9wWHAW2hXbr66eHIoI62EHEou6.jpg","linkurl":""}],"productprice":"180.00","marketprice":"150.00","goodssn":"","merchid":"118","sales":"0","collect":"0","dispatchprice":0,"hasSales":true}
     * detail_sale : {"detail_tab":{"share":{"hideshare":"0","share":"分享","share_link":"","share_icon":"icon-share"}},"coupon":[{"id":"6","title":"新用户专享","enough":"199.00"},{"id":"7","title":"通用券部分商品除外","enough":"399.00"},{"id":"12","title":"150元无门槛","enough":"0.00"},{"id":"13","title":"30元全场通用卷","enough":"1500.00"}]}
     */
    private static final long serialVersionUID = 1L;
    private DetailTabBean detail_tab;
    private DetailSaleBean detail_sale;

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

    public static class DetailTabBean implements Serializable {
        /**
         * id : 377
         * title : 日本松风 Beautifil Flow光固化树脂 2ml/支 F02 另含5支注射头
         * thumb : [{"imgurl":"http://aoquan.maimaitoo.com/attachment/images/1/merch/118/Xo9W9wWHAW2hXbr66eHIoI62EHEou6.jpg","linkurl":""}]
         * productprice : 180.00
         * marketprice : 150.00
         * goodssn :
         * merchid : 118
         * sales : 0
         * collect : 0
         * dispatchprice : 0
         * hasSales : true
         * registnum": "2223",
         * "area": "广东省 中山市"
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
        private boolean hasSales;
        private String registnum;
        private String area;
        private String stock;

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        private List<ThumbBean> thumb;

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

        public boolean isHasSales() {
            return hasSales;
        }

        public void setHasSales(boolean hasSales) {
            this.hasSales = hasSales;
        }

        public List<ThumbBean> getThumb() {
            return thumb;
        }

        public void setThumb(List<ThumbBean> thumb) {
            this.thumb = thumb;
        }

        public static class ThumbBean implements Serializable {
            /**
             * imgurl : http://aoquan.maimaitoo.com/attachment/images/1/merch/118/Xo9W9wWHAW2hXbr66eHIoI62EHEou6.jpg
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

    public static class DetailSaleBean implements Serializable {
        /**
         * detail_tab : {"share":{"hideshare":"0","share":"分享","share_link":"","share_icon":"icon-share"}}
         * coupon : [{"id":"6","title":"新用户专享","enough":"199.00"},{"id":"7","title":"通用券部分商品除外","enough":"399.00"},{"id":"12","title":"150元无门槛","enough":"0.00"},{"id":"13","title":"30元全场通用卷","enough":"1500.00"}]
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

        public static class DetailTabBeanX implements Serializable {
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

            public static class ShareBean implements Serializable {
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

        public static class CouponBean implements Serializable {
            /**
             * id : 6
             * title : 新用户专享
             * enough : 199.00
             */

            private String id;
            private String title;
            private String enough;

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
        }
    }
}
