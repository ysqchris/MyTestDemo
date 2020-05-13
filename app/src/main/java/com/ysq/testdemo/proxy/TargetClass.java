package com.ysq.testdemo.proxy;

import android.util.Log;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/12/9
 * <p>
 * 包 名：com.ysq.testdemo.proxy
 * <p>
 * 类 名：TargetClass
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class TargetClass implements  ProxyInterface{

    @Override
    public void testProxy() {
        Log.e("CHRIS", "testProxy: 目标类方法执行");
    }
}
