package com.kunyuanzhihui.ifullcaretv.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.bean.ServiceBean;
import com.kunyuanzhihui.ifullcaretv.util.Adapter;
import com.kunyuanzhihui.ifullcaretv.util.ViewHolder;
import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.view.GridViewTV;
import com.open.androidtvwidget.view.ListViewTV;
import com.open.androidtvwidget.view.MainUpView;

import java.util.ArrayList;
import java.util.List;


/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/9- 10:01.
 */
public class MoreServiceActivity extends BaseActivity {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private View mcontentView;
    private Adapter<ServiceBean> menuAdapter2;
    private Adapter<String> menuAdapter;

    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_moreservice;
    }


    private ListView lv_menu;
    private GridView gv_service;
    private MainUpView mainUpView;
    private RecyclerViewBridge recyclerViewBridge;
    List<String> menulist = new ArrayList<>();
    private List<ServiceBean> service_list = new ArrayList<ServiceBean>();
    private View oldview;

    @Override
    protected void init() {

        initdata();
        mcontentView = getLayoutInflater().inflate(R.layout.activity_moreservice, null, false);

        initMenuList();
        initserviceGrid();


    }

    private void initdata() {
        mainUpView = (MainUpView) findViewById(R.id.mainUpView);
        mainUpView.setEffectBridge(new RecyclerViewBridge());
        recyclerViewBridge = (RecyclerViewBridge) mainUpView.getEffectBridge();
        recyclerViewBridge.setUpRectResource(R.mipmap.select);
        recyclerViewBridge.setDrawUpRectPadding(new Rect(19, 19, 19, 19));

        menulist.add("设备维修");
        menulist.add("生活照料");
        menulist.add("心理慰藉");
        menulist.add("卫生保健");
        menulist.add("法律服务");
        menulist.add("文化娱乐");

        service_list.add(new ServiceBean(0, R.mipmap.blue, "厨卫安装与维护"));
        service_list.add(new ServiceBean(0, R.mipmap.brightred, "家电安装"));
        service_list.add(new ServiceBean(0, R.mipmap.bluepurple, "家居维护"));
        service_list.add(new ServiceBean(0, R.mipmap.purple2, "照明安装与维护"));
    }

    private void initMenuList() {
        menuAdapter = new Adapter<String>(mcontentView.getContext(), menulist, R.layout.item_menu) {
            @Override
            public void insert(ViewHolder helper, String item) {
                TextView tv = helper.getView(R.id.tv_menu);
                tv.setText(item);

            }
        };
        lv_menu.setAdapter(menuAdapter);


        lv_menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recyclerViewBridge.setFocusView(view, 1.1f);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 0; i < menulist.size(); i++) {
                    parent.getChildAt(i).setBackgroundResource(R.color.gray);

                }
                view.setBackgroundResource(R.color.blue);
                switch (position) {
                    case 0:

                        service_list.clear();
                        service_list.add(new ServiceBean(0, R.mipmap.blue, "厨卫安装与维护"));
                        service_list.add(new ServiceBean(0, R.mipmap.brightred, "家电安装"));
                        service_list.add(new ServiceBean(0, R.mipmap.bluepurple, "家居维护"));
                        service_list.add(new ServiceBean(0, R.mipmap.purple2, "照明安装与维护"));
                        gv_service.setAdapter(menuAdapter2);
                        break;
                    case 1:

                        service_list.clear();
                        service_list.add(new ServiceBean(1, R.mipmap.blue, "日常服务"));
                        service_list.add(new ServiceBean(1, R.mipmap.brightred, "个人卫生"));
                        service_list.add(new ServiceBean(1, R.mipmap.bluepurple, "清洗织物"));
                        service_list.add(new ServiceBean(1, R.mipmap.purple2, "洗衣服"));
                        service_list.add(new ServiceBean(1, R.mipmap.purple2, "一日三餐"));
                        gv_service.setAdapter(menuAdapter2);
                        break;
                    case 2:
                        service_list.clear();
                        service_list.add(new ServiceBean(2, R.mipmap.blue, "中医保健"));
                        service_list.add(new ServiceBean(2, R.mipmap.brightred, "西医"));
                        service_list.add(new ServiceBean(2, R.mipmap.bluepurple, "身体检测"));
                        service_list.add(new ServiceBean(2, R.mipmap.purple2, "健康咨询"));
                        gv_service.setAdapter(menuAdapter2);
                        break;
                    case 3:
                        service_list.clear();
                        service_list.add(new ServiceBean(3, R.mipmap.blue, "老人心情调查"));
                        service_list.add(new ServiceBean(3, R.mipmap.brightred, "心理咨询热线"));
                        service_list.add(new ServiceBean(3, R.mipmap.bluepurple, "心理咨询"));
                        service_list.add(new ServiceBean(3, R.mipmap.purple2, "心理治疗"));

                        gv_service.setAdapter(menuAdapter2);
                        break;
                    case 4:
                        service_list.clear();
                        service_list.add(new ServiceBean(4, R.mipmap.blue, "法律宣传"));
                        service_list.add(new ServiceBean(4, R.mipmap.brightred, "法律援助"));
                        service_list.add(new ServiceBean(4, R.mipmap.bluepurple, "法律咨询"));

                        gv_service.setAdapter(menuAdapter2);
                        break;
                    case 5:
                        service_list.clear();
                        service_list.add(new ServiceBean(5, R.mipmap.blue, "节日、生日祝贺"));
                        service_list.add(new ServiceBean(5, R.mipmap.brightred, "上门送礼物"));
                        service_list.add(new ServiceBean(5, R.mipmap.bluepurple, "陪读"));
                        service_list.add(new ServiceBean(5, R.mipmap.purple2, "陪同参观"));
                        service_list.add(new ServiceBean(5, R.mipmap.purple2, "娱乐活动"));
                        service_list.add(new ServiceBean(5, R.mipmap.purple2, "老人交友"));
                        gv_service.setAdapter(menuAdapter2);
                        break;
                }

            }
        });
        lv_menu.setSelection(0);
//        lv_menu.getChildAt(0).setBackgroundResource(R.color.colorPrimary);
    }

    private void initserviceGrid() {
        menuAdapter2 = new Adapter<ServiceBean>(mcontentView.getContext(), service_list, R.layout.item_service) {
            @Override
            public void insert(ViewHolder helper, ServiceBean item) {
                ImageView iv = helper.getView(R.id.img_bg);
                TextView tv = helper.getView(R.id.tv_service);
                iv.setBackgroundResource(item.getBg_res());
                tv.setText(item.getTitle());

            }
        };

        gv_service.requestFocus();

        gv_service.setAdapter(menuAdapter2);


        gv_service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, final View view, int position, long id) {

                recyclerViewBridge.setFocusView(view, 1.0f);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gv_service.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                itemclick(position);

            }
        });

    }


    private void itemclick(int position) {

        ServiceBean serviceBean = service_list.get(position);
        switch (serviceBean.getId()) {
            case 0:

                switch (position) {
                    case 0:
                        startActivity(new Intent(getApplication(), HutchdefendsActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getApplication(), HomeapplianceActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getApplication(), HouseholdActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getApplication(), LightingActivity.class));
                        break;

                }

                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }

    }


}


