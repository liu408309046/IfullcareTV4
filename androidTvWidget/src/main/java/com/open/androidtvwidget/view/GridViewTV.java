package com.open.androidtvwidget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * GridView TV版本.
 *
 * @author hailongqiu 356752238@qq.com
 */
public class GridViewTV extends GridView {

    public GridViewTV(Context context) {
        super(context);
        init(context, null);
    }

    public GridViewTV(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 崩溃了.
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    WidgetTvViewBring mWidgetTvViewBring;
    private OnItemListener mOnItemListener;
    private OnItemClickListener mOnItemClickListener; // item 单击事件.
    private ItemListener mItemListener;
    private View itemView;




    private void init(Context context, AttributeSet attrs) {
        this.setChildrenDrawingOrderEnabled(true);
        mWidgetTvViewBring = new WidgetTvViewBring(this);

        mItemListener = new ItemListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (null != mOnItemListener) {
                    if (null != itemView) {
                        itemView = itemView; // 选中的item.
                        itemView.setSelected(hasFocus);
                        if (hasFocus) {
                            mOnItemListener.onItemSelected(GridViewTV.this, itemView, getPositionForView(itemView));
                        } else {
                            mOnItemListener.onItemPreSelected(GridViewTV.this, itemView, getPositionForView(itemView));
                        }
                    }
                }
            }

            @Override
            public void onClick(View v) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onItemClick(GridViewTV.this, itemView, getPositionForView(itemView));
                }
            }
        };
    }




    @Override
    public void bringChildToFront(View child) {
        mWidgetTvViewBring.bringChildToFront(this, child);
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        // position = getSelectedItemPosition() - getFirstVisiblePosition();
        return mWidgetTvViewBring.getChildDrawingOrder(childCount, i);
    }

    private interface ItemListener extends OnClickListener, OnFocusChangeListener {
    }

    public interface OnItemListener {
        void onItemPreSelected(GridViewTV parent, View itemView, int position);

        void onItemSelected(GridViewTV parent, View itemView, int position);


    }

    public interface OnChildViewHolderSelectedListener {
        public void onChildViewHolderSelected(GridViewTV parent, RecyclerView.ViewHolder vh,
                                              int position);
    }

    public interface OnItemClickListener {
        void onItemClick(GridViewTV parent, View itemView, int position);
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.mOnItemListener = onItemListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
