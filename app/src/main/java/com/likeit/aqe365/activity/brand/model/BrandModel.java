package com.likeit.aqe365.activity.brand.model;

import java.util.List;

public class BrandModel {

    private List<BannerBean> banner;
    private List<LogoBean> logo;
    private List<LogocategoryBean> logocategory;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<LogoBean> getLogo() {
        return logo;
    }

    public void setLogo(List<LogoBean> logo) {
        this.logo = logo;
    }

    public List<LogocategoryBean> getLogocategory() {
        return logocategory;
    }

    public void setLogocategory(List<LogocategoryBean> logocategory) {
        this.logocategory = logocategory;
    }

    public static class BannerBean {
        /**
         * link : www
         * thumb : http://aoquan.maimaitoo.com/attachment/images/1/2018/08/Sk7oF78FrXoR7kCr8KC8ORREwxh7NU.jpg
         */

        private String link;
        private String thumb;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
    }

//    public static class LogoBean {
//        /**
//         * id : 4
//         * name : 澳泉
//         * advimg : http://aoquan.maimaitoo.com/attachment/images/1/2018/06/jGZXkRZWnhHNnHHnz8R8GxNmr8wqZw.jpg
//         */
//
//        private String id;
//        private String name;
//        private String advimg;
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getAdvimg() {
//            return advimg;
//        }
//
//        public void setAdvimg(String advimg) {
//            this.advimg = advimg;
//        }
//    }

    public static class LogocategoryBean {
        /**
         * id : 4
         * name : 澳泉
         * advertise1 : http://aoquan.maimaitoo.com/imgs/GO5.png
         * advertiseurl1 :
         * recommend : [{"id":"362","title":"北京德联/DELIAN 一次性使用防护帽(蓝色条形帽)","marketprice":"6.00","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/f4ofcoc2o2no1Q1NyO8fO481d2hZD4.jpg","showsales":"1","productprice":"9.00"},{"id":"345","title":"西湖巴尔 网底舌侧拉钩","marketprice":"25.03","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/CZ3LcF95g0Emqj744J111ij23qJ72q.jpg","showsales":"1","productprice":"28.00"},{"id":"324","title":"康田正 豆瓣成型片套装","marketprice":"1500.00","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/k6Cln6T9562rRcDH3CrNlTlKcKk35L.jpg","showsales":"1","productprice":"1600.00"},{"id":"296","title":"力乐 乳胶检查手套【有粉】 10盒起售","marketprice":"28.00","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/vkLlGvaVACG22e5Qpva122LNqEDaQK.jpg","showsales":"1","productprice":"0.00"},{"id":"272","title":"博凡一次性口腔器械盒","marketprice":"205.00","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/IC41D1qOhAcaTBth91qodH1ElnT644.jpg","showsales":"1","productprice":"0.00"},{"id":"249","title":"观雅窝沟封闭剂(黄） 1.5ML/支","marketprice":"45.00","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/merch/117/fuXWHH6iiZexe96RxwX8cuucsR7Hzw.jpg","showsales":"1","productprice":"50.00"}]
         */

        private String id;
        private String name;
        private String advertise1;
        private String advertiseurl1;
        private List<RecommendBean> recommend;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdvertise1() {
            return advertise1;
        }

        public void setAdvertise1(String advertise1) {
            this.advertise1 = advertise1;
        }

        public String getAdvertiseurl1() {
            return advertiseurl1;
        }

        public void setAdvertiseurl1(String advertiseurl1) {
            this.advertiseurl1 = advertiseurl1;
        }

        public List<RecommendBean> getRecommend() {
            return recommend;
        }

        public void setRecommend(List<RecommendBean> recommend) {
            this.recommend = recommend;
        }

        public static class RecommendBean {
            /**
             * id : 362
             * title : 北京德联/DELIAN 一次性使用防护帽(蓝色条形帽)
             * marketprice : 6.00
             * thumb : http://aoquan.maimaitoo.com/attachment/images/1/merch/117/f4ofcoc2o2no1Q1NyO8fO481d2hZD4.jpg
             * showsales : 1
             * productprice : 9.00
             */

            private String id;
            private String title;
            private String marketprice;
            private String thumb;
            private String showsales;
            private String productprice;

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

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getShowsales() {
                return showsales;
            }

            public void setShowsales(String showsales) {
                this.showsales = showsales;
            }

            public String getProductprice() {
                return productprice;
            }

            public void setProductprice(String productprice) {
                this.productprice = productprice;
            }
        }
    }
}
