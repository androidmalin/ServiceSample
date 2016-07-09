package com.malin.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

/**
 * 后台执行定时任务的服务
 */
public class LongRunningService extends Service {
    public static final String TAG = "LongRunningService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "" + new Date().toString());
            }
        }).start();


        //AlarmManager.set(,,);
        //第一个参数:ELAPSED_REALTIME_WAKEUP:表示2定时任务的触发时间从系统开机算起.
        //第二个参数:定时任务触发的时间,以毫秒为单位.
        //第三个参数:PendingIntent.getBroadcast()获取一个能够执行广播的PendingIntent.
        //这样当定时任务被触发的时候,广播接收器的onReceiver()方法就可以得到执行


        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int delayTime = 1 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + delayTime;
        Intent intent1 = new Intent(this, AlarReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent1, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);

        return super.onStartCommand(intent, flags, startId);
    }
}
