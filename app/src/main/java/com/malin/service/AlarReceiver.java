package com.malin.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //启动服务
        Intent i = new Intent(context, LongRunningService.class);
        context.startService(i);
    }
}
