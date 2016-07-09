package com.malin.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

//需要在AndroidManifest.xml中进行注册
public class MyService extends Service {

    private static final String TAG = "KKKKKK";
    private DownLoadBinder mBinder = new DownLoadBinder();

    class DownLoadBinder extends Binder {

        public void startDownLoad() {
            Log.d(TAG, "DownLoadBinder startDownLoad executed");
        }


        public int getProgress() {
            Log.d(TAG, "DownLoadBinder getProgress executed");
            return 0;
        }
    }


    //运行在UI线程中
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "MyService onBind() " + "UIThread:" + isRunOnUIThread());
        return mBinder;
    }

    //在服务创建的时候调用,运行在UI线程中
    @Override
    public void onCreate() {
        super.onCreate();

        Intent intent = new Intent(this, MainActivity.class);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, 0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setAutoCancel(false);
        builder.setTicker("this is ticker text");
        builder.setContentTitle("WhatsApp Notification");
        builder.setContentText("You have a new message");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        Notification notification = builder.build();
        startForeground(1, notification);
        Log.d(TAG, "MyService onCreate() " + "UIThread:" + isRunOnUIThread());
    }


    //在服务每次启动的时候调用,运行在UI线程中
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MyService onStartCommand() " + "UIThread:" + isRunOnUIThread());

        //产生一个ANR
        //adb shell
        //cat /data/anr/traces.txt | more
        //看日志可以定位出现问题的额代码的行数.
//        for (int i=0;i<Integer.MAX_VALUE;i++){
//            Log.d(TAG,new Date().toString());
//        }
        return super.onStartCommand(intent, flags, startId);

    }

    //在服务销毁的时候调用,运行在UI线程中
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MyService onDestroy() " + "UIThread:" + isRunOnUIThread());
    }

    private boolean isRunOnUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
