package com.example.pokedex.data;

import com.example.pokedex.domain.Pokemon;

import java.util.List;

public interface PokemonsCallback {

    void onSuccess(List<Pokemon> list);

    void onError(String errorMessage);
}

