package com.ysq.testdemo.mvvm;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2020/5/11
 * <p>
 * 包 名：com.ysq.testdemo.mvvm
 * <p>
 * 类 名：Data
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class Data<T> {

    private T mData;
    private String mErrorMsg;
    public Data(T data, String errorMsg) {
        mData = data;
        mErrorMsg = errorMsg;
    }

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }

    public String getErrorMsg() {
        return mErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        mErrorMsg = errorMsg;
    }
}
