package com.example.pokedex.data;

import com.example.pokedex.domain.Pokemon;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Wrapper {
    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults(){
        return results;
    }


    public void setResults(ArrayList<Pokemon> results){
    this.results = results;
    }
}
