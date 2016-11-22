package com.kunyuanzhihui.ifullcaretv.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2016/10/23.
 */

public class MyHorizontalScrollView extends HorizontalScrollView {

    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getMaxScrollAmount() {
        return super.getMaxScrollAmount();
    }

    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        rect.right = rect.right + rect.width()/2;
        rect.left = rect.left -rect.width()/2;
        return super.computeScrollDeltaToGetChildRectOnScreen(rect);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean isInEditMode() {
        return false;
    }
}
