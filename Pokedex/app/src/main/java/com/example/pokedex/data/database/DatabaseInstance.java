package com.example.pokedex.data.database;

import android.content.Context;

import androidx.room.Room;

public class DataBaseInstance {

    private static FavDataBase database;

    public static FavDataBase getRetrofitInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(
                    context,
                    FavDataBase.class,
                    "database-name"
            ).build();
        }
        return database;
    }
}