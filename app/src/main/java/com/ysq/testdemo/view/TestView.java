package com.ysq.testdemo.view;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.ysq.testdemo.utils.ContactUtils;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/12/10
 * <p>
 * 包 名：com.ysq.testdemo.view
 * <p>
 * 类 名：TestView
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class TestView extends AppCompatTextView {

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
       // Log.i(ContactUtils.lOG_TAG, "dispatchTouchEvent: 自定义TestView 分发dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.i(ContactUtils.lOG_TAG, "onTouchEvent: 自定义TestView onTouchEvent");
        return super.onTouchEvent(event);
    }

}
