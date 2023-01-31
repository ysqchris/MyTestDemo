package com.ysq.testdemo.mvvm;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2020/5/11
 * <p>
 * 包 名：com.ysq.testdemo.mvvm
 * <p>
 * 类 名：BindingAdapter
 * <p>
 * 作 者：Yusq 
 * <p>
 * 简 述：
 */
public class BindingAdapter {
    // 测试分支合并
    // 增加一个测试提交  再提交一次 提交更新变更 尽快尽快京东方
    @androidx.databinding.BindingAdapter("url")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}
