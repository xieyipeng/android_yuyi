package com.example.xieyipeng.recycledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.xieyipeng.recycledemo.JavaBean.Fruit;
import com.example.xieyipeng.recycledemo.adapt.FruitAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FruitAdapter fruitAdapter;
    private List<Fruit> fruits = new ArrayList<>();
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruit();
        manager = new LinearLayoutManager(MainActivity.this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView = findViewById(R.id.recycler);
        fruitAdapter = new FruitAdapter(fruits);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(fruitAdapter);
    }

    private void initFruit() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("apple", R.drawable.apple_pic);
            fruits.add(apple);
            Fruit banana = new Fruit("banana", R.drawable.banana_pic);
            fruits.add(banana);
            Fruit cherry = new Fruit("cherry", R.drawable.cherry_pic);
            fruits.add(cherry);
            Fruit grape = new Fruit("grape", R.drawable.grape_pic);
            fruits.add(grape);
            Fruit mango = new Fruit("mango", R.drawable.mango_pic);
            fruits.add(mango);
            Fruit orange = new Fruit("orange", R.drawable.orange_pic);
            fruits.add(orange);
            Fruit pear = new Fruit("pear", R.drawable.pear_pic);
            fruits.add(pear);
            Fruit pineapple = new Fruit("pineapple", R.drawable.pineapple_pic);
            fruits.add(pineapple);
            Fruit strawberry = new Fruit("strawberry", R.drawable.strawberry_pic);
            fruits.add(strawberry);
            Fruit watermalon = new Fruit("watermalon", R.drawable.watermelon_pic);
            fruits.add(watermalon);
        }
    }

    private String getRandonLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}
