package com.ysq.testdemo.mvvm;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2020/5/11
 * <p>
 * 包 名：com.ysq.testdemo.mvvm
 * <p>
 * 类 名：ImageRepertory
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：网络请求配置
 */
public class ImageRepertory {

    private Retrofit mRetrofit;

    public ImageRepertory() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://cn.bing.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private interface Service {

        @GET("HPImageArchive.aspx")
        Observable<ImageBean> getImage(
                @Query("format") String format,
                @Query("idx") int idx,
                @Query("n") int n
        );

    }

    public Observable<ImageBean> getImage(String format, int idx, int n) {
        return mRetrofit.create(Service.class).getImage(format, idx, n);
    }

}
