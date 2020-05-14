package com.ying.wardrobe.entity;

/**
 * @ProjectName: WardrobeProject
 * @Package: com.ying.wardrobe.entity
 * @ClassName: WeatherEntity
 * @Description: java类作用描述
 * @Author: liucy
 * @CreateDate: 2020/5/14 0014 上午 10:29
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/14 0014 上午 10:29
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class WeatherEntity {

    /**
     * msg : null
     * status : 0
     * data : {"city":"沈阳","cityid":243,"citycode":"101070101","date":"2020-05-14","week":"星期四","weather":"阴","temp":"24","temphigh":"29","templow":"14","img":"2","humidity":"40","pressure":"1000","windspeed":"6.7","winddirect":"西南风","windpower":"4级","updatetime":"2020-05-14 09:33:00"}
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
         * city : 沈阳
         * cityid : 243
         * citycode : 101070101
         * date : 2020-05-14
         * week : 星期四
         * weather : 阴
         * temp : 24
         * temphigh : 29
         * templow : 14
         * img : 2
         * humidity : 40
         * pressure : 1000
         * windspeed : 6.7
         * winddirect : 西南风
         * windpower : 4级
         * updatetime : 2020-05-14 09:33:00
         */

        private String city;
        private int cityid;
        private String citycode;
        private String date;
        private String week;
        private String weather;
        private String temp;
        private String temphigh;
        private String templow;
        private String img;
        private String humidity;
        private String pressure;
        private String windspeed;
        private String winddirect;
        private String windpower;
        private String updatetime;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getTemphigh() {
            return temphigh;
        }

        public void setTemphigh(String temphigh) {
            this.temphigh = temphigh;
        }

        public String getTemplow() {
            return templow;
        }

        public void setTemplow(String templow) {
            this.templow = templow;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getWindspeed() {
            return windspeed;
        }

        public void setWindspeed(String windspeed) {
            this.windspeed = windspeed;
        }

        public String getWinddirect() {
            return winddirect;
        }

        public void setWinddirect(String winddirect) {
            this.winddirect = winddirect;
        }

        public String getWindpower() {
            return windpower;
        }

        public void setWindpower(String windpower) {
            this.windpower = windpower;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
