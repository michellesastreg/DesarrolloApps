package com.example.pokedex.data;

import com.example.pokedex.domain.Pokemon;
import com.example.pokedex.domain.PokemonDetail;

import java.util.ArrayList;
import java.util.List;

public interface PokeDetailCallback {

    void onSuccess(PokemonDetail detail);

    void onError(String errorMessage);

}