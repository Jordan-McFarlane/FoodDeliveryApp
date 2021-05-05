package com.example.fooddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ResturantView  extends ConstraintLayout {
    private ImageView image;
    private TextView name;
    public FoodItem[] foodItems;




    public ResturantView(@NonNull Context context, AttributeSet attrs) {
        super(context,attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.resturant, this);

       name = (TextView)findViewById(R.id.name);
       image = (ImageView)findViewById(R.id.resturant_image);







        image.setClickable(true);
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchActivityIntent = new Intent(context, Menu.class);
                switchActivityIntent.putExtra("foodItems", foodItems);




                context.startActivity(switchActivityIntent);
            }
        });





    }
    public void setFromRestaurant(Resturant r){
        image.setImageResource(r.imageID);
        name.setText(r.name);
        foodItems = r.foodItems;
    }



}


