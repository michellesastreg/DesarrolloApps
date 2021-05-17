package com.example.pokedex.data.pokemons;

import com.example.pokedex.domain.PokemonsResponse;

import java.util.ArrayList;

public interface PokemonsCallback {

    void onSuccess(ArrayList<PokemonsResponse> list);

    void onError(String errorMessage);
}

