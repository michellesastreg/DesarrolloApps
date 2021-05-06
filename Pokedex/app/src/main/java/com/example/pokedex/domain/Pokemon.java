package com.example.pokedex.domain;

public class Pokemon {
    private String base_experience;
    private int game_index;
    private int height;
    private int weight;
    public String name;

    public String url;

    public String getImageUrl() {
        String pokemonId = url
                .replace("https://pokeapi.co/api/v2/pokemon/", "")
                .replace("/", "");
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemonId + ".png";
    }

}
