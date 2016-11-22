package com.kunyuanzhihui.ifullcaretv.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/12- 14:36.
 */

public class GrallyRecyclerView extends RecyclerView {
    private Scroller mscroller;
    private int mLastx;
    private int mTargetPos;

    public GrallyRecyclerView(Context context) {
        super(context);
        initcontext(context);
    }


    public GrallyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initcontext(context);
    }

    public GrallyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initcontext(context);
    }

    private void initcontext(Context context) {
        mscroller = new Scroller(context);

    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        //computeScrollOffset返回true表示滚动还在继续，持续时间应该就是startScroll设置的时间
        if(mscroller!=null && mscroller.computeScrollOffset()){

            scrollBy(mLastx - mscroller.getCurrX(), 0);
            mLastx = mscroller.getCurrX();
            postInvalidate();//让系统继续重绘，则会继续重复执行computeScroll
        }
    }

    /**
     * 将指定item平滑移动到整个view的中间位置
     * @param position
     */
    public void smoothToCenter(int position){
        int parentWidth = getWidth();//获取父视图的宽度
        int childCount = getChildCount();//获取当前视图可见子view的总数
        //获取可视范围内的选项的头尾位置
        int firstvisiableposition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        int lastvisiableposition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        int count = ((LinearLayoutManager)getLayoutManager()).getItemCount();//获取item总数

        mTargetPos = Math.max(0, Math.min(count - 1, position));//获取目标item的位置（参考listview中的smoothScrollToPosition方法）

        View targetChild = getChildAt(mTargetPos-firstvisiableposition);//获取目标item在当前可见视图item集合中的位置
        View firstChild = getChildAt(0);//当前可见视图集合中的最左view
        View lastChild = getChildAt(childCount-1);//当前可见视图集合中的最右view

        int childLeftPx = targetChild.getLeft();//子view相对于父view的左边距
        int childRightPx = targetChild.getRight();//子view相对于父view的右边距


        int childWidth = targetChild.getWidth();
        int centerLeft = parentWidth/2-childWidth/2;//计算子view居中后相对于父view的左边距
        int centerRight = parentWidth/2+childWidth/2;//计算子view居中后相对于父view的右边距

        if(childLeftPx>centerLeft){//子view左边距比居中view大（说明子view靠父view的右边，此时需要把子view向左平移
            //平移的起始位置就是子view的左边距，平移的距离就是两者之差
            mLastx = childLeftPx;
            mscroller.startScroll(childLeftPx,0,centerLeft-childLeftPx,0,600);//600为移动时长，可自行设定
            postInvalidate();
        }else if(childRightPx<centerRight){
            mLastx = childRightPx;
            mscroller.startScroll(childRightPx,0,centerRight-childRightPx,0,600);
            postInvalidate();
        }


    }
}
