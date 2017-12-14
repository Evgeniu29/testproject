package com.example.son.testtask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ArrayList<Product> products = new ArrayList<Product>();
    SqliteDatabase db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button parse = (Button) findViewById(R.id.parse);
        Button start = (Button) findViewById(R.id.start);


        parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostAsync().execute();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AnotherActivity.class);
                startActivity(i);
            }
        });

    }

    private class PostAsync extends AsyncTask<String, Void, Void> {

        XMLHelper helper;


        protected Void doInBackground(String... strings) {
            helper = new XMLHelper();
            helper.get();

            products = helper.getPostsList();
            db = new SqliteDatabase(getApplicationContext());
            db.deleteAllProducts();


            for (int i = 0; i < products.size(); i++) {
                db.addProduct(products.get(i));
            }

            return null;
        }


    }
}