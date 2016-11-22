package com.kunyuanzhihui.ifullcaretv.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.kunyuanzhihui.ifullcaretv.MyApplication;
import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.util.PreferencesUtils;
import com.kunyuanzhihui.ifullcaretv.widget.MyHorizontalScrollView;

public class MainActivity extends BaseActivity {
    @ViewResourceId(R.id.h_scrollview)
    MyHorizontalScrollView mScrollView;

    RelativeLayout rl_scrollContext;
    LinearLayout ll_healthshop, ll_university, ll_measure, ll_register, ll_government_service, ll_character_manager;

    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        Log.e("----当前用户---",MyApplication.getInstance().getCur_User().toString());
        Log.e("----当前token---",MyApplication.getInstance().getTv_token());
//        startService(new Intent(this, UsbService.class));

        ll_university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StandByActivity.class));
            }
        });

        ll_measure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, DetailActivity.class));

            }
        });

        ll_government_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, GovernmentServiceActivity.class));


            }
        });

        getWindow().getDecorView().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if (newFocus != null) {
                    newFocus.bringToFront();
                    mainUpView.setFocusView(newFocus, oldFocusView, 1.1f);
                }
                oldFocusView = newFocus;
            }
        });
        ll_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

            }
        });

        ll_character_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FamilyListActivity.class));
//                startActivity(new Intent(getApplicationContext(), CharacterManagerActivity.class));
            }
        });
        ll_healthshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HealthShopActivity.class));
            }
        });

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
