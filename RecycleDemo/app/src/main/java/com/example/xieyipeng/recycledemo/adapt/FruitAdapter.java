package com.example.xieyipeng.recycledemo.adapt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xieyipeng.recycledemo.JavaBean.Fruit;
import com.example.xieyipeng.recycledemo.R;

import java.util.ArrayList;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitHolder> {

    List<Fruit> fruits = new ArrayList<>();

    public FruitAdapter(List<Fruit> list) {
        fruits = list;
    }

    static class FruitHolder extends RecyclerView.ViewHolder {

        private ImageView fruitImage;
        private TextView fruitName;

        public FruitHolder(@NonNull View itemView) {
            super(itemView);
            fruitImage = itemView.findViewById(R.id.item_fruit_image);
            fruitName = itemView.findViewById(R.id.item_fruit_name);
        }
    }

    @NonNull
    @Override
    public FruitHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fruit, viewGroup, false);
        return new FruitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitHolder fruitHolder, int i) {
        Fruit fruit = fruits.get(i);
        fruitHolder.fruitImage.setImageResource(fruit.getImageID());
        fruitHolder.fruitName.setText(fruit.getName());
    }


    @Override
    public int getItemCount() {
        return fruits.size();
    }
}
