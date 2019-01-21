package com.example.administrator.industrialmessagesbridge.myView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

public class InsideViewPager extends ViewPager {
    private ViewGroup parent;//NestedScrollView布局
    private ViewGroup refreshParent;//刷新布局（如果没有刷新可以不设置）
    private float mDownPosX = 0;
    private float mDownPosY = 0;



    public InsideViewPager(Context context) {
        super(context);
    }

    public InsideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //在初始化ViewPager和NestedScrollView以后的地方调用此方法，例如viewPager.setNestedpParent(nestedScrollView);
    public void setNestedpParent(ViewGroup parent) {
        this.parent = parent;
    }

    //如果嵌套的有刷新布局也需要调用这个方法，例如viewPager.setRefreshParent(myRefreshView);
    public void setRefreshParent(ViewGroup refreshParent){
        this.refreshParent = refreshParent;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height)height = h;
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            // TODO Auto-generated method stub
            final float x = ev.getX();
            final float y = ev.getY();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDownPosX = x;
                    mDownPosY = y;
                    if (parent != null) {
                        //通知父ViewPager不要干扰本控件的操作
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
               if (refreshParent != null) {//刷新控件禁止滑动
                   refreshParent.setEnabled(false);
               }
                    break;
                case MotionEvent.ACTION_MOVE:
                    final float deltaX = Math.abs(x - mDownPosX);
                    final float deltaY = Math.abs(y - mDownPosY);
                    if (deltaX > deltaY) {
                        if (parent != null) {
                            //通知父ViewPager不要干扰本控件的操作
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                        if (refreshParent != null) {//刷新控件禁止滑动
                       refreshParent.setEnabled(false);
                   }
                        //当拦截触摸事件到达此位置的时候，返回true，
                        //说明将onTouch拦截在此控件，进而执行此控件的onTouchEvent
                        return true;
                    } else {
                   if (parent != null) {
                       parent.requestDisallowInterceptTouchEvent(false);
                   }
                   if (refreshParent != null) {//刷新控件可以滑动
                       refreshParent.setEnabled(true);
                   }
               }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
               if (refreshParent != null) {//刷新控件可以滑动了
                   refreshParent.setEnabled(true);
               }
               break;
                default:
                    break;
            }
            return super.onInterceptTouchEvent(ev);
        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
       if (refreshParent != null) {//刷新控件可以滑动了
                refreshParent.setEnabled(true);
       }
            return super.onTouchEvent(ev);
        }




}
