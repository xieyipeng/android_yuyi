package com.example.xieyipeng.handlerdemo;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @SuppressLint("HandlerLeak")
    private Handler myHandler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x11) {
                textView.setText("产生随机数：" + (int) (Math.random() * 100));
            }
        }
    };

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.random_text);
        textView.setText("产生随机数：" + (int) (Math.random() * 100));

//        try {
//            while (true) {
//                Message message = new Message();
//                message.what = 0x11;
//                myHandler.sendMessage(message);
//                Thread.sleep(2000);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Message message = new Message();
                        message.what = 0x11;
                        myHandler.sendMessage(message);
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
