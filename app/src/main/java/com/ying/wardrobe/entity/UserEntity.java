package com.ying.wardrobe.entity;

/**
 * @ProjectName: WardrobeProject
 * @Package: com.ying.wardrobe.entity
 * @ClassName: User
 * @Description: java类作用描述
 * @Author: liucy
 * @CreateDate: 2020/5/13 0013 下午 15:42
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/13 0013 下午 15:42
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class UserEntity {

    /**
     * data : {"age":0,"id":0,"nickname":"string","password":"string","phone":"string","tall":0,"username":"string","weight":0}
     * msg : string
     * status : 0
     */

    private DataBean data;
    private String msg;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * age : 0
         * id : 0
         * nickname : string
         * password : string
         * phone : string
         * tall : 0
         * username : string
         * weight : 0
         */

        private int age;
        private int id;
        private String nickname;
        private String password;
        private String phone;
        private int tall;
        private String username;
        private int weight;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getTall() {
            return tall;
        }

        public void setTall(int tall) {
            this.tall = tall;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
