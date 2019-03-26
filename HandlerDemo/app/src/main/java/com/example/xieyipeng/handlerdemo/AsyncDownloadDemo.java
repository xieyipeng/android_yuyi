package com.example.xieyipeng.handlerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncDownloadDemo extends AppCompatActivity {

    Button loadButton;
    TextView textView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ascentask_download_demo);

        loadButton=findViewById(R.id.start_load_button);
        textView=findViewById(R.id.finish_text_view);
        progressBar=findViewById(R.id.processBar);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadTask downloadTask=new DownloadTask(textView,progressBar);
                downloadTask.execute(100);
            }
        });

    }
}
