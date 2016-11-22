package com.kunyuanzhihui.ifullcaretv.activity;

import android.view.View;
import android.view.ViewTreeObserver;

import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.widget.MyHorizontalScrollView;

/**
 * 说明：  挂号
 * 作者： 刘雅俊
 * 时间：2016/11/10- 15:39.
 */
public class RegisterActivity extends BaseActivity{

    @ViewResourceId(R.id.sc_register)
    MyHorizontalScrollView mScrollView;
    @Override
    protected boolean needFocusFrame() {
        return true;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_registered;
    }

    @Override
    protected void init() {

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

    }
}
