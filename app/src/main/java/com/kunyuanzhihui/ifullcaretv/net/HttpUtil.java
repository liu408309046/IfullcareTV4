package com.kunyuanzhihui.ifullcaretv.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/17- 17:16.
 */

public class HttpUtil  {

    public static AsyncHttpClient mHttpClient = new AsyncHttpClient();
    private static String TAG = "HttpUtil";

    public static void PostAsy(String url, AsyncHttpResponseHandler responseHandler, String... kvs) {
        RequestParams _RequestParams = new RequestParams();


        for (int i = 0; i < kvs.length; i += 2) {

            _RequestParams.put(kvs[i], kvs[i + 1]);
        }

        mHttpClient.post(url, _RequestParams, responseHandler);
    }
}
