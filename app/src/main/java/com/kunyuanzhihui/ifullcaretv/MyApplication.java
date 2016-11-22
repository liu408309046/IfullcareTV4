package com.kunyuanzhihui.ifullcaretv;

import android.app.Application;

import com.google.gson.Gson;
import com.kunyuanzhihui.ifullcaretv.bean.CurUser;
import com.kunyuanzhihui.ifullcaretv.bean.Userbean;
import com.kunyuanzhihui.ifullcaretv.util.PreferencesUtils;

import org.xutils.x;

/**
 * Created by Administrator on 2016/10/19.
 */

public class MyApplication extends Application {

    private static MyApplication Instance = null;
    private Userbean.DataBean cur_User;


    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
        x.Ext.init(this);
    }


    public static synchronized MyApplication getInstance() {
        if (Instance == null) {
            Instance = new MyApplication();
        }
        return Instance;
    }


    public String getTv_token() {

        return PreferencesUtils.getString(this, "tv_token");
    }

    public void setTv_token(String tv_token) {
        PreferencesUtils.putString(this, "tv_token", tv_token);
    }

    public Userbean.DataBean getCur_User() {

        if (cur_User == null) {
            String info = PreferencesUtils.getString(this, "cur_User");
            cur_User = new Gson().fromJson(info, Userbean.DataBean.class);
        }
        return cur_User;
    }

    public void setCur_User(Userbean.DataBean cur_User) {
        String info = new Gson().toJson(cur_User);
        PreferencesUtils.putString(this, "cur_User", info);
    }


    public boolean isLogin() {
        return PreferencesUtils.getBoolean(this, "isLogin", false);
    }

    public void setIsLogin(boolean islogin) {
        PreferencesUtils.putBoolean(this, "isLogin", islogin);
    }
}
