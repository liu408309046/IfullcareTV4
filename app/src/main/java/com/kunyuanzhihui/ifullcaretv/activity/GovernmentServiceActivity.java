package com.kunyuanzhihui.ifullcaretv.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.kunyuanzhihui.ifullcaretv.MyApplication;
import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.net.EHResponse;
import com.kunyuanzhihui.ifullcaretv.net.EHapiManager;
import com.kunyuanzhihui.ifullcaretv.widget.MyHorizontalScrollView;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/8- 15:59.
 */
public class GovernmentServiceActivity extends BaseActivity{
    @ViewResourceId(R.id.h_scrollview)

    MyHorizontalScrollView mScrollView;

    RelativeLayout rl_mroe;


    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.government_service_activity;
    }

    @Override
    protected void init() {



        rl_mroe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GovernmentServiceActivity.this,MoreServiceActivity.class));
            }
        });
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if(newFocus!=null){
                    newFocus.bringToFront();
                    mainUpView.setFocusView(newFocus,oldFocusView,1.1f);
                }
                oldFocusView = newFocus;
            }
        });

        EHapiManager.getServicesType(MyApplication.getInstance().getTv_token(),new EHResponse() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(String request) {

            }
        });

    }


}
