package com.example.mobileapps1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapps1.models.Adaptor.CategoryAdaptor;
import com.example.mobileapps1.models.Adaptor.PopularAdaptor;
import com.example.mobileapps1.models.Domain.CategoryDomain;
import com.example.mobileapps1.models.Domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Products extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn2);
        LinearLayout homeBtn=findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Products.this, CartListAcvivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Products.this, Products.class));
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pizza", "cat_1"));
        category.add(new CategoryDomain("Burger", "cat_2"));
        category.add(new CategoryDomain("Hotdog", "cat_3"));
        category.add(new CategoryDomain("Drink", "cat_4"));
        category.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);


    }
    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList=new ArrayList<>();
        foodList.add(new FoodDomain("Pepperoni pizza", "pizza", "slices pepperoni, mozzarelacheese, fresh oregano, ground black pepper, pizza sauce", 9.76));
        foodList.add(new FoodDomain("Cheese Burger", "pop_2", "beef, gouda, cheese, Special sauce, Lettuce, tomato", 8.79));
        foodList.add(new FoodDomain("Vegetable pizza", "pop_3", "olive oil, vegetable oil, pitted kalameta, cherry tomatoes, fresh oregano, basil", 9.80 ));

        adapter2=new PopularAdaptor(foodList);
        recyclerViewPopularList.setAdapter(adapter2);
    }
}