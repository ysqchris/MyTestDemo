package com.ysq.testdemo.threadtest

/**
 * 项目名：MyTestDemo
 *
 * 时 间：2019/11/13
 *
 * 包 名：com.ysq.testdemo.threadtest
 *
 * 类 名：MockThread
 *
 * 作 者：Yusq
 *
 * 简 述：
 */
class MockThread (target: Runnable, name: String) : Thread(target, name) {

    //用于保存绑定到线程上的数据
    var threadLocals: MockThreadLocal.ThreadLocalMap? = null

}