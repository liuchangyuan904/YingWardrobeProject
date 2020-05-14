package com.ying.wardrobe.entity;

import java.util.List;

/**
 * @ProjectName: WardrobeProject
 * @Package: com.ying.wardrobe.entity
 * @ClassName: StatisticsEntity
 * @Description: java类作用描述
 * @Author: liucy
 * @CreateDate: 2020/5/14 0014 下午 12:13
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/14 0014 下午 12:13
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class StatisticsEntity {


    /**
     * msg : null
     * status : 0
     * data : {"pinlv":{"changku":1,"qunzi":1,"duanxiu":2,"waitao":1},"list":[{"type":"duanxiu","count":2,"url":"http://117.159.17.27:10308/avatar/jpg/e997ba1bf5114b469f6dc61b2b072a88.jpg"},{"type":"changku","count":1,"url":"http://117.159.17.27:10308/avatar/jpg/98c1d7e001ab41509533ac043e41fd5e.jpg"},{"type":"qunzi","count":1,"url":"http://117.159.17.27:10308/avatar/jpg/fbeaae69141844fea4c72578451827d3.jpg"}]}
     */

    private Object msg;
    private int status;
    private DataBean data;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pinlv : {"changku":1,"qunzi":1,"duanxiu":2,"waitao":1}
         * list : [{"type":"duanxiu","count":2,"url":"http://117.159.17.27:10308/avatar/jpg/e997ba1bf5114b469f6dc61b2b072a88.jpg"},{"type":"changku","count":1,"url":"http://117.159.17.27:10308/avatar/jpg/98c1d7e001ab41509533ac043e41fd5e.jpg"},{"type":"qunzi","count":1,"url":"http://117.159.17.27:10308/avatar/jpg/fbeaae69141844fea4c72578451827d3.jpg"}]
         */

        private PinlvBean pinlv;
        private List<ListBean> list;

        public PinlvBean getPinlv() {
            return pinlv;
        }

        public void setPinlv(PinlvBean pinlv) {
            this.pinlv = pinlv;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PinlvBean {
            /**
             * changku : 1
             * qunzi : 1
             * duanxiu : 2
             * waitao : 1
             */

            private int changku;
            private int qunzi;
            private int duanxiu;
            private int waitao;
            private int maozi;

            public int getMaozi() {
                return maozi;
            }

            public void setMaozi(int maozi) {
                this.maozi = maozi;
            }

            public int getChangku() {
                return changku;
            }

            public void setChangku(int changku) {
                this.changku = changku;
            }

            public int getQunzi() {
                return qunzi;
            }

            public void setQunzi(int qunzi) {
                this.qunzi = qunzi;
            }

            public int getDuanxiu() {
                return duanxiu;
            }

            public void setDuanxiu(int duanxiu) {
                this.duanxiu = duanxiu;
            }

            public int getWaitao() {
                return waitao;
            }

            public void setWaitao(int waitao) {
                this.waitao = waitao;
            }
        }

        public static class ListBean {
            /**
             * type : duanxiu
             * count : 2
             * url : http://117.159.17.27:10308/avatar/jpg/e997ba1bf5114b469f6dc61b2b072a88.jpg
             */

            private String type;
            private int count;
            private String url;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
