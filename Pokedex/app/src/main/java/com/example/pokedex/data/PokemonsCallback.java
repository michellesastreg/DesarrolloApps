package com.example.pokedex.data;

import com.example.pokedex.domain.Pokemon;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface PokemonsCallback {

    void onSuccess(ArrayList<Pokemon> results);

    void onError(String errorMessage);
}

