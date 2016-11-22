package com.kunyuanzhihui.ifullcaretv.activity;

import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.kunyuanzhihui.ifullcaretv.MyApplication;
import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.bean.GoodTypeBean;
import com.kunyuanzhihui.ifullcaretv.net.EHResponse;
import com.kunyuanzhihui.ifullcaretv.net.EHapiManager;
import com.kunyuanzhihui.ifullcaretv.presenter.LeftMenuPresenter;
import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.MainUpView;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/18- 17:40.
 */

public class HealthShopActivity extends BaseActivity {

    private RecyclerViewTV left_menu_rv, rv_goods;
    private MainUpView mainUpView;
    private RecyclerViewBridge mRecyclerViewBridge;
    private View oldView;
    private List<GoodTypeBean.DataBean> types=new ArrayList<>();

    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_healthshop;
    }

    @Override
    protected void init() {
        getGoodsType();

        mainUpView.setEffectBridge(new RecyclerViewBridge());
        // 注意这里，需要使用 RecyclerViewBridge 的移动边框 Bridge.
        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.mipmap.select);
        float density = getResources().getDisplayMetrics().density;
        mRecyclerViewBridge.setDrawUpRectPadding(new Rect(19, 19, 19, 19));
        initLeftMenu();
    }

    private void  getGoodsType(){
        EHapiManager.getHealthshot_goodsType(MyApplication.getInstance().getTv_token(), new EHResponse() {
            @Override
            public void onSuccess(String result) {
                GoodTypeBean goodTypeBean=new Gson().fromJson(result,GoodTypeBean.class);
                int result_code=goodTypeBean.getResult_code();
                String message=goodTypeBean.getMessage();
                List<GoodTypeBean.DataBean> dataBean=goodTypeBean.getData();
                types=dataBean;
                initLeftMenu();
            }

            @Override
            public void onError(String request) {

            }
        });

    }


    private void initLeftMenu() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        left_menu_rv.setLayoutManager(layoutManager);
        left_menu_rv.setFocusable(false);
        GeneralAdapter generalAdapter = new GeneralAdapter(new LeftMenuPresenter(getApplicationContext(),types));
        left_menu_rv.setAdapter(generalAdapter);
        left_menu_rv.setOnItemListener(new RecyclerViewTV.OnItemListener() {
            @Override
            public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
                // 传入 itemView也可以, 自己保存的 oldView也可以.
                mRecyclerViewBridge.setUnFocusView(itemView);
            }

            @Override
            public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.0f);
                oldView = itemView;
            }

            /**
             * 这里是调整开头和结尾的移动边框.
             */
            @Override
            public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.0f);
                oldView = itemView;
            }
        });
        left_menu_rv.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                // 测试.
                mRecyclerViewBridge.setFocusView(itemView, oldView, 1.0f);
                oldView = itemView;
                //
//                onViewItemClick(itemView, position);
            }
        });
    }
}
