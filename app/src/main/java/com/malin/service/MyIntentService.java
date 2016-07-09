package com.malin.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;

/**
 * 异步的会自动停止的服务.
 */
public class MyIntentService extends IntentService {
    final static String TAG = "PPPPPPP";

    public MyIntentService() {
        super("MyIntentService");
        Log.i(TAG, "MyIntentService is constructed");
    }

    @Override
    protected void onHandleIntent(Intent arg0) {
        Log.i(TAG, "MyIntentService onHandleIntent() " + "isUIThread:" + isUIThread());
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "MyIntentService 睡眠结束");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "MyIntentService is destroy " + "isUIThread:" + isUIThread());
    }


    private boolean isUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
