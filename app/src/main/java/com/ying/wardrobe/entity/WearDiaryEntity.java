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
     * data : [{"localDate":"2020-05-14","dailyList":[{"id":"1","createTime":"2020-05-14T03:08:13","updateTime":"2020-05-14T03:08:13","yifuId":"1","userId":"10","date":"2020-05-14","yifu":{"id":"1","createTime":"2020-05-14T01:18:29","updateTime":"2020-05-14T01:18:29","type":"duanxiu","photo":"http://117.159.17.27:10308/avatar/jpg/e997ba1bf5114b469f6dc61b2b072a88.jpg","price":22,"style":"33","userId":"10"}}]}]
     */

    private Object msg;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * localDate : 2020-05-14
         * dailyList : [{"id":"1","createTime":"2020-05-14T03:08:13","updateTime":"2020-05-14T03:08:13","yifuId":"1","userId":"10","date":"2020-05-14","yifu":{"id":"1","createTime":"2020-05-14T01:18:29","updateTime":"2020-05-14T01:18:29","type":"duanxiu","photo":"http://117.159.17.27:10308/avatar/jpg/e997ba1bf5114b469f6dc61b2b072a88.jpg","price":22,"style":"33","userId":"10"}}]
         */

        private String localDate;
        private List<DailyListBean> dailyList;

        public String getLocalDate() {
            return localDate;
        }

        public void setLocalDate(String localDate) {
            this.localDate = localDate;
        }

        public List<DailyListBean> getDailyList() {
            return dailyList;
        }

        public void setDailyList(List<DailyListBean> dailyList) {
            this.dailyList = dailyList;
        }

        public static class DailyListBean {
            /**
             * id : 1
             * createTime : 2020-05-14T03:08:13
             * updateTime : 2020-05-14T03:08:13
             * yifuId : 1
             * userId : 10
             * date : 2020-05-14
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
}
