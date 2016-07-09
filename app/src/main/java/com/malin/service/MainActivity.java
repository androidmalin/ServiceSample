package com.malin.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonStart;
    private Button mButtonStop;
    private Button mButtonBindService;
    private Button mButtonUnBindService;
    private Button mButtonBindIntentService;
    private Button mButtonLongRunningService;
    private static final String TAG = "KKKKKK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mButtonStart = (Button) findViewById(R.id.btn_start);
        mButtonStop = (Button) findViewById(R.id.btn_stop);
        mButtonBindService = (Button) findViewById(R.id.btn_bind_service);
        mButtonUnBindService = (Button) findViewById(R.id.btn_unbind_service);
        mButtonBindIntentService = (Button) findViewById(R.id.btn_bind_intent_service);
        mButtonLongRunningService = (Button) findViewById(R.id.btn_start_long_run_service);
    }

    private void initListener() {
        mButtonStart.setOnClickListener(this);
        mButtonStop.setOnClickListener(this);
        mButtonBindService.setOnClickListener(this);
        mButtonUnBindService.setOnClickListener(this);
        mButtonBindIntentService.setOnClickListener(this);
        mButtonLongRunningService.setOnClickListener(this);
    }


    private MyService.DownLoadBinder downLoadBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            Log.d(TAG, "ServiceConnection onServiceConnected()");
            downLoadBinder = (MyService.DownLoadBinder) iBinder;
            downLoadBinder.startDownLoad();
            downLoadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "ServiceConnection onServiceDisconnected()");
        }
    };


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start: {
                //启动服务
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            }

            case R.id.btn_stop: {
                //停止服务
                Intent intent = new Intent(this, MyService.class);
                stopService(intent);
                break;
            }
            //Bind Service
            case R.id.btn_bind_service: {
                Intent binderIntent = new Intent(this, MyService.class);
                //绑定服务
                bindService(binderIntent, serviceConnection, BIND_AUTO_CREATE);
                break;
            }

            //解绑服务
            case R.id.btn_unbind_service: {
                unbindService(serviceConnection);
                break;
            }


            case R.id.btn_bind_intent_service: {
                Intent intent = new Intent(this, MyIntentService.class);
                startService(intent);
                break;
            }

            case R.id.btn_start_long_run_service: {
                Intent intent = new Intent(this, LongRunningService.class);
                startService(intent);
                break;
            }
            default: {
                break;
            }
        }
    }
}
