package com.example.pokedex.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.pokedex.data.favorites.FavoriteList;

import java.util.List;


@Dao
public interface FavoriteDao {

    @Insert
    void insertPokemon(FavoriteList pokemon);

    @Query("DELETE FROM favorite_table WHERE name = :pokemonName")
    void deletePokemon(String pokemonName);

    @Query("DELETE FROM favorite_table")
    void deleteAll();

    @Query("SELECT * FROM favorite_table")
    LiveData<List<FavoriteList>> getFavoritePokemons();
}