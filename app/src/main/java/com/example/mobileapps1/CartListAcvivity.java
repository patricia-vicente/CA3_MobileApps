package com.example.mobileapps1;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapps1.Helper.ManagementCart;

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
    }

    private void initView() {
    recyclerViewList=findViewById(R.id.recyclerView);
    totalFeeTxt=findViewById(R.id.totalFeeTxt);
    taxTxt=findViewById(R.id.taxTxt);
    deliveryTxt=findViewById(R.id.deliveryTxt);
    totalTxt=findViewById(R.id.totalTxt);
    emptyTxt=findViewById(R.id.emptyTxt);
    scrollView=findViewById(R.id.scrollView3);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=
    }
}