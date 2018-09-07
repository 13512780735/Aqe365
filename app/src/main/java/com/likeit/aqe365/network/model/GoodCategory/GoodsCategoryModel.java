package com.likeit.aqe365.network.model.GoodCategory;

import java.io.Serializable;
import java.util.List;

public class GoodsCategoryModel implements Serializable{


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * id : 1174
         * name : 牙科耗材
         * twotier : [{"id":"1177","name":"口腔常用耗材类","goods":[{"id":"1255","name":"器械盒","thumb":"images/1/2018/05/Mr4DvrhR4HRRkKRRAR9ksOikVVGglo.png"},{"id":"1262","name":"牙科吸管","thumb":"images/1/2018/05/p0dVDxF6261b32H11F12FUU2E2ZeiU.png"},{"id":"1232","name":"水枪头","thumb":"images/1/2018/04/G64bd1b8OBb1eE1BgcZzg65CbEw1G4.jpg"},{"id":"1229","name":"牙托","thumb":"images/1/2018/05/uq1LkNzQUhKte68Kln8T5L65R8ytik.png"},{"id":"1251","name":"咬合纸","thumb":"images/1/2018/04/JBbDdA2n58h660XpAUa8cCn0z6o5ua.jpg"},{"id":"1228","name":"楔子","thumb":"images/1/2018/04/Kb9bq12JeBe391ch89987tV519H1ME.jpg"},{"id":"1261","name":"口镜","thumb":"images/1/2018/05/waM2R0lmydnLLA3EMn2JMROJm9nz4P.png"},{"id":"1260","name":"镊子","thumb":"images/1/2018/05/hkgAh7TTf9ftTkHZu3ahZPt6CY93kI.png"},{"id":"1263","name":"探针","thumb":"images/1/2018/05/r2MmINSv67B924tiF2bI70sN6IMc4z.png"}]},{"id":"1176","name":"口内辅助材料类","goods":[{"id":"1231","name":"根管治疗类","thumb":"images/1/2018/04/rw20K02wcsKH72s37dN0s022nRK76n.jpg"},{"id":"1230","name":"口腔护理类","thumb":"images/1/2018/04/A6sowBvkEGsA1wAVbnza4GF56A45Zf.jpg"},{"id":"1254","name":"口腔内科治疗","thumb":"images/1/2018/05/Buts0Bt21ZLLJvTD0tyuD12jlDYStj.png"}]},{"id":"1178","name":"印模材料","goods":[{"id":"1226","name":"硅橡胶类","thumb":"images/1/2018/05/d09gPrNzS6m6RXMpnCbu9sFUfgLBNN.png"},{"id":"1259","name":"石膏类","thumb":"images/1/2018/05/vJ8RjLRMr6M044Rg80JGJlGjJJ6MMU.png"},{"id":"1225","name":"藻酸盐类","thumb":"images/1/2018/04/sUQvjNLvVy8N8JJ5NVUJ5KVvLFJoZL.jpg"}]},{"id":"1175","name":"车针、扩锉类","goods":[{"id":"1224","name":"车针类","thumb":"images/1/2018/05/u4x6zV6RZv6vRT22sUJU3zSS26SVs4.png"},{"id":"1223","name":"磨头类","thumb":"images/1/2018/05/dWabQA7f7BF6xZMMQ7QQAXEmX2wB6t.png"},{"id":"1253","name":"扩锉类","thumb":"images/1/2018/05/rK0EQe02BEIGKgzgiub0e23mMT25Kt.png"}]},{"id":"1179","name":"正畸产品类","goods":[{"id":"1227","name":"正畸弓丝类","thumb":"images/1/2018/04/t9N3w18ZnWI3J8WIG13iQE96IcCuke.jpg"},{"id":"1256","name":"正畸托槽类","thumb":"images/1/2018/05/v7huZK2mM2gzkddkgV7uDh2288zUrg.png"},{"id":"1257","name":"正畸附件类","thumb":"images/1/2018/05/v7W7THelEE1KHf4MTDdWFTAK76Kff4.png"},{"id":"1276","name":"钳子牙挺类","thumb":"images/1/2018/05/dEhWkbe560ECiuWBQ056ze655icwIk.jpg"}]},{"id":"1272","name":"修复材料","goods":[{"id":"1273","name":"暂封材料","thumb":""},{"id":"1274","name":"树脂填充","thumb":"images/1/2018/05/Uf23HK338z3fd3F0B3IdBr8HbHKiHX.png"},{"id":"1275","name":"玻璃离子","thumb":"images/1/2018/06/A46kz6Y6lK9Tk4C5YbbKYkYt4jk99P.jpg"}]},{"id":"1278","name":"口腔器械","goods":[{"id":"1279","name":"钳子牙挺类","thumb":"images/1/2018/05/dEhWkbe560ECiuWBQ056ze655icwIk.jpg"},{"id":"1280","name":"外科器械类","thumb":"images/1/2018/05/x9CINg99Z9MZ990IMCPF4NCpqp6qVi.jpg"}]}]
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

        public static class TwotierBean implements Serializable{
            /**
             * id : 1177
             * name : 口腔常用耗材类
             * goods : [{"id":"1255","name":"器械盒","thumb":"images/1/2018/05/Mr4DvrhR4HRRkKRRAR9ksOikVVGglo.png"},{"id":"1262","name":"牙科吸管","thumb":"images/1/2018/05/p0dVDxF6261b32H11F12FUU2E2ZeiU.png"},{"id":"1232","name":"水枪头","thumb":"images/1/2018/04/G64bd1b8OBb1eE1BgcZzg65CbEw1G4.jpg"},{"id":"1229","name":"牙托","thumb":"images/1/2018/05/uq1LkNzQUhKte68Kln8T5L65R8ytik.png"},{"id":"1251","name":"咬合纸","thumb":"images/1/2018/04/JBbDdA2n58h660XpAUa8cCn0z6o5ua.jpg"},{"id":"1228","name":"楔子","thumb":"images/1/2018/04/Kb9bq12JeBe391ch89987tV519H1ME.jpg"},{"id":"1261","name":"口镜","thumb":"images/1/2018/05/waM2R0lmydnLLA3EMn2JMROJm9nz4P.png"},{"id":"1260","name":"镊子","thumb":"images/1/2018/05/hkgAh7TTf9ftTkHZu3ahZPt6CY93kI.png"},{"id":"1263","name":"探针","thumb":"images/1/2018/05/r2MmINSv67B924tiF2bI70sN6IMc4z.png"}]
             */

            private String id;
            private String name;
            private List<GoodsBean> goods;

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

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean  {
                /**
                 * id : 1255
                 * name : 器械盒
                 * thumb : images/1/2018/05/Mr4DvrhR4HRRkKRRAR9ksOikVVGglo.png
                 */

                private String id;
                private String name;
                private String thumb;
                private boolean isCheck;

                public boolean isCheck() {
                    return isCheck;
                }

                public void setCheck(boolean check) {
                    isCheck = check;
                }

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
}
