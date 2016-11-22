package com.kunyuanzhihui.ifullcaretv.bean;

/**
 * 说明：
 * 作者： 刘小俊程序猿
 * 时间：2016/11/9- 17:53.
 */

public class ServiceBean {
    private int id;
    private int bg_res;
    private String title;

    public ServiceBean(int id, int bg_res, String title) {
        this.id = id;
        this.bg_res = bg_res;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBg_res() {
        return bg_res;
    }

    public void setBg_res(int bg_res) {
        this.bg_res = bg_res;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
