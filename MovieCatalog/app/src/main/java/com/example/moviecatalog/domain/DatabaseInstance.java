package com.example.moviecatalog.domain;

import android.content.Context;

import androidx.room.Room;

public class DatabaseInstance {

        private static Database.AppDatabase database;

        public static Database.AppDatabase getRetrofitInstance(Context context) {
            if (database == null) {
                database = Room.databaseBuilder(
                        context,
                        Database.AppDatabase.class,
                        "database-name"
                ).build();
            }
            return database;
        }
    }

