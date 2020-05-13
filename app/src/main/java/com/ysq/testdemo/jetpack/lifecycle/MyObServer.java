package com.ysq.testdemo.jetpack.lifecycle;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;


/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2020/3/20
 * <p>
 * 包 名：com.ysq.testdemo.jetpack
 * <p>
 * 类 名：MyObServer
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class MyObServer implements LifecycleObserver {

    private static final String TAG = "MyObserver";

    public MyObServer(Context pContext) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start(){
        Log.e(TAG, "start: MyObServer 观察者 + start");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void  stop(){
        Log.w(TAG, "stop: MyObServer 观察者 + stop");
    }
}
