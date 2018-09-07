package com.likeit.aqe365.network.model.goods;

import java.io.Serializable;
import java.util.List;

public class GoodsSalesModel implements Serializable{
    private static final long serialVersionUID = 2L;
    private List<ListBean> list;
    private List<PriceBean> price;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<PriceBean> getPrice() {
        return price;
    }

    public void setPrice(List<PriceBean> price) {
        this.price = price;
    }

    public static class ListBean implements Serializable{
        /**
         * name : 规格
         * spec : [{"id":"326","title":"薄荷味","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/2018/04/A6sowBvkEGsA1wAVbnza4GF56A45Zf.jpg"},{"id":"387","title":"香橙味","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/2018/04/L5X06h0Bh6I6902T9jOIsbsc5P44El.jpg"},{"id":"388","title":"绿茶味","thumb":"http://aoquan.maimaitoo.com/attachment/images/1/2018/04/rOb0u3zob05UdEdFo10f3uEb3380Ss.jpg"}]
         */

        private String name;
        private List<SpecBean> spec;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SpecBean> getSpec() {
            return spec;
        }

        public void setSpec(List<SpecBean> spec) {
            this.spec = spec;
        }

        public static class SpecBean implements Serializable{
            /**
             * id : 326
             * title : 薄荷味
             * thumb : http://aoquan.maimaitoo.com/attachment/images/1/2018/04/A6sowBvkEGsA1wAVbnza4GF56A45Zf.jpg
             */

            private String id = "";
            private String title="";
            private String thumb;
            private boolean isSelect = false;
            private boolean isCanSelect = true;

            public boolean isCanSelect() {
                return isCanSelect;
            }

            public void setCanSelect(boolean canSelect) {
                isCanSelect = canSelect;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
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

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }
    }

    public static class PriceBean implements Serializable{
        /**
         * stock : 888
         * marketprice : 12.00
         * specs : 326
         * "id": "616",
         */

        private String stock;
        private String marketprice;
        private String specs = "";
        private String id= "";
        private String title= "";

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getMarketprice() {
            return marketprice;
        }

        public void setMarketprice(String marketprice) {
            this.marketprice = marketprice;
        }

        public String getSpecs() {
            return specs;
        }

        public void setSpecs(String specs) {
            this.specs = specs;
        }
    }
}
