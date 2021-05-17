package com.example.pokedex.data.callback;

import com.example.pokedex.domain.Pokemons;

import java.util.ArrayList;

public interface PokemonsCallback {

    void onSuccess(ArrayList<Pokemons> list);

    void onError(String errorMessage);

    void onDataBaseResponse(Pokemons pokemon);
}

