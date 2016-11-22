package com.kunyuanzhihui.ifullcaretv.bean;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/16- 9:36.
 */

public class NewCharacter {
    private int head_res;
    private String mark;
    private String name;
    private String nick;
    private String height;
    private String weight;
    private String age;
    private String phone_num;

    public NewCharacter() {
    }

    public NewCharacter(int head_res, String mark, String name, String nick, String height, String weight, String age, String phone_num) {
        this.head_res = head_res;
        this.mark = mark;
        this.name = name;
        this.nick = nick;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.phone_num = phone_num;
    }

    public int getHead_res() {
        return head_res;
    }

    public void setHead_res(int head_res) {
        this.head_res = head_res;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }
}
