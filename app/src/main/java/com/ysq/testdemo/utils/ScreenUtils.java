package com.ysq.testdemo.utils;

import android.content.Context;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/11/9
 * <p>
 * 包 名：com.ysq.testdemo
 * <p>
 * 类 名：ScreenUtils
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class ScreenUtils {


    public static int dip2px(Context pContext, int dpValue) {
        final float scale = pContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);

    }
}
