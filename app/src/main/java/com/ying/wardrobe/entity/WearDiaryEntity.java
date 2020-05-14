package com.ying.wardrobe.entity;

import java.util.List;

/**
 * @ProjectName: WardrobeProject
 * @Package: com.ying.wardrobe.entity
 * @ClassName: WearDiaryEntity
 * @Description: java类作用描述
 * @Author: liucy
 * @CreateDate: 2020/5/14 0014 上午 11:10
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/14 0014 上午 11:10
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class WearDiaryEntity {

    /**
     * msg : null
     * status : 0
     * data : [[{"id":"1","createTime":"2020-05-14T03:08:13","updateTime":"2020-05-14T04:38:17","yifuId":"1","userId":"10","date":"2020-05-13","yifu":{"id":"1","createTime":"2020-05-14T01:18:29","updateTime":"2020-05-14T01:18:29","type":"duanxiu","photo":"http://117.159.17.27:10308/avatar/jpg/e997ba1bf5114b469f6dc61b2b072a88.jpg","price":22,"style":"33","userId":"10"}}],[{"id":"2","createTime":"2020-05-14T03:56:55","updateTime":"2020-05-14T03:56:55","yifuId":"2","userId":"10","date":"2020-05-14","yifu":{"id":"2","createTime":"2020-05-14T01:19:52","updateTime":"2020-05-14T01:19:52","type":"duanxiu","photo":"http://117.159.17.27:10308/avatar/jpg/14ddb764d9e041409870d6aa980cd7cf.jpg","price":33,"style":"2","userId":"10"}},{"id":"3","createTime":"2020-05-14T04:11:21","updateTime":"2020-05-14T04:11:21","yifuId":"3","userId":"10","date":"2020-05-14","yifu":{"id":"3","createTime":"2020-05-14T01:56:02","updateTime":"2020-05-14T01:56:02","type":"changku","photo":"http://117.159.17.27:10308/avatar/jpg/98c1d7e001ab41509533ac043e41fd5e.jpg","price":333,"style":"3333","userId":"10"}},{"id":"4","createTime":"2020-05-14T04:18:18","updateTime":"2020-05-14T04:18:18","yifuId":"5","userId":"10","date":"2020-05-14","yifu":{"id":"5","createTime":"2020-05-14T01:57:53","updateTime":"2020-05-14T01:57:53","type":"waitao","photo":"http://117.159.17.27:10308/avatar/jpg/cc2c8f1ad65d4637a8a1f2775b27f3c1.jpg","price":33,"style":"22","userId":"10"}},{"id":"5","createTime":"2020-05-14T04:23:21","updateTime":"2020-05-14T04:23:21","yifuId":"8","userId":"10","date":"2020-05-14","yifu":{"id":"8","createTime":"2020-05-14T04:22:14","updateTime":"2020-05-14T04:22:14","type":"qunzi","photo":"http://117.159.17.27:10308/avatar/jpg/fbeaae69141844fea4c72578451827d3.jpg","price":333,"style":"二哥","userId":"10"}}]]
     */

    private Object msg;
    private int status;
    private List<List<DataBean>> data;

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

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * createTime : 2020-05-14T03:08:13
         * updateTime : 2020-05-14T04:38:17
         * yifuId : 1
         * userId : 10
         * date : 2020-05-13
         * yifu : {"id":"1","createTime":"2020-05-14T01:18:29","updateTime":"2020-05-14T01:18:29","type":"duanxiu","photo":"http://117.159.17.27:10308/avatar/jpg/e997ba1bf5114b469f6dc61b2b072a88.jpg","price":22,"style":"33","userId":"10"}
         */

        private String id;
        private String createTime;
        private String updateTime;
        private String yifuId;
        private String userId;
        private String date;
        private YifuBean yifu;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getYifuId() {
            return yifuId;
        }

        public void setYifuId(String yifuId) {
            this.yifuId = yifuId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public YifuBean getYifu() {
            return yifu;
        }

        public void setYifu(YifuBean yifu) {
            this.yifu = yifu;
        }

        public static class YifuBean {
            /**
             * id : 1
             * createTime : 2020-05-14T01:18:29
             * updateTime : 2020-05-14T01:18:29
             * type : duanxiu
             * photo : http://117.159.17.27:10308/avatar/jpg/e997ba1bf5114b469f6dc61b2b072a88.jpg
             * price : 22
             * style : 33
             * userId : 10
             */

            private String id;
            private String createTime;
            private String updateTime;
            private String type;
            private String photo;
            private int price;
            private String style;
            private String userId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
