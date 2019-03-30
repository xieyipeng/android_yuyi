package com.example.xieyipeng.logindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView nameShow;
    private TextView passShow;
    private TextView sexShow;
    private TextView locationShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
        initContain();
    }

    private void initContain() {
        Intent intent = getIntent();
        nameShow.setText(intent.getStringExtra("user_name"));
        passShow.setText(intent.getStringExtra("password"));
        sexShow.setText(intent.getStringExtra("sex"));
        locationShow.setText(intent.getStringExtra("city"));
    }

    private void initView() {
        nameShow = findViewById(R.id.resultName);
        passShow = findViewById(R.id.resultPsd);
        sexShow = findViewById(R.id.resultGender);
        locationShow = findViewById(R.id.resultCity);
    }
}
