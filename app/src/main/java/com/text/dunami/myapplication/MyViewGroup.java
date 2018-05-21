package com.text.dunami.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dunami on 2018/4/19.
 */

public class MyViewGroup extends ViewGroup {
    List<line> lineList = null;

    public MyViewGroup(Context context) {
        super(context);
    }


    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//
//    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        lineList = new ArrayList<line>();
        Log.d("asd", "被调用");

        int widthSize = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int childcount = getChildCount();
        int linelength = 0;
        line line = null;
        for (int i = 0; i < childcount; i++) {
            View view = getChildAt(i);
            if (view.getVisibility() == GONE) {
                continue;
            }
            int childWidthSpec = MeasureSpec.makeMeasureSpec(widthSize, widthMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : widthMode);
            int childHeightSpec = MeasureSpec.makeMeasureSpec(heightSize, heightMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : widthMode);
            //  measureChildren(widthMeasureSpec, heightMeasureSpec);
            view.measure(childWidthSpec, childHeightSpec);
            if (line == null) {
                line = new line();
            }
            //开始计算一行放几个控件
            linelength += view.getMeasuredWidth();
            if (widthSize >= linelength) {
                Log.d("asd", i + "个" + "    " + linelength);
                line.addchild(view);
            } else {
                lineList.add(line);
                Log.d("asd", lineList.size() + "行" + "    ");
                line = new line();
                line.addchild(view);
                linelength = view.getMeasuredWidth();
                //line.addchild(view);

            }
            if (i == childcount - 1)
                if (line != null) {
                    lineList.add(line);
                }
        }
        Log.d("asd", +lineList.size() + "个");
        Log.d("asd", MeasureSpec.getSize(widthMeasureSpec) + "  " + getChildAt(0).getMeasuredHeight() * lineList.size() + "");
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        // measureChildren(widthMeasureSpec, heightMeasureSpec);
        //Log.d("asd",getChildCount()+"个");
        // Log.d("asd",getChildCount()+"");
//        for (int i=0; i<getChildCount(); i++) {
//            View child = getChildAt(i);
//            Log.d("asd",MeasureSpec.getSize(widthMeasureSpec)+"");
//            Log.d("asd",MeasureSpec.getSize(heightMeasureSpec)+"");
////            int childwidth=View.MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY);
////            int childheigh=View.MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY);
//            int childwidth=View.MeasureSpec.makeMeasureSpec(500, MeasureSpec.EXACTLY);
//            int childheigh=View.MeasureSpec.makeMeasureSpec(500, MeasureSpec.EXACTLY);
//           child.measure(childwidth,childheigh);
//        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < lineList.size(); i++) {
            line line = lineList.get(i);

            line.layoutLine(l, t, i);
        }
        // super.onLayout(boolean changed,int l,int t,int r,int b);
// 参数说明
        // changed 当前View的大小和位置改变了
        // left 左部位置
        // top 顶部位置
        // right 右部位置
        // bottom 底部位置

        // 1. 遍历子View：循环所有子View
        // 注：本例中其实只有一个
//        Log.d("asd", "left" + l + "top" + t + "right" + r + "bottom" + b);
//        Log.d("asd", getChildCount() + "");
//        for (int i = 0; i < getChildCount(); i++) {
//            View child = getChildAt(i);
//
//            // 取出当前子View宽 / 高
//            int width = child.getMeasuredWidth();
//            int height = child.getMeasuredHeight();
//            Log.d("asd", "/////////////////////////");
//
//            Log.d("asd", width + "");
//            Log.d("asd", height + "");
//            // 2. 计算当前子View的四个位置值
//            // 2.1 位置的计算逻辑
//            int mLeft = (r - width) / 2;
//            int mTop = (b - height) / 2;
//            int mRight = mLeft + width;
//            int mBottom = mTop + height;
//
//            // 3. 根据上述4个位置的计算值，设置子View的4个顶点
//            // 即确定了子View在父容器的位置
//            child.layout(mLeft, mTop, mRight, mBottom);
//            Log.d("asd", "mLeft " + mLeft + "mTop " + mTop + "mRight " + mRight + "mBottom " + mBottom);
//        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    private class line {
        List<View> children = new ArrayList<View>();

        public void addchild(View child) {
            children.add(child);
        }

        public void layoutLine(int l, int t, int linecount) {
            int left = 0;
            int top = 0;
            for (int i = 0; i < children.size(); i++) {
                View child = children.get(i);


                int childWidth=child.getMeasuredWidth()+100;
               child.getLayoutParams().width=childWidth+100;
                int widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
                int childHeight=child.getMeasuredHeight();
                int heightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
                child.measure(widthMeasureSpec, heightMeasureSpec);
                child.layout(left, child.getMeasuredHeight() * linecount, left + child.getMeasuredWidth(), child.getMeasuredHeight() * linecount + child.getMeasuredHeight());
                left += child.getMeasuredWidth();
            }
        }
    }
}
