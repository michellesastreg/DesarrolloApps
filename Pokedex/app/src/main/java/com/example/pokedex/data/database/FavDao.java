package com.example.pokedex.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pokedex.domain.PokemonDetail;
import com.example.pokedex.domain.Pokemons;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface FavDao {
    @Query("SELECT * FROM PokemonDetail")
    List<PokemonDetail> getAll();

    @Query("SELECT * FROM PokemonDetail WHERE name = :name")
    PokemonDetail getPokemon(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PokemonDetail... pokemon);

    @Delete
    void delete(PokemonDetail pokemon);

}
