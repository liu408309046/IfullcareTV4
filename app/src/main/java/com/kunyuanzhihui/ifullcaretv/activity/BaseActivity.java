package com.kunyuanzhihui.ifullcaretv.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.kunyuanzhihui.ifullcaretv.R;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/10/24.
 */

public abstract class BaseActivity extends AppCompatActivity {

    MainUpView mainUpView;
    View oldFocusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResource());
        addFocusFrame();
        initWidget();
        init();
    }

    /**
     * 使用注解或者使用属性名初始化view，注意混淆编译
     */
    protected void initWidget() {
        try {
            Class<?> c = this.getClass();
            Field[] fs = c.getDeclaredFields();
            for (Field f : fs) {
                if (f.isAnnotationPresent(ViewResourceId.class)) {
                    ViewResourceId id = f.getAnnotation(ViewResourceId.class);
                    if (id.value() > 0) {
                        f.setAccessible(true);
                        f.set(this, findViewById(id.value()));
                        continue;
                    }
                }
                if (View.class.isAssignableFrom(f.getType())) {
                    int id = getResources().getIdentifier(f.getName(), "id", getPackageName());
                    if (id > 0) {
                        f.setAccessible(true);
                        f.set(this, findViewById(id));
                        continue;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private void addFocusFrame() {
        if (needFocusFrame()) {
            mainUpView = new MainUpView(this);
            EffectNoDrawBridge noDrawBridge = new EffectNoDrawBridge();
            mainUpView.setEffectBridge(noDrawBridge);
            noDrawBridge.setTranDurAnimTime(200);
            noDrawBridge.setUpRectResource(R.mipmap.select);
            noDrawBridge.setDrawUpRectPadding(new Rect(19, 19, 19, 19));
        }
    }

    /**
     * 是否需要聚焦框
     *
     * @return
     */
    protected abstract boolean needFocusFrame();

    /**
     * 获取资源id
     *
     * @return
     */
    protected abstract int getContentViewResource();

    /**
     * 初始化
     */
    protected abstract void init();

    public @interface ViewResourceId {
        int value() default 0;
    }
}
