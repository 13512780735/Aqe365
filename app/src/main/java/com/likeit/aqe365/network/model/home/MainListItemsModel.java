package com.likeit.aqe365.network.model.home;

import java.io.Serializable;
import java.util.List;

public class MainListItemsModel implements Serializable{


    private List<BannerBean> banner;
    private List<NoticeBean> notice;
    private List<RecommendBean> recommend;
    private List<GoodsgroupBean> goodsgroup;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<NoticeBean> getNotice() {
        return notice;
    }

    public void setNotice(List<NoticeBean> notice) {
        this.notice = notice;
    }

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public List<GoodsgroupBean> getGoodsgroup() {
        return goodsgroup;
    }

    public void setGoodsgroup(List<GoodsgroupBean> goodsgroup) {
        this.goodsgroup = goodsgroup;
    }

    public static class BannerBean {
        /**
         * imgurl : http://aoquan.maimaitoo.com/attachment/images/1/2018/04/boX99UXy5yUkx6qky8x5qHTYZj9dXo.jpg
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

    public static class NoticeBean {
        /**
         * id : 2
         * title : 澳泉医销网多商户商城系统公告
         */

        private String id;
        private String title;

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
    }

    public static class RecommendBean {
        /**
         * goods : [{"id":"368","title":"齿科 搅拌器 半自动搅拌器","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/xA8I9wAVYGSKiXDgZrNHi4H8R9s9Ae.png","marketprice":"450.00"},{"id":"335","title":"希捷 消毒片","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/ROnn4enOChxEcPtcCTkkhl3OL44RRN.jpeg","marketprice":"6.50"},{"id":"331","title":"康田正 薄网牙托20个/盒","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/JT59XJZ5NthNbjBzgbt7j8n8582OqH.jpg","marketprice":"35.75"},{"id":"330","title":"康田正 消毒盒","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/twZzbZTTqiLCBJtAuhqqzLqqwB2cql.jpg","marketprice":"14.30"},{"id":"328","title":"康田正 一次性面罩 10个/包","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/NnsVK6uUnnJMVb6U2rXFXzSJmj3RVu.jpg","marketprice":"85.80"},{"id":"327","title":"康田正 树脂成型片补充装","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/xRdS9AwS57SnNZwPWv5J6EE5xVEVJq.jpg","marketprice":"71.50"},{"id":"323","title":"观雅 木榴油","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/SZ5UQVu1oUlLZUa1l5C1cQQ51O5M6q.jpg","marketprice":"39.30"},{"id":"322","title":"观雅 无砷失活抑菌材料（快2-4天）","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/b7y37m5EMeKU5YGupi50RKkgEGGpuG.jpg","marketprice":"70.20"},{"id":"321","title":"观雅 丁香油","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/Ih7XHxA7B0hZHX5AqHHLEh17THbYqy.jpg","marketprice":"12.00"}]
         * moduletitle : —— 推荐产品 ——
         * liststyle : block three
         */

        private String moduletitle;
        private String liststyle;
        private List<GoodsBean> goods;

        public String getModuletitle() {
            return moduletitle;
        }

        public void setModuletitle(String moduletitle) {
            this.moduletitle = moduletitle;
        }

        public String getListstyle() {
            return liststyle;
        }

        public void setListstyle(String liststyle) {
            this.liststyle = liststyle;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * id : 368
             * title : 齿科 搅拌器 半自动搅拌器
             * thumb : http://aoquan.maimaitoo.com/attachment/images/1/merch/117/xA8I9wAVYGSKiXDgZrNHi4H8R9s9Ae.png
             * marketprice : 450.00
             */

            private String id;
            private String title;
            private String thumb;
            private String marketprice;

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
        }
    }

    public static class GoodsgroupBean {
        /**
         * goods : [{"id":"376","title":"贺特佳 丁腈检查手套 燕麦款 护手  20盒起售","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/vAqaH1aZB7yBK1k7Okn17H7q7h1WMh.jpg","marketprice":"32.50"},{"id":"375","title":"日本松风 Beautifil Flow光固化树脂 2ml/支 F10 另含5支注射头","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/118/AjPP6afRJYpK0889EpPKpjeU7jfPEP.jpg","marketprice":"150.00"},{"id":"374","title":"松风Beautifil II纳美复合树脂","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/118/fr0oZkrwkHL240lP44HmAHLHEwrewO.jpg","marketprice":"138.00"}]
         * moduletitle : —— 产品上新 ——
         * liststyle : block three
         */

        private String moduletitle;
        private String liststyle;
        private List<GoodsBeanX> goods;

        public String getModuletitle() {
            return moduletitle;
        }

        public void setModuletitle(String moduletitle) {
            this.moduletitle = moduletitle;
        }

        public String getListstyle() {
            return liststyle;
        }

        public void setListstyle(String liststyle) {
            this.liststyle = liststyle;
        }

        public List<GoodsBeanX> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBeanX> goods) {
            this.goods = goods;
        }

        public static class GoodsBeanX {
            /**
             * id : 376
             * title : 贺特佳 丁腈检查手套 燕麦款 护手  20盒起售
             * thumb : http://aoquan.maimaitoo.com/attachment/images/1/merch/117/vAqaH1aZB7yBK1k7Okn17H7q7h1WMh.jpg
             * marketprice : 32.50
             */

            private String id;
            private String title;
            private String thumb;
            private String marketprice;

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
        }
    }
}
