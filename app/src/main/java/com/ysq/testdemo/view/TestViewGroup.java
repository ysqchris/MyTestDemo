package com.ysq.testdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ysq.testdemo.utils.ContactUtils;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/12/10
 * <p>
 * 包 名：com.ysq.testdemo.view
 * <p>
 * 类 名：TestViewGroup
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class TestViewGroup extends LinearLayout {

    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //Log.e(ContactUtils.lOG_TAG, "dispatchTouchEvent: TestViewGroup 分发dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //Log.e(ContactUtils.lOG_TAG, "onInterceptTouchEvent: TestViewGroup 拦截TestViewGroup");
        //return super.onInterceptTouchEvent(ev);
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.e(ContactUtils.lOG_TAG, "onTouchEvent: TestViewGroup onTouchEvent");
        return super.onTouchEvent(event);
    }
}
