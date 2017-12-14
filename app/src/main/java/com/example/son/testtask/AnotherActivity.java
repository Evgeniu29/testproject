package com.example.son.testtask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by son on 10.12.2017.
 */

public class AnotherActivity extends Activity implements ProductAdapter.OnItemClicked {

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    SqliteDatabase db;
    ArrayList<Product> products = new ArrayList<Product>();
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        db = new SqliteDatabase(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        products = db.listProducts();
        recyclerView.setItemViewCacheSize(products.size());
        productAdapter = new ProductAdapter(this, products);
        recyclerView.setAdapter(productAdapter);
        productAdapter.setOnClick(this);

    }

    @Override
    public void onItemClick(int position) {
    }


}
