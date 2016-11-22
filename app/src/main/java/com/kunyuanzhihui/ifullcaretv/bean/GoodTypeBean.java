package com.kunyuanzhihui.ifullcaretv.bean;

import java.util.List;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/19- 10:51.
 */

public class GoodTypeBean {


    /**
     * result_code : 0
     * message : 获取成功
     * data : [{"cat_name":"运动户外","cat_id":"16"},{"cat_name":"女装","cat_id":"21"},{"cat_name":"鞋靴","cat_id":"44"},{"cat_name":"母婴童","cat_id":"69"},{"cat_name":"钻石","cat_id":"132"},{"cat_name":"箱包","cat_id":"215"},{"cat_name":"配饰","cat_id":"265"},{"cat_name":"美妆","cat_id":"324"},{"cat_name":"家居","cat_id":"391"},{"cat_name":"手机数码","cat_id":"459"},{"cat_name":"食品酒饮","cat_id":"532"},{"cat_name":"电脑办公","cat_id":"596"},{"cat_name":"家电","cat_id":"622"}]
     */

    private int result_code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cat_name : 运动户外
         * cat_id : 16
         */

        private String cat_name;
        private String cat_id;

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }
    }
}
