package com.ysq.testdemo.mvvm;

import androidx.databinding.ObservableField;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2020/5/13
 * <p>
 * 包 名：com.ysq.testdemo.mvvm
 * <p>
 * 类 名：DataBindingObserverBean
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class DataBindingObserverBean {

    public final ObservableField<String> mObservableField = new ObservableField<>();

    public void setObservableField(String observableField) {
        this.mObservableField.set(observableField);
    }

}
