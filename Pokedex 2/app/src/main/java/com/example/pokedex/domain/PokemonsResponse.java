package com.example.pokedex.domain;

import com.example.pokedex.data.favorites.FavoriteList;

import java.util.ArrayList;

public class PokemonsResponse {

    public String name;
    public String url;
    private ArrayList<FavoriteList> results;


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

    public ArrayList<FavoriteList> getResults() {
        return results;
    }

    public void setResults(ArrayList<FavoriteList> results) {
        this.results = results;
    }


}
