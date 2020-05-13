package com.ysq.testdemo.rxjava;

import android.annotation.SuppressLint;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2020/1/10
 * <p>
 * 包 名：com.ysq.testdemo.rxjava
 * <p>
 * 类 名：RxJavaTest
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class RxJavaTest {

    private static final String TAG = "RXJAVA";


    // 1. 创建被观察者 Observable 对象
    Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
        // create() 是 RxJava 最基本的创造事件序列的方法
        // 此处传入了一个 OnSubscribe 对象参数
        // 当 Observable 被订阅时，OnSubscribe 的 call() 方法会自动被调用，即事件序列就会依照设定依次被触发
        // 即观察者会依次调用对应事件的复写方法从而响应事件
        // 从而实现被观察者调用了观察者的回调方法 & 由被观察者向观察者的事件传递，即观察者模式

        // 2. 在复写的subscribe（）里定义需要发送的事件
        @Override
        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            // 通过 ObservableEmitter类对象产生事件并通知观察者
            // ObservableEmitter类介绍
            // a. 定义：事件发射器
            // b. 作用：定义需要发送的事件 & 向观察者发送事件
            emitter.onNext("被订阅1");
            emitter.onNext("被订阅2");
            emitter.onNext("被订阅3");
            emitter.onComplete();
        }
    });


    /**
     * 其他创建被观察这对象的方法， 被观察创建，发送事件对象
     */
    // 方法1：just(T...)：直接将传入的参数依次发送出来
    Observable observable1 = Observable.just("A", "B", "C");
    // 将会依次调用：
    // onNext("A");
    // onNext("B");
    // onNext("C");
    // onCompleted();

    // 方法2：from(T[]) / from(Iterable<? extends T>) : 将传入的数组 / Iterable 拆分成具体对象后，依次发送出来
    String[] words = {"A", "B", "C"};
    Observable observable2 = Observable.fromArray(words);
    // 将会依次调用：
    // onNext("A");
    // onNext("B");
    // onNext("C");
    // onCompleted();


    /***
     * 二、 创建观察者
     */

    // <--方式1：采用Observer 接口 -->
    // 1. 创建观察者 （Observer ）对象
    Observer<String> observer = new Observer<String>() {

        // 2. 创建对象时通过对应复写对应事件方法 从而 响应对应事件
        // 观察者接收事件前，默认最先调用复写 onSubscribe（）
        @Override
        public void onSubscribe(Disposable d) {
            Log.d(TAG, "开始采用subscribe连接");
        }

        // 当被观察者生产Next事件 & 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onNext(String value) {
            Log.d(TAG, "对Next事件作出响应" + value);
        }

        // 当被观察者生产Error事件& 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "对Error事件作出响应");
        }

        // 当被观察者生产Complete事件& 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onComplete() {
            Log.d(TAG, "对Complete事件作出响应");
        }
    };

//<--方式2：采用Subscriber 抽象类 -->
// 说明：Subscriber类 = RxJava 内置的一个实现了 Observer 的抽象类，对 Observer 接口进行了扩展

    // 1. 创建观察者 （Observer ）对象
    Subscriber<Integer> subscriber = new Subscriber<Integer>() {

        // 2. 创建对象时通过对应复写对应事件方法 从而 响应对应事件
        // 观察者接收事件前，默认最先调用复写 onSubscribe（）
        @Override
        public void onSubscribe(Subscription s) {
            Log.d(TAG, "开始采用subscribe连接");
        }

        // 当被观察者生产Next事件 & 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onNext(Integer value) {
            Log.d(TAG, "对Next事件作出响应" + value);
        }

        // 当被观察者生产Error事件& 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "对Error事件作出响应");
        }

        // 当被观察者生产Complete事件& 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onComplete() {
            Log.d(TAG, "对Complete事件作出响应");
        }
    };


//<--特别注意：2种方法的区别，即Subscriber 抽象类与Observer 接口的区别 -->

// 相同点：二者基本使用方式完全一致（实质上，在RxJava的 subscribe 过程中，Observer总是会先被转换成Subscriber再使用）
// 不同点：Subscriber抽象类对 Observer 接口进行了扩展，新增了两个方法：
    // 1. onStart()：在还未响应事件前调用，用于做一些初始化工作
    // 2. unsubscribe()：用于取消订阅。在该方法被调用后，观察者将不再接收 & 响应事件
    // 调用该方法前，先使用 isUnsubscribed() 判断状态，确定被观察者Observable是否还持有观察者Subscriber的引用，如果引用不能及时释放，就会出现内存泄露


    /**
     * 三、 开始订阅事件
     */

    public void testRxjava() {
        // 1. 观察者（observer）订阅 （subscribe） 被观察者（observable）
        //observable2.subscribe(observer);

        //2. RxJava的链式操作
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            // 1. 创建被观察者 & 生产事件
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onComplete();
//            }
//        }).subscribe(new Observer<Integer>() {
//            // 2. 通过通过订阅（subscribe）连接观察者和被观察者
//            // 3. 创建观察者 & 定义响应事件的行为
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "开始采用subscribe连接");
//            }
//            // 默认最先调用复写的 onSubscribe（）
//            @Override
//            public void onNext(Integer value) {
//                Log.d(TAG, "对Next事件"+ value +"作出响应"  );
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "对Error事件作出响应");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "对Complete事件作出响应");
//            }
//
//        });

        //3. 观察者 改成 Consumer实现，accept方法回调
        Observable.just(0).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object s) throws Exception {
                Log.e(TAG, "对Next事件" + s + "作出响应" + Thread.currentThread().getName());

            }
        });
    }

    /**
     * 快速发送集合
     */
    public void testListRxjava() {

        // 1. 设置一个集合
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        // 2. 通过fromIterable()将集合中的对象 / 数据发送出去
        Observable.fromIterable(list)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });


    }

    /**
     * 延迟创建 defer
     */
    public void defectRxjava() {
        //<-- 1. 第1次对i赋值 ->>
        final Integer i = 10;

        // 2. 通过defer 定义被观察者对象
        // 注：此时被观察者对象还没创建
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(i);
            }
        });

        //<-- 2. 第2次对i赋值 ->>
        //  i = 15;

        //<-- 3. 观察者开始订阅 ->>
        // 注：此时，才会调用defer（）创建被观察者对象（Observable）
        observable.subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "接收到的整数是" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });
    }


    /**
     * 循环创建 timer
     * <p>
     * 快速创建1个被观察者对象（Observable）
     * 发送事件的特点：延迟指定时间后，发送1个数值0（Long类型）
     */

    public void timerRxJava() {
        // 该例子 = 延迟2s后，发送一个long类型数值
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });

        // 注：timer操作符默认运行在一个新线程上
        // 也可自定义线程调度器（第3个参数）：timer(long,TimeUnit,Scheduler)
    }


    /**
     * 循环创建 interval
     * <p>
     * 快速创建1个被观察者对象（Observable）
     * 发送事件的特点：每隔指定时间 就发送 事件
     */

    public void intervalRxJava() {
        // 参数说明：
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        Observable.interval(3,1,TimeUnit.SECONDS)
                // 该例子发送的事件序列特点：延迟3s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });

        // 注：interval默认在computation调度器上执行
        // 也可自定义指定线程调度器（第3个参数）：interval(long,TimeUnit,Scheduler)
    }


    /**
     * 循环创建 interval
     * <p>
     * 快速创建1个被观察者对象（Observable）
     * 发送事件的特点：每隔指定时间 就发送 事件，可指定发送的数据的数量
     */

    public void intervalRangelRxJava() {
        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 参数3 = 第1次事件延迟发送时间；
        // 参数4 = 间隔时间数字；
        // 参数5 = 时间单位
        Observable.intervalRange(3,10,2, 1, TimeUnit.SECONDS)
                // 该例子发送的事件序列特点：
                // 1. 从3开始，一共发送10个事件；
                // 2. 第1次延迟2s发送，之后每隔2秒产生1个数字（从0开始递增1，无限个）
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });
    }



}

