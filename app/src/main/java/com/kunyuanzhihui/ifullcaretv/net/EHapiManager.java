package com.kunyuanzhihui.ifullcaretv.net;

import com.kunyuanzhihui.ifullcaretv.MyApplication;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/16- 17:22.
 */

public class EHapiManager {


    /**
     * 获取二维码链接
     *
     * @param callback
     */
    public static void get_qecode(final EHResponse callback) {

        HttpUtil.PostAsy(TvAPI.GET_TV_LOGIN_QRCODE, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onError(responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                callback.onSuccess(responseString);
            }
        });

    }

    /**
     * 监控二维码是否扫码
     *
     * @param TVtoken
     * @param callback
     */
    public static void monitoring_qecode(String TVtoken, final EHResponse callback) {

        HttpUtil.PostAsy(TvAPI.MONITORING_QRCODE, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onError(responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                callback.onSuccess(responseString);
            }
        }, "TVtoken", TVtoken);

    }

    /**
     * 获取服务类型
     *
     * @param callback
     */
    public static void getServicesType(String TVtoken, final EHResponse callback) {

        HttpUtil.PostAsy(TvAPI.GET_SERVICES_TYPE, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onError(responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                callback.onSuccess(responseString);
            }
        }, "token", TVtoken);

    }

    /**
     * @param type_id  类别
     * @param sortid   1.按推荐排序2.按价格排序3.按好评排序
     * @param callback
     */
    public static void getServicesList(String type_id, String sortid, String TVtoken, final EHResponse callback) {

        HttpUtil.PostAsy(TvAPI.GET_SERVICES_LIST, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onError(responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                callback.onSuccess(responseString);
            }
        }, "type_id", type_id, "sortid", sortid, "token", TVtoken);

    }

    /**
     * 获取家庭组
     * @param TVtoken
     * @param callback
     */
    public static void getMyFamilys(String TVtoken, final EHResponse callback) {

        HttpUtil.PostAsy(TvAPI.GET_FAMILY_LIST, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onError(responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                callback.onSuccess(responseString);
            }
        }, "token", TVtoken);


    }

    /**
     * 根据家庭组获取成员
     * @param family_id
     * @param TVtoken
     * @param callback
     */
    public static void getFamilyMembers(String family_id,String TVtoken, final EHResponse callback) {

        HttpUtil.PostAsy(TvAPI.GET_FAMILY_MEMBERS, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onError(responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                callback.onSuccess(responseString);
            }
        }, "family_id",family_id,"token", TVtoken);
    }


    public static void getHealthshot_goodsType(String TVtoken, final EHResponse callback) {

        HttpUtil.PostAsy(TvAPI.GET_HEALTHSHOP_TYPE_MENU, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onError(responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                callback.onSuccess(responseString);
            }
        },"token", TVtoken);


    }
}
