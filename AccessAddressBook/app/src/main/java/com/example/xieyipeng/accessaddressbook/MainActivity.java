package com.example.xieyipeng.accessaddressbook;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText nameEditText;
    private EditText numberEditText;
    private Button addButton;
    private Button searchButton;
    private LinearLayout titleLinearLayout;
    private ListView addressBookListView;
    private List<Map<String, String>> data = new ArrayList<>();
    private ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolver = getContentResolver();
        initView();
        initClicks();

    }

    private void initClicks() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    //TODO: 动态获取写权限
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_CONTACTS}, 1);
                } else {
                    //TODO: 添加电话号码
                    addAddressBook();
                }
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    //TODO: 动态获取读取权限
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 2);
                } else {
                    //TODO: 查询电话号码
                    data = searchAddressBook();
                    //TODO: 设置电话号码到 ListView
                    assert data != null;
                    if (data.size() != 0) {
                        SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, data, R.layout.item_address_book_list,
                                new String[]{"id", "name", "number"},
                                new int[]{R.id.item_address_book_id_text_view, R.id.item_address_book_name_text_view, R.id.item_address_book_number_text_view});
                        addressBookListView.setAdapter(simpleAdapter);
                    } else {
                        Toast.makeText(MainActivity.this, "通讯录为空!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        titleLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleLinearLayout.getVisibility() != View.VISIBLE) {
                    titleLinearLayout.setVisibility(View.VISIBLE);
                } else {
                    titleLinearLayout.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private List<Map<String, String>> searchAddressBook() {
        List<Map<String, String>> res = new ArrayList<>();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        assert cursor != null;
        while (cursor.moveToNext()) {
            Map<String, String> person = new HashMap<>();
            String person_id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String person_name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Cursor num = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + person_id, null, null);
            if (num.moveToNext()) {
                String person_number = num.getString(num.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                person.put("number", person_number);
            }
            num.close();
            person.put("id", person_id);
            person.put("name", person_name);
            res.add(person);
        }
        cursor.close();
        return res;
    }


    private void addAddressBook() {
        String name = nameEditText.getText().toString().trim();
        String number = numberEditText.getText().toString().trim();
        ContentValues values = new ContentValues();
        Uri uri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long contactID = ContentUris.parseId(uri);
        values.clear();
        //压入 name
        values.put(ContactsContract.Data.RAW_CONTACT_ID, contactID);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
        resolver.insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();
        //压入 number
        values.put(ContactsContract.Data.RAW_CONTACT_ID, contactID);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, number);
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        resolver.insert(ContactsContract.Data.CONTENT_URI, values);
        Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        nameEditText = findViewById(R.id.name_edit_text);
        numberEditText = findViewById(R.id.number_edit_text);
        addButton = findViewById(R.id.add_button);
        searchButton = findViewById(R.id.search_button);
        titleLinearLayout = findViewById(R.id.title_linear_layout);
        addressBookListView = findViewById(R.id.address_book_list_view);
    }
}
