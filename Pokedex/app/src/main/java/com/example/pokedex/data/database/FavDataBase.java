package com.example.pokedex.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pokedex.domain.Pokemons;

    @Database(entities = {Pokemons.class}, version = 1)
    public abstract class FavDataBase extends RoomDatabase {
        public abstract FavDao favDao();
    }

