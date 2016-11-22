package com.kunyuanzhihui.ifullcaretv.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.kunyuanzhihui.ifullcaretv.MyApplication;
import com.kunyuanzhihui.ifullcaretv.R;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/17- 18:24.
 */

public class StartpageActivity extends BaseActivity {
    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_start_page;
    }

    Handler handler=new Handler();

    @Override
    protected void init() {

        mHandler.sendEmptyMessageDelayed(SWITCH_MAINACTIVITY, 1500);

    }

    private final static int SWITCH_MAINACTIVITY = 1000;
    private final static int SWITCH_GUIDACTIVITY = 1001;

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SWITCH_MAINACTIVITY:
                    new Thread(new Runnable() {
                        public void run() {
                            if (MyApplication.getInstance().isLogin()) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else {
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            }
                        }
                    }).start();
                    break;
                case SWITCH_GUIDACTIVITY:

                    break;
            }
            super.handleMessage(msg);
        }
    };
}
