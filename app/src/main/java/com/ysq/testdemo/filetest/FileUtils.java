package com.ysq.testdemo.filetest;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/10/8
 * <p>
 * 包 名：com.ysq.testdemo.filetest
 * <p>
 * 类 名：FileUtils
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：测试提交 jira 哈哈哈  #56 #57 喊喊口号jkjfkd fjd jdlfjadlf 
 * #56#57
 * #56，#57
 */
public class FileUtils {


    public void saveInterFile(View v , Context pContext){
        String FILENAME = "hello_file";
        String string = "hello world!";
        FileOutputStream fos = null;
        Log.e("TAG", "save: ");
        try {
            fos = pContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
        } catch (FileNotFoundException pE) {
            pE.printStackTrace();
        }catch (IOException pE){
            pE.printStackTrace();
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        t.start();
    }


    public void readInterFile(View v , Context pContext){
        String FILENAME = "hello_file";
        FileInputStream fos = null;
        String str2 = "" ;
        Log.e("TAG", "readInterFile: ");
        try {
            fos = pContext.openFileInput(FILENAME);
            fos.available();
            byte[] buffer = new byte[5];//创建byte数组用于读入数据
            int len  = fos.read(buffer);
            str2 = new String(buffer, 0, len , "utf-8");
            fos.available();
            fos.close();
        } catch (FileNotFoundException pE) {
            pE.printStackTrace();
        }catch (IOException pE){
            pE.printStackTrace();
        }
        Log.e("TAG", "str2: = " + str2);
    }


    public void outPrivateFile(View pView , Context pContext){
        File file3 = new File( pContext.getExternalCacheDir().getAbsolutePath(), "getExternalCacheDir.txt");
        try {
            OutputStream outputStream1 = new FileOutputStream(file3);
            outputStream1.write("getExternalCacheDir".getBytes());
            outputStream1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("TAG", "file3=" + file3);

        File file4 = new File(pContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "getExternalFilesDir.txt");
        try {
            OutputStream outputStream1 = new FileOutputStream(file4);
            outputStream1.write("getExternalFilesDir".getBytes());
            outputStream1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("TAG", "file4 = " + file4);
    }


}
