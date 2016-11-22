package com.kunyuanzhihui.ifullcaretv.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.kunyuanzhihui.ifullcaretv.MyApplication;
import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.adapter.CMGalleryAdapter;
import com.kunyuanzhihui.ifullcaretv.bean.GalleryBean;
import com.kunyuanzhihui.ifullcaretv.bean.QrcodeBean;
import com.kunyuanzhihui.ifullcaretv.bean.Userbean;
import com.kunyuanzhihui.ifullcaretv.net.EHResponse;
import com.kunyuanzhihui.ifullcaretv.net.EHapiManager;
import com.kunyuanzhihui.ifullcaretv.widget.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/17- 11:00.
 */
public class LoginActivity extends BaseActivity implements CMGalleryAdapter.OnItemSelectListener, CMGalleryAdapter.OnItemClickListener {

    private RelativeLayout rel_login;
    private RecyclerView gallery_add;
    private List<GalleryBean> user_list;
    private ImageView iv_qrcode;
    private String tv_token;
    private boolean is_scanning = false;
    private Handler handler = new Handler();


    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.page_add_character;
    }

    @Override
    protected void init() {
        getQrcode();
        iv_qrcode.setVisibility(View.VISIBLE);
        rel_login.setBackgroundResource(R.mipmap.bg);
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
        setData();
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gallery_add.setLayoutManager(manager);
        gallery_add.addItemDecoration(new SpaceItemDecoration(0, 0, 0, 30));
        gallery_add.setFocusable(false);


        CMGalleryAdapter adapter = new CMGalleryAdapter(getApplicationContext(), user_list);
        gallery_add.setAdapter(adapter);
        adapter.setOnItemSelectListener(this);
        adapter.setOnItemClickListener(this);

    }


    private final Runnable task = new Runnable() {

        public void run() {

            if (!is_scanning) {
                handler.postDelayed(this, 1000);
                monitor_qrcode(tv_token);
            }

        }
    };

    private void setData() {
        user_list = new ArrayList<>();
        user_list.add(new GalleryBean(R.mipmap.newcharacter, "已有账号登入"));
        user_list.add(new GalleryBean(R.mipmap.newcharacter, "添加访客"));
    }

    @Override
    public void onItemSelect(View view, int position) {

    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                MyApplication.getInstance().setIsLogin(true);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;
            case 1:
                startActivity(new Intent(getApplicationContext(), AddCharacterActivity.class));
                break;

        }
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    private void getQrcode() {

        EHapiManager.get_qecode(new EHResponse() {

            @Override
            public void onSuccess(String result) {
                Log.e("----二维码------", result);
                QrcodeBean qrcodeBean = new Gson().fromJson(result, QrcodeBean.class);
                int result_code = qrcodeBean.getResult_code();
                String message = qrcodeBean.getMessage();
                QrcodeBean.DataBean data = qrcodeBean.getData();

                if (result_code == 0) {
                    if (data != null && !data.equals("")) {
                        Bitmap qrBitmap = generateBitmap(data.getQcode(), 200, 200);
                        iv_qrcode.setImageBitmap(qrBitmap);
                        tv_token = data.getToken();

                    }
                }
                if (tv_token != null) {
                    MyApplication.getInstance().setTv_token(tv_token);
                    handler.postDelayed(task, 1000);

                }
            }

            @Override
            public void onError(String request) {

            }
        });

    }

    public void monitor_qrcode(String tv_token) {

        EHapiManager.monitoring_qecode(tv_token, new EHResponse() {
            @Override
            public void onSuccess(String result) {
                Log.e("---监控二维码-----", result);
                Userbean qrcodeBean = new Gson().fromJson(result, Userbean.class);
                int result_code = qrcodeBean.getResult_code();
                String message = qrcodeBean.getMessage();
                Userbean.DataBean userbean = qrcodeBean.getData();
                if (result_code == 0) {

                    MyApplication.getInstance().setCur_User(userbean);
                    is_scanning = true;
                    MyApplication.getInstance().setIsLogin(true);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }


            }

            @Override
            public void onError(String request) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            is_scanning = true;
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    private Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
