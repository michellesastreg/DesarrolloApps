package com.example.pokedex.data;

import com.example.pokedex.domain.Pokemon;

import java.util.ArrayList;

public interface PokemonsCallback {

    void onSuccess(ArrayList<Pokemon> list);

    void onError(String errorMessage);
}

