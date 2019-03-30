package com.example.xieyipeng.logindemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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
                String result = checkInfo();
                if (result == null) {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("user_name", user_name_et.getText().toString().trim());
                    intent.putExtra("password", password_et.getText().toString().trim());
                    intent.putExtra("sex", male_bt.isChecked() ? "男" : "女");
                    intent.putExtra("city", location_et.getText().toString().trim());
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("错误提示：");
                    builder.setMessage(result);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            user_name_et.setText("");
                            password_et.setText("");
                            re_input.setText("");
                            location_et.setText("");
                            male_bt.setChecked(false);
                            female_bt.setChecked(false);
                        }
                    });
                    builder.create().show();
                }
            }

            private String checkInfo() {
                if (user_name_et.getText().toString().trim().equals("")) {
                    return "用户名不能为空！";
                }
                if (password_et.getText().toString().trim().length() < 6 || password_et.getText().toString().trim().length() > 15) {
                    return "密码长度不符合要求";
                }
                if (!password_et.getText().toString().trim().equals(re_input.getText().toString().trim())) {
                    return "两次输入密码不一致";
                }
                if (!male_bt.isChecked() && !female_bt.isChecked()) {
                    return "性别未选择";
                }
                if (location_et.getText().toString().trim().equals("")) {
                    return "地点为空";
                }
                return null;
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
