package com.example.pokedex.data;

import android.content.Context;

import androidx.room.Room;

public class DatabaseInstance {

    private static FavoriteDatabase database;

    public static FavoriteDatabase getRetrofitInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(
                    context,
                    FavoriteDatabase.class,
                    "database-name"
            ).build();
        }
        return database;
    }
}
