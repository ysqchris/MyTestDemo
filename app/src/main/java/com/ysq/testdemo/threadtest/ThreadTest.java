package com.ysq.testdemo.threadtest;

import android.util.Log;

import com.ysq.testdemo.throwabletest.ThrowableTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/10/8
 * <p>
 * 包 名：com.ysq.testdemo.threadtest
 * <p>
 * 类 名：ThreadTest
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class ThreadTest {

    private ThreadLocal<Boolean> mThreadLocal = new ThreadLocal<Boolean>();

    public void threadTest(){

        ExecutorService pool = Executors.newFixedThreadPool(4);
        // pool.

        // 主线程
        mThreadLocal.set(true);
        Log.i("threadLocal", mThreadLocal.get() +" = " + Thread.currentThread().getName());

        // 线程1
        new Thread("Thread1"){
            public void run(){
                mThreadLocal.set(false);
                Log.i("threadLocal", mThreadLocal.get() +" = " + Thread.currentThread().getName());
            }
        }.start();
    }


    public void testThrowable(){
        ThrowableTest testException1 = new ThrowableTest();
        try {
            testException1.testEx();
        } catch (Exception e) {
            e.printStackTrace();
        }
        testException1.testThrowRuntimeException();
        int a = 6;
        int b = 0;
        try { // try监控区域

            if (b == 0) throw new ArithmeticException(); // 通过throw语句抛出异常
            System.out.println("a/b的值是：" + a / b);
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }
    }
}
