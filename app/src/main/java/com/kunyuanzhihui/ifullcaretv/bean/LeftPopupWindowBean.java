package com.kunyuanzhihui.ifullcaretv.bean;

/**
 * Created by Administrator on 2016/11/1.
 */

public class LeftPopupWindowBean {
    int imgRes;
    String itemText;
    int itemProgress;

    public LeftPopupWindowBean(int imgRes, String itemText, int itemProgress) {
        this.imgRes = imgRes;
        this.itemText = itemText;
        this.itemProgress = itemProgress;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public int getItemProgress() {
        return itemProgress;
    }

    public void setItemProgress(int itemProgress) {
        this.itemProgress = itemProgress;
    }
}
