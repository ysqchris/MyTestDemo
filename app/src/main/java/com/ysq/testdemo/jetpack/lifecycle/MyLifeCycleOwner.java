package com.ysq.testdemo.jetpack.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2020/3/20
 * <p>
 * 包 名：com.ysq.testdemo.jetpack
 * <p>
 * 类 名：MyLifeCycleOwner
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class MyLifeCycleOwner implements LifecycleOwner {

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
