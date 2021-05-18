package com.example.pokedex.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pokedex.UI.DetailActivity;
import com.example.pokedex.domain.PokemonDetail;
import com.example.pokedex.domain.Pokemons;

    @Database(entities = {PokemonDetail.class}, version = 1)
    public abstract class FavDataBase extends RoomDatabase {
        public abstract FavDao favDao();
    }

