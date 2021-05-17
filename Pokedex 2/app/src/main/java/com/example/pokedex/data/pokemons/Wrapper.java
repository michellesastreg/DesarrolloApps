package com.example.pokedex.data.pokemons;

import com.example.pokedex.domain.PokemonsResponse;

import java.util.ArrayList;

public class Wrapper {

    private ArrayList<PokemonsResponse> results;

    public ArrayList<PokemonsResponse> getResults(){
        return results;
    }


    public void setResults(ArrayList<PokemonsResponse> results){
    this.results = results;
    }
}
