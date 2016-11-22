package com.kunyuanzhihui.ifullcaretv.fragment;



import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kunyuanzhihui.ifullcaretv.R;
import com.kunyuanzhihui.ifullcaretv.activity.BaseActivity;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;

import java.lang.reflect.Field;


/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/9- 13:38.
 */

public abstract class BaseFragment extends Fragment {
    MainUpView mainUpView;
    View oldFocusView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(getContentViewResource(),null);
        addFocusFrame();
        initWidget(view);
        init();
        return view;

    }
    /**
     * 使用注解或者使用属性名初始化view，注意混淆编译
     */
    protected void initWidget(View view) {
        try {
            Class<?> c = this.getClass();
            Field[] fs = c.getDeclaredFields();
            for (Field f : fs) {
                if (f.isAnnotationPresent(ViewResourceId.class)) {
                    BaseFragment.ViewResourceId id = f.getAnnotation(ViewResourceId.class);
                    if (id.value() > 0) {
                        f.setAccessible(true);
                        f.set(this, view.findViewById(id.value()));
                        continue;
                    }
                }
                if (View.class.isAssignableFrom(f.getType())) {
                    int id = getResources().getIdentifier(f.getName(), "id", getActivity().getPackageName());
                    if (id > 0) {
                        f.setAccessible(true);
                        f.set(this, view.findViewById(id));
                        continue;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private void addFocusFrame() {
        if (needFocusFrame()) {
            mainUpView = new MainUpView(getActivity().getApplicationContext());
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
