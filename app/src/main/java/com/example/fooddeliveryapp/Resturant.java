package com.example.fooddeliveryapp;

public class Resturant {

    public String name;
    public FoodItem[] foodItems;
    public int imageID;


    public Resturant(String name,FoodItem[] foodItems, int imageID){
        this.name = name;
        this.foodItems = foodItems;
        this.imageID = imageID;
    }

    public String getName(){
        return name;
    }

    public FoodItem[] getFoodItem(){
        return foodItems;
    }

    public int getImageId(){
        return imageID;
    }


}
