package com.likeit.aqe365.activity.sort.bean;

import java.util.List;

public class CategoryListItemsModel {

    /**
     * list : [{"id":"327","title":"康田正 树脂成型片补充装","thumb":"images/1/merch/117/xRdS9AwS57SnNZwPWv5J6EE5xVEVJq.jpg","marketprice":"71.50","productprice":"71.50","showsales":"1"},{"id":"326","title":"康田正 小粘棒吸塑装","thumb":"images/1/merch/117/OTHwtTnJ5H8rH8Y668NHIJn8oDo8GJ.jpg","marketprice":"22.88","productprice":"26.00","showsales":"1"},{"id":"323","title":"观雅 木榴油","thumb":"images/1/merch/117/SZ5UQVu1oUlLZUa1l5C1cQQ51O5M6q.jpg","marketprice":"39.30","productprice":"50.00","showsales":"1"},{"id":"322","title":"观雅 无砷失活抑菌材料（快2-4天）","thumb":"images/1/merch/117/b7y37m5EMeKU5YGupi50RKkgEGGpuG.jpg","marketprice":"70.20","productprice":"80.00","showsales":"1"},{"id":"321","title":"观雅 丁香油","thumb":"images/1/merch/117/Ih7XHxA7B0hZHX5AqHHLEh17THbYqy.jpg","marketprice":"12.00","productprice":"18.00","showsales":"1"},{"id":"320","title":"观雅 无砷失活抑菌材料（快2-4天）","thumb":"images/1/merch/117/A3g3vaCjaGccQ32b2e3I2V9cKZCoOW.jpg","marketprice":"28.08","productprice":"44.00","showsales":"2"},{"id":"319","title":"观雅 牙周康软膏","thumb":"images/1/merch/117/LiNmNenH1zNHhNCXhv4iSN1YZnXnn1.jpg","marketprice":"28.08","productprice":"44.00","showsales":"1"},{"id":"254","title":"齿蒙 牙刷（定制LOGO）","thumb":"images/1/merch/117/gPCzMMZwNbYLWU9wmMZLY99Z1Synl9.png","marketprice":"38000.00","productprice":"5000.00","showsales":"1"},{"id":"252","title":"丝瑞妮 漱口水","thumb":"images/1/merch/117/W77844ecjc8J87ECc8cFeI8t81H1iJ.jpg","marketprice":"12.00","productprice":"15.00","showsales":"1"},{"id":"249","title":"观雅窝沟封闭剂(黄） 1.5ML/支","thumb":"images/1/merch/117/fuXWHH6iiZexe96RxwX8cuucsR7Hzw.jpg","marketprice":"45.00","productprice":"50.00","showsales":"1"},{"id":"248","title":"观雅 酸蚀剂","thumb":"images/1/merch/117/Ew5fzpOn0Sf5wP9Olpnwe0w92vKw0P.jpg","marketprice":"15.00","productprice":"20.00","showsales":"1"},{"id":"247","title":"观雅 氢氧化钠根管消毒糊剂","thumb":"images/1/merch/117/fp8TGf85i0gAmtH8AtAih5H50ZQFP0.jpg","marketprice":"50.00","productprice":"60.00","showsales":"1"},{"id":"246","title":"观雅 氢氧化钙根管消毒材料Ⅱ型【碘仿糊剂】","thumb":"images/1/merch/117/N2222K2RamoDyLX5cMmiXOCk511iiB.jpg","marketprice":"70.00","productprice":"80.00","showsales":"1"},{"id":"245","title":"观雅 光固化粘接剂","thumb":"images/1/merch/117/jLgzKHSd4FK4h49YFlI4TdBrI99BTD.jpg","marketprice":"28.80","productprice":"30.00","showsales":"1"},{"id":"244","title":"观雅 EDTA润滑剂","thumb":"images/1/merch/117/aQ4yxx71Zeu1YXJo1zX7Qz77X98d64.jpg","marketprice":"50.00","productprice":"80.00","showsales":"1"}]
     * total :
     * pagesize :
     */

    private String total;
    private String pagesize;
    private List<ListBean> list;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 327
         * title : 康田正 树脂成型片补充装
         * thumb : images/1/merch/117/xRdS9AwS57SnNZwPWv5J6EE5xVEVJq.jpg
         * marketprice : 71.50
         * productprice : 71.50
         * showsales : 1
         */

        private String id;
        private String title;
        private String thumb;
        private String marketprice;
        private String productprice;
        private String showsales;

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

        public String getProductprice() {
            return productprice;
        }

        public void setProductprice(String productprice) {
            this.productprice = productprice;
        }

        public String getShowsales() {
            return showsales;
        }

        public void setShowsales(String showsales) {
            this.showsales = showsales;
        }
    }
}
