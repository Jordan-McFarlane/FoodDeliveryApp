package com.example.fooddeliveryapp;

import android.widget.Button;

import java.io.Serializable;

public class FoodItem implements Serializable {


    public String description;
    public int imageResId;
    public double price;

    public FoodItem(String description,int imageResId, double price){
      this.description = description;
        this.imageResId = imageResId;
        this.price = price;

    }



}
