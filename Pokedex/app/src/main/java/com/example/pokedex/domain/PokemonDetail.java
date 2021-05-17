package com.example.pokedex.domain;

public class PokemonDetail {

    public int base_experience;
    public int height;
    public String name;
    public int id;
    public int weight;

    public String getImageUrl() {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
    }

}

