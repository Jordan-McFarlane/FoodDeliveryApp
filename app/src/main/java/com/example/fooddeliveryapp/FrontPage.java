package com.example.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FrontPage  extends AppCompatActivity {
    private Resturant[] resturants;
    private ResturantView[] resturantViewArray;
    private FoodItem[] pizzas;
    private FoodItem[] burgers;
    private FoodItem[] breakfast;
    private FoodItem[] sushi;
    private ImageView image;
    private Toolbar toolbar;
    private BottomNavigationView navView;


    private EditText search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);

       search = (EditText) findViewById(R.id.search);
       image = (ImageView) findViewById(R.id.resturant_image);
       navView = (BottomNavigationView) findViewById(R.id.design_nav_view);


       navView.setSelectedItemId(R.id.homeIcon);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.homeIcon:
                        startActivity(new Intent(getApplicationContext(),Menu.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });




        pizzas = new FoodItem[]{
                new FoodItem("Chicago",R.drawable.chicago_pizza,12.00),
                new FoodItem("Cheese", R.drawable.cheese_pizza,6.00),
                new FoodItem ("Veggie", R.drawable.veggie_pizza,12.00),
               new FoodItem ("Taco Pizza", R.drawable.taco_pizza,14.00)
        };

        burgers = new FoodItem[]{
                new FoodItem(" Beef Burger",R.drawable.burger_catagory,12.00),
                new FoodItem("Hamburger", R.drawable.burger_catagory,6.00),
                new FoodItem ("Hawaiian Burger", R.drawable.burger_catagory,12.00),
                new FoodItem ("VeggieTaco Pizza", R.drawable.burger_catagory,14.00)
        };

        breakfast = new FoodItem[]{
                new FoodItem(" Pancakes",R.drawable.breakfast_catagory,12.00),
                new FoodItem("Omlet", R.drawable.breakfast_catagory,6.00),
                new FoodItem ("Bacon", R.drawable.breakfast_catagory,12.00),
                new FoodItem ("Home Fries", R.drawable.breakfast_catagory,14.00)
        };
        sushi = new FoodItem[]{
                new FoodItem("Salmon Nigiri",R.drawable.sushi_catagory,12.00),
                new FoodItem("California Rolls", R.drawable.breakfast_catagory,6.00),
                new FoodItem ("Ebi", R.drawable.breakfast_catagory,12.00),
                new FoodItem ("Maguro", R.drawable.breakfast_catagory,14.00)
        };


        resturants = new Resturant[]{
                new Resturant("Pizzas",pizzas ,R.drawable.pizza_catagory),
                new Resturant("Burgers",burgers, R.drawable.burger_catagory),
                new Resturant ("Breakfast", breakfast, R.drawable.breakfast_catagory),
                new Resturant ("Sushi",sushi, R.drawable.sushi_catagory)

        };

        resturantViewArray = new ResturantView[] {

                (ResturantView) findViewById(R.id.ResturantView1),
                (ResturantView)findViewById(R.id.ResturantView2),
                (ResturantView)findViewById(R.id.ResturantView3),
                (ResturantView)findViewById(R.id.ResturantView4)

        };
        for(int i =0 ; i < resturantViewArray.length; i++)
        {
            resturantViewArray[i].setFromRestaurant(resturants[i]);

        }



    }













}
