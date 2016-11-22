package com.kunyuanzhihui.ifullcaretv.bean;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/16- 17:47.
 */

public class QrcodeBean {


    /**
     * result_code : 0
     * message : 获取数据成功
     * data : {"token":"6ebea05c08083a3799e64ce8058c078b","qcode":"eyJ0eXBlIjoxLCJjb250ZW50IjoiNmViZWEwNWMwODA4M2EzNzk5ZTY0Y2U4MDU4YzA3OGIifQ=="}
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
         * token : 6ebea05c08083a3799e64ce8058c078b
         * qcode : eyJ0eXBlIjoxLCJjb250ZW50IjoiNmViZWEwNWMwODA4M2EzNzk5ZTY0Y2U4MDU4YzA3OGIifQ==
         */

        private String token;
        private String qcode;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getQcode() {
            return qcode;
        }

        public void setQcode(String qcode) {
            this.qcode = qcode;
        }
    }
}
