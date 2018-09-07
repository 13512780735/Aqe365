package com.likeit.aqe365.network.model;

import java.util.List;

public class HomeModel {

    /**
     * id : 1174
     * name : 牙科耗材
     * twotier : [{"id":"1177","name":"口腔常用耗材类","goods":{"id":"1255","name":"器械盒","thumb":"images/1/2018/05/Mr4DvrhR4HRRkKRRAR9ksOikVVGglo.png"}},{"id":"1176","name":"口内辅助材料类","goods":{"id":"1231","name":"根管治疗类","thumb":"images/1/2018/04/rw20K02wcsKH72s37dN0s022nRK76n.jpg"}},{"id":"1178","name":"印模材料","goods":{"id":"1226","name":"硅橡胶类","thumb":"images/1/2018/05/d09gPrNzS6m6RXMpnCbu9sFUfgLBNN.png"}},{"id":"1175","name":"车针、扩锉类","goods":{"id":"1224","name":"车针类","thumb":"images/1/2018/05/u4x6zV6RZv6vRT22sUJU3zSS26SVs4.png"}},{"id":"1179","name":"正畸产品类","goods":{"id":"1227","name":"正畸弓丝类","thumb":"images/1/2018/04/t9N3w18ZnWI3J8WIG13iQE96IcCuke.jpg"}},{"id":"1272","name":"修复材料","goods":{"id":"1273","name":"暂封材料","thumb":""}},{"id":"1278","name":"口腔器械","goods":{"id":"1279","name":"钳子牙挺类","thumb":"images/1/2018/05/dEhWkbe560ECiuWBQ056ze655icwIk.jpg"}}]
     */

    private String id;
    private String name;
    private List<TwotierBean> twotier;

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

    public List<TwotierBean> getTwotier() {
        return twotier;
    }

    public void setTwotier(List<TwotierBean> twotier) {
        this.twotier = twotier;
    }

    public static class TwotierBean {
        /**
         * id : 1177
         * name : 口腔常用耗材类
         * goods : {"id":"1255","name":"器械盒","thumb":"images/1/2018/05/Mr4DvrhR4HRRkKRRAR9ksOikVVGglo.png"}
         */

        private String id;
        private String name;
        private GoodsBean goods;

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

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * id : 1255
             * name : 器械盒
             * thumb : images/1/2018/05/Mr4DvrhR4HRRkKRRAR9ksOikVVGglo.png
             */

            private String id;
            private String name;
            private String thumb;

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

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }
    }
}
