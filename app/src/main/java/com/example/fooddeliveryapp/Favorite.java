package com.example.fooddeliveryapp;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Favorite {


        @ColumnInfo(name = "food_name")

        @PrimaryKey
        @NonNull
        public String foodName;

        @ColumnInfo(name = "isFav")
        public boolean isFav;



}



