package com.ysq.testdemo.jetpack.livedata;

import androidx.lifecycle.LiveData;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2020/3/20
 * <p>
 * 包 名：com.ysq.testdemo.jetpack.livedata
 * <p>
 * 类 名：MutableLiveData
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class MutableLiveData<T> extends LiveData<T> {

    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
    }
}
