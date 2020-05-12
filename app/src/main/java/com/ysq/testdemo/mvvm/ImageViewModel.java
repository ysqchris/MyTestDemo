package com.ysq.testdemo.mvvm;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2020/5/11
 * <p>
 * 包 名：com.ysq.testdemo.mvvm
 * <p>
 * 类 名：ImageViewModel
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class ImageViewModel extends ViewModel {

    private MutableLiveData<Data<ImageBean.ImagesBean>> mImage;
    private ImageRepertory mRepertory;
    private int idx;

    public ImageViewModel() {
        mImage = new MutableLiveData<>();
        mRepertory = new ImageRepertory();
        idx = 0;
    }

    public MutableLiveData<Data<ImageBean.ImagesBean>> getImage() {
        return mImage;
    }

    public void LoadImage() {
        mRepertory.getImage("js", idx, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ImageBean imageBean) {
                        mImage.setValue(new Data<ImageBean.ImagesBean>(
                                (ImageBean.ImagesBean) imageBean.getImages().get(0), null));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mImage.setValue(new Data<ImageBean.ImagesBean>(
                                null, e.getMessage()
                        ));
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void nextImage() {
        mRepertory.getImage("js", ++idx, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ImageBean imageBean) {
                        mImage.setValue(new Data<ImageBean.ImagesBean>((ImageBean.ImagesBean) imageBean.getImages().get(0),null));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mImage.setValue(new Data<ImageBean.ImagesBean>(
                                null, e.getMessage()
                        ));
                        idx--;
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void previousImage() {
        if (idx <= 0) {
            mImage.setValue(new Data<ImageBean.ImagesBean>(
                    null, "已经是第一个了"
            ));
            return;
        }
        mRepertory.getImage("js", --idx, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ImageBean imageBean) {
                        mImage.setValue(new Data<ImageBean.ImagesBean>(
                                (ImageBean.ImagesBean) imageBean.getImages().get(0), null));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mImage.setValue(new Data<ImageBean.ImagesBean>(
                                null, e.getMessage()
                        ));
                        idx++;
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e("CHRIS", "onCleared: 清除数据");
    }
}
