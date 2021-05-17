package com.example.pokedex.data.wrappers;

import com.example.pokedex.domain.Pokemons;

import java.util.ArrayList;

public class Wrapper {

    private ArrayList<Pokemons> results;

    public ArrayList<Pokemons> getResults(){
        return results;
    }


    public void setResults(ArrayList<Pokemons> results){
    this.results = results;
    }
}
