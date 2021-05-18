package com.example.pokedex.domain;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PokemonDetail {

    @PrimaryKey
    @NonNull
    public int base_experience;
    public int height;
    public String name;
    public int id;
    public int weight;
    public String url;

    public String getImageUrl() {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
    }

    public String getPokemonId(){
        return url
                .replace("https://pokeapi.co/api/v2/pokemon/", "")
                .replace("/", "");
    }

}

