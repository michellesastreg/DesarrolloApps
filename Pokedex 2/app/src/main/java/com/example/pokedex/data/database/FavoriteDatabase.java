package com.example.pokedex.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pokedex.data.favorites.FavoriteList;

@Database(entities={FavoriteList.class},version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();


}