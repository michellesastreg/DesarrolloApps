package com.example.pokedex.data.favorites;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "favorite_table")
public class FavoriteList {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    private String url;

    public FavoriteList(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPokemonId(){
        return url
                .replace("https://pokeapi.co/api/v2/pokemon/", "")
                .replace("/", "");
    }

    public String getImageUrl() {
        String pokemonId = url
                .replace("https://pokeapi.co/api/v2/pokemon/", "")
                .replace("/", "");
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemonId + ".png";
    }

}