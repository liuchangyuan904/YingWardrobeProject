package com.ying.wardrobe.entity;

import java.util.List;

public class ClothesEntity {

    /**
     * msg : null
     * status : 0
     * data : [{"id":"1","createTime":"2020-05-14T01:18:29","updateTime":"2020-05-14T01:18:29","type":"duanxiu","photo":"http://117.159.17.27:10308/avatar/jpg/e997ba1bf5114b469f6dc61b2b072a88.jpg","price":22,"style":"33","userId":"10"},{"id":"2","createTime":"2020-05-14T01:19:52","updateTime":"2020-05-14T01:19:52","type":"duanxiu","photo":"http://117.159.17.27:10308/avatar/jpg/14ddb764d9e041409870d6aa980cd7cf.jpg","price":33,"style":"2","userId":"10"}]
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
