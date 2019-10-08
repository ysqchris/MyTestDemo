package com.ysq.testdemo.filetest;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/10/8
 * <p>
 * 包 名：com.ysq.testdemo.filetest
 * <p>
 * 类 名：FilePaths
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class FilePaths {

    public void testFilePath(Context pContext){
        /**
         * 内部存储 getFilesDir  /data/data/包名/files
         * 用户不可见 ，外部不可访问
         */
        File interFilePath  =  pContext.getFilesDir();
        Log.e("TAG", "内部私有存储: " + interFilePath.getAbsolutePath());
        // openFileOutput 可以使用该方法直接存 ，该方法默认打开该文件路径
        // openFileInput  直接读取文件

        /**
         * 内部缓存目录 /data/data/包名/cache
         * 用户不可见，外部不可访问
         */
        File interCachePath =  pContext.getCacheDir();
        Log.e("TAG", "内部私有缓存: " + interCachePath.getAbsolutePath());


        /**
         * 外部存储上的私有存储目录  /storage/emulated/0/Android/data/包名/files/Pictures
         * 参数可以传
         * Environment.DIRECTORY_MUSIC 音乐
         * Environment.DIRECTORY_MOVIES 视频
         * Environment.DIRECTORY_PICTURES 图片等
         * 也可以直接传 null
         */
        File externalFilePath =  pContext.getExternalFilesDir(null);
        Log.e("TAG", "外部私有: " + externalFilePath.getAbsolutePath());

        /**
         * 外部私有缓存目录 /storage/emulated/0/Android/data/包名/cache
         */
        File externalCachePath =  pContext.getExternalCacheDir();
        Log.e("TAG", "externalCachePath: " + externalCachePath.getAbsolutePath());

        /**
         * 外部公共目录 /storage/emulated/0/Pictures
         * Environment.DIRECTORY_MUSIC 音乐
         * Environment.DIRECTORY_MOVIES 视频
         * Environment.DIRECTORY_PICTURES 图片等
         * 也可以直接传 null
         */
        File externalPublicPath =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Log.e("TAG", "externalPublicPath: " + externalPublicPath.getAbsolutePath());

        /**
         * 外部公共目录 根目录 /storage/emulated/0
         */
        File externalRootPath =  Environment.getExternalStorageDirectory();
        Log.e("TAG", "externalRootPath: " + externalRootPath.getAbsolutePath());
    }


}
