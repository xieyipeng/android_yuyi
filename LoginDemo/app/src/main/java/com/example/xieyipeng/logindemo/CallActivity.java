package com.example.xieyipeng.logindemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        EditText getCellPhone = findViewById(R.id.get_cell_phone);
        final String num = getCellPhone.getText().toString().trim();
        getCellPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num.length() == 11) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + num);
                    intent.setData(data);
                    startActivity(intent);
                } else {
                    Toast.makeText(CallActivity.this, "电话号码格式错误!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
