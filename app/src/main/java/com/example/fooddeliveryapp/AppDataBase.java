package com.example.fooddeliveryapp;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Favorite.class}, version = 1)
abstract class AppDataBase extends RoomDatabase {
        public abstract FavoriteDAO favoriteDAO();
    }



