package com.example.fooddeliveryapp;

import androidx.room.Dao;
import androidx.room.Query;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface FavoriteDAO {



        @Query("SELECT isFav FROM Favorite WHERE food_name LIKE :foodName")
        Single<Boolean> isFavorited(String foodName);

        //Function sets favorited variable in the database
        @Query("Insert INTO Favorite (food_name, isFav) Values(:foodName, :isFav) ON CONFLICT(food_name) DO UPDATE SET isFav = :isFav;")
        Completable setFavorited(boolean isFav, String foodName);



}
