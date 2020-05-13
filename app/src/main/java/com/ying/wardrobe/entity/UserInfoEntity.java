package com.ying.wardrobe.entity;

/**
 * @ProjectName: WardrobeProject
 * @Package: com.ying.wardrobe.entity
 * @ClassName: UserInfo
 * @Description: java类作用描述
 * @Author: liucy
 * @CreateDate: 2020/5/13 0013 下午 15:51
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/13 0013 下午 15:51
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class UserInfoEntity {

    /**
     * msg : null
     * status : 0
     * data : {"id":"10","createTime":"2020-05-13T06:16:06","updateTime":"2020-05-13T06:16:06","username":"6666","password":"1111","nickname":"新用户","phone":"1589350566217","age":null,"weight":null,"tall":null}
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
         * id : 10
         * createTime : 2020-05-13T06:16:06
         * updateTime : 2020-05-13T06:16:06
         * username : 6666
         * password : 1111
         * nickname : 新用户
         * phone : 1589350566217
         * age : null
         * weight : null
         * tall : null
         */

        private String id;
        private String createTime;
        private String updateTime;
        private String username;
        private String password;
        private String nickname;
        private String phone;
        private Object age;
        private Object weight;
        private Object tall;

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public Object getTall() {
            return tall;
        }

        public void setTall(Object tall) {
            this.tall = tall;
        }
    }
}
