package com.example.pokedex.domain;

public class Pokemon {

    public String name;
    public String url;

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
