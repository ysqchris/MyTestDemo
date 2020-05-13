package com.ysq.testdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.ysq.testdemo.filetest.FilePaths;
import com.ysq.testdemo.view.TextSetImage;


public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "low";
    private static final String TAG = "CHRIS";
    private WindowManager mWindowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TextSetImage.addTagToTextView(this, (TextView) findViewById(R.id.tv3), getString(R.string.span_text) , " ");
        getWindow();
        //new RxJavaTest().testRxjava();

       // new FilePaths().testFilePath(this);

        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , MvvmActivity.class));
                //getFile();
            }
        });
        //new MainViewModel().testKotilin();



    }

    private void getFile() {
        //通过系统的文件浏览器选择一个文件
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        //筛选，只显示可以“打开”的结果，如文件(而不是联系人或时区列表)
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        //过滤只显示图像类型文件
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
        Uri uri = null;
        if (data != null) {
            // 获取选择文件Uri
            uri = data.getData();
            // 获取图片信息
            Cursor cursor = this.getContentResolver()
                    .query(uri, IMAGE_PROJECTION, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                String displayName = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                String size = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                Log.i(TAG, "Uri: " + uri.toString());
                Log.i(TAG, "Name: " + displayName);
                Log.i(TAG, "Size: " + size);
            }
            cursor.close();
        }

    }

    private final String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media._ID };
}
