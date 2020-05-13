package com.ysq.testdemo.notify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat;

import com.ysq.testdemo.R;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/11/11
 * <p>
 * 包 名：com.ysq.testdemo.notify
 * <p>
 * 类 名：CreateNotifyTestUtils
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class CreateNotifyTestUtils {

    public  static  String createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "channelId";
            CharSequence channelName = "channelName";
            String channelDescription ="channelDescription";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, channelImportance);
            // 设置描述 最长30字符
            notificationChannel.setDescription(channelDescription);
            // 该渠道的通知是否使用震动
            notificationChannel.enableVibration(true);
            // 设置显示模式
            notificationChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);

            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

            return channelId;
        } else {
            return null;
        }
    }


    public static void  createNotifyCation(Context pContext) {
        NotificationManager manager = (NotificationManager) pContext.getSystemService(pContext.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(pContext, createNotificationChannel(pContext));
        Notification notification = builder
                .setContentTitle("这是通知标题")
                .setContentText("这是通知内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(pContext.getResources(), R.mipmap.ic_launcher))
                .build();
        //manager.notify(1, notification);
    }
}
