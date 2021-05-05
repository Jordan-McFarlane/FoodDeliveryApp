package com.example.fooddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class FoodItemView extends ConstraintLayout {
    // internal components
    private ImageView image;
    private TextView descriptionView;
    private TextView counterView;
    private Button plus;
    private Button minus;
    private int count;
    private  TextView priceView;
    private boolean isFav;
    private ImageView favView;
    private FavoriteDAO favDAO;
    private FoodItem item;


    public FoodItemView(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.food_preview, this);


        image = (ImageView)findViewById(R.id.imageView);
        descriptionView = (TextView)findViewById(R.id.descriptionTextView);
        counterView = (TextView) findViewById(R.id.counter);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        priceView =  (TextView) findViewById(R.id.priceView);
        favView = (ImageView) findViewById(R.id.favorite);
        setIsFav(false);





        System.out.println("counterView: "+ counterView);

        plus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                showCounter();
            }
        });

        minus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 0)
                {
                    count--;
                    showCounter();
                }
                else
                {

                }
            }
        });

        count = 0;
        showCounter();


        favView.setClickable(true);
        favView.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
               setIsFav(!isFav);
               //TODO If clicked set isFav in database
                //use setFavorited(isFav,item.description)

               favDAO.setFavorited(isFav, item.description);




            }
        });




    }




    public void setIsFav(boolean isFav){

        this.isFav = isFav;
        //isFav is false initally
        //if it is clicked it is set to true
        //and the image view picture value changes
        //If the image view is click
       if(isFav == true)
       {
           favView.setImageResource(R.drawable.heart_clicked);
       }
       else
       {
           favView.setImageResource(R.drawable.heart_unclicked);
       }


    }






    /*
        public void setButtonOnClickListener(View.OnClickListener listener)
        {
            addToCart.setOnClickListener(listener);

        }
    */




    public void showCounter(){
        counterView.setText(String.valueOf(count));
    }




    public void setFromFoodItem(FoodItem item){
        image.setImageResource(item.imageResId);
        descriptionView.setText(item.description);
        priceView.setText(String.format("$%.2f",item.price));
        this.item = item;
        //TODO is it saved in the data base isFavorited pass in item.description
        //set isFav to whats in the database
       Single<Boolean> isFavedSingle = favDAO.isFavorited(item.description);
        Disposable disposable = isFavedSingle
                .subscribeWith(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onSuccess(Boolean isFaved) {
                        // Do something
                        setIsFav(isFaved.booleanValue());

                    }
                });















    }

    public void setCount(int count)
    {
        this.count = count;
        showCounter();
    }
    public int getCount(){
       return count;
    }

    public void setFavoriteDAO(FavoriteDAO favDAO){
        this.favDAO = favDAO;
    }

}



