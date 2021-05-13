package com.example.pokedex.domain;

public class PokemonDetail {

    public String base_experience;
    public int height;
    public int id;
    public int weight;
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

}
