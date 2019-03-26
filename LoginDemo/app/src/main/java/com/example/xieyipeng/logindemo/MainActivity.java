package com.example.xieyipeng.logindemo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private Button location_bt;
    private EditText user_name_et;
    private EditText password_et;
    private EditText re_input;
    private RadioButton male_bt;
    private RadioButton female_bt;
    private EditText location_et;
    private Button sign_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();

    }

    private void initClick() {
        location_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CityChoose.class);
                startActivityForResult(intent, 0);
            }
        });
        sign_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 0) {
            assert data != null;
            Bundle bundle = data.getExtras();
            assert bundle != null;
            String ans = bundle.getString("city");
            location_et.setText(ans);
        }
    }

    private void initView() {
        location_bt = findViewById(R.id.location_bt);
        user_name_et = findViewById(R.id.user_name_et);
        password_et = findViewById(R.id.password);
        re_input = findViewById(R.id.re_input);
        male_bt = findViewById(R.id.male);
        female_bt = findViewById(R.id.female);
        location_et = findViewById(R.id.location_et);
        sign_bt = findViewById(R.id.sign);
    }
}
