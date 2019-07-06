package com.example.xieyipeng.servicelifecircle;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button stop;
    private Button bind;
    private Button unBind;
    private static Intent intent;
    private ServiceConnection serviceConection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
    }

    private void initClick() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MineService.class);
                intent.setAction("com.example.xieyipeng.MY_SERVICE");
                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
                startService(intent);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent,serviceConection,Service.BIND_AUTO_CREATE);
            }
        });
        unBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(serviceConection);
            }
        });
    }

    private void initView() {
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        bind = findViewById(R.id.bind);
        unBind = findViewById(R.id.unbind);
        serviceConection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("MINE_SERVICE", "onServiceConnected: " );
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("MINE_SERVICE", "onServiceDisconnected: " );
            }
        };
    }
}
