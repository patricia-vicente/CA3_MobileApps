package com.example.mobileapps1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapps1.Helper.ManagementCart;
import com.example.mobileapps1.models.Adaptor.CartListAdapter;
import com.example.mobileapps1.models.Interface.ChangeNumberItemsListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListAcvivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list_acvivity);


        managementCart=new ManagementCart(this);

        initView();
        initList();
        CalculateCart();
    }

    private void CalculateCart() {
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn2);
        LinearLayout homeBtn=findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListAcvivity.this, CartListAcvivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListAcvivity.this, Products.class));
            }
        });
    }

    private void initView() {
    recyclerViewList=findViewById(R.id.recyclerView);
    totalFeeTxt=findViewById(R.id.totalFeeTxt);
    taxTxt=findViewById(R.id.taxTxt);
    deliveryTxt=findViewById(R.id.deliveryTxt);
    totalTxt=findViewById(R.id.totalTxt);
    emptyTxt=findViewById(R.id.emptyTxt);
    scrollView=findViewById(R.id.scrollView3);
    recyclerViewList=findViewById(R.id.cartView);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void calculateCart() {
        double percentTax=0.02;
        double delivery=10;

        tax=Math.round((managementCart.getTotalFee()*percentTax)*100)/100;
        double total=Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal=Math.round(managementCart.getTotalFee()*100)/100;

        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);
    }
}