package com.example.pokedex.data;

import com.example.pokedex.domain.PokemonDetail;

import java.util.List;

public interface PokeDetailCallback {

    void onSuccess(List<PokemonDetail> list);

    void onError(String errorMessage);
}
