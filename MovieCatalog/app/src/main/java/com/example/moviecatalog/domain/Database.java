package com.example.moviecatalog.domain;

import androidx.room.Database;
import androidx.room.RoomDatabase;


    @Database(entities = {Movie.class}, version = 1)
    abstract class AppDatabase extends RoomDatabase {
        public abstract DAO.MovieDao movieDao();
}
