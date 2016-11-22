package com.kunyuanzhihui.ifullcaretv.bean;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/17- 18:43.
 */

public class Userbean {

    /**
     * result_code : 0
     * message : 登录成功
     * data : {"user_name":"u150VFQL3603","real_name":null,"nickname":null,"height":"0","weight":"0","age":"0","mobile_phone":"15018433603","user_money":"0.00","zf_money":"0.00","bx_money":"0.00","address_id":"0","headimg":"http://120.76.212.89/ApiSlinw/../images/no_picture.gif","user_id":"14"}
     */

    private int result_code;
    private String message;
    private DataBean data;

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_name : u150VFQL3603
         * real_name : null
         * nickname : null
         * height : 0
         * weight : 0
         * age : 0
         * mobile_phone : 15018433603
         * user_money : 0.00
         * zf_money : 0.00
         * bx_money : 0.00
         * address_id : 0
         * headimg : http://120.76.212.89/ApiSlinw/../images/no_picture.gif
         * user_id : 14
         */

        private String user_name;
        private Object real_name;
        private Object nickname;
        private String height;
        private String weight;
        private String age;
        private String mobile_phone;
        private String user_money;
        private String zf_money;
        private String bx_money;
        private String address_id;
        private String headimg;
        private String user_id;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public Object getReal_name() {
            return real_name;
        }

        public void setReal_name(Object real_name) {
            this.real_name = real_name;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getZf_money() {
            return zf_money;
        }

        public void setZf_money(String zf_money) {
            this.zf_money = zf_money;
        }

        public String getBx_money() {
            return bx_money;
        }

        public void setBx_money(String bx_money) {
            this.bx_money = bx_money;
        }

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "user_name='" + user_name + '\'' +
                    ", real_name=" + real_name +
                    ", nickname=" + nickname +
                    ", height='" + height + '\'' +
                    ", weight='" + weight + '\'' +
                    ", age='" + age + '\'' +
                    ", mobile_phone='" + mobile_phone + '\'' +
                    ", user_money='" + user_money + '\'' +
                    ", zf_money='" + zf_money + '\'' +
                    ", bx_money='" + bx_money + '\'' +
                    ", address_id='" + address_id + '\'' +
                    ", headimg='" + headimg + '\'' +
                    ", user_id='" + user_id + '\'' +
                    '}';
        }
    }


}
