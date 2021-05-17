package com.example.pokedex.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pokedex.domain.Pokemons;

import java.util.List;

@Dao
public interface FavDao {
    @Query("SELECT * FROM Pokemons")
    List<Pokemons> getAll();

    @Query("SELECT * FROM Pokemons WHERE name = :name")
    Pokemons getPokemon(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Pokemons... pokemon);

    @Delete
    void delete(Pokemons pokemon);
}
