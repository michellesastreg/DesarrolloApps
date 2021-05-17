package com.example.pokedex.data.callback;

import com.example.pokedex.domain.PokemonDetail;

public interface PokeDetailCallback {

    void onSuccess(PokemonDetail detail);

    void onError(String errorMessage);

}