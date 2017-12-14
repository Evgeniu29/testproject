package com.example.son.testtask;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by son on 08.12.2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {


    private LayoutInflater layoutInflater;
    public static ArrayList<Product> products;
    public String str;
    static AnotherActivity anotherActivity;

    private OnItemClicked onClick;

    //make interface like this
    public interface OnItemClicked {
        void onItemClick(int position);
    }


    public ProductAdapter(AnotherActivity anotherActivity, ArrayList<Product> products) {
        this.products = products;
        this.anotherActivity = anotherActivity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Product product = products.get(position);
        holder.identifier.setText(String.valueOf(product.getId()));
        holder.name.setText(product.getName());
        holder.price.setText(String.valueOf(product.getPrice()));
        holder.price.setEnabled(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
                holder.price.setEnabled(true);
            }

        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView identifier, name;
        public EditText price;
        public Pattern pattern;


        public MyViewHolder(View view) {
            super(view);
            identifier = (TextView) view.findViewById(R.id.identifier);
            name = (TextView) view.findViewById(R.id.name);
            price = (EditText) view.findViewById(R.id.price);

            price.addTextChangedListener(new TextWatcher() {

                                             Double price;

                                             @Override
                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                             }

                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {


                                             }

                                             @Override
                                             public void afterTextChanged(Editable s) {


                                                 try {

                                                     price = Double.parseDouble(s.toString());

                                                 } catch (NumberFormatException ex) {

                                                     // TODO: toast

                                                     Toast toast = Toast.makeText(anotherActivity, "None valid number", Toast.LENGTH_SHORT);
                                                     toast.setGravity(Gravity.CENTER, 0, 0);
                                                     toast.show();


                                                 }

                                                 products.get(getAdapterPosition()).price = price;
                                                 anotherActivity.db.updateProduct(products.get(getAdapterPosition()));
                                             }

                                         }
            );

        }

    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }


}







