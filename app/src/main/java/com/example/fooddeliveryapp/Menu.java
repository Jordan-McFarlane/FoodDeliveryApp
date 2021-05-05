package com.example.fooddeliveryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
private Button viewCart;
private FoodItem[] foodArray;
private FoodItemView[] foodItemViewArray;
private LinearLayout foodListLayout;
private Resturant[] resturant;
private int[] counts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "database-name").allowMainThreadQueries().build();



        setContentView(R.layout.menu_preview);
        foodListLayout = (LinearLayout)findViewById(R.id.foodItemList);
        viewCart = (Button) findViewById(R.id.viewCart);

        if(savedInstanceState != null)
        {
            foodArray =(FoodItem[]) savedInstanceState.getSerializable("foodItems");
            counts  = (int[]) savedInstanceState.getSerializable("counts");
        }
        else{
              foodArray = (FoodItem[]) getIntent().getExtras().getSerializable("foodItems");

        }
        foodItemViewArray = new FoodItemView[foodArray.length];
        for(int i = 0; i < foodArray.length; i++)
        {

            //create foodItem views using foodArray
            //Add as children of the linearlayout
            //add to food itemview array
            FoodItemView foodItemView = new FoodItemView(this);
            foodItemView.setFavoriteDAO(db.favoriteDAO());
            foodListLayout.addView(foodItemView);
            foodItemView.setFromFoodItem(foodArray[i]);
            foodItemViewArray[i] = foodItemView;
            if(savedInstanceState != null)
            {
                foodItemView.setCount(counts[i]);
            }
        }



        viewCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            openCheckoutConfirmation();
            }
        });


    }

    private double calcPrice(){

         double total = 0;

        for(int i = 0; i < foodArray.length; i++)
        {

           total += foodArray[i].price*foodItemViewArray[i].getCount();
            System.out.println("Calculate"+total);

        }

        return total;

    }
    public void clearCart(){
        for(int i = 0; i < foodItemViewArray.length; i++)
        {
            foodItemViewArray[i].setCount(0);
        }
     }


//Method ask for users confirmation on the purchase
    private void openCheckoutConfirmation(){
        int duration = Toast.LENGTH_SHORT;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Total cost: $"+calcPrice()+",  Proceed with purchase?")
                .setTitle("Confirmation");
            Context context = this;
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                Toast.makeText(context,"Suscessful",duration).show();
                System.out.println("DOES THE TOAST WORK");

                clearCart();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });



        AlertDialog dialog = builder.create();
        dialog.show();

    }


    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //Things to save
        //Food Items
        //number of stuff in the cart




        int[] counts = new int[foodItemViewArray.length];
        savedInstanceState.putSerializable("foodItems",foodArray);
        for (int i = 0; i < foodItemViewArray.length; i++) {
            counts[i] = foodItemViewArray[i].getCount();
        }
        savedInstanceState.putSerializable("counts", counts);

    }








}