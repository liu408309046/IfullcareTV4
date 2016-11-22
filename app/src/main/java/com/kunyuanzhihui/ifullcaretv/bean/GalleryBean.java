package com.kunyuanzhihui.ifullcaretv.bean;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/14- 16:19.
 */

public class GalleryBean {
    public int headRes;
    public String headText;

    public GalleryBean(int headRes, String headText) {
        this.headRes = headRes;
        this.headText = headText;
    }

    public int getHeadRes() {
        return headRes;
    }

    public void setHeadRes(int headRes) {
        this.headRes = headRes;
    }

    public String getHeadText() {
        return headText;
    }

    public void setHeadText(String headText) {
        this.headText = headText;
    }
}
