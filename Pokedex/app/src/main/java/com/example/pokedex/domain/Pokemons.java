package com.example.pokedex.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pokemons {

    @PrimaryKey
    @NonNull
    public String name;
    public String url;

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

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }

    public String getUrl(){
        return name;
    }

    public void setUrl(){
        this.name = name;
    }

}
