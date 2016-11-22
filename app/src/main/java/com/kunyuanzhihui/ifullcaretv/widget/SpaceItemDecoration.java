package com.kunyuanzhihui.ifullcaretv.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/12- 14:59.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int top;
    private int bottom;
    private int left;
    private int right;

    public SpaceItemDecoration(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.right = right;
            outRect.top = top;
            outRect.bottom = bottom;
            outRect.left = left;
    }

}
