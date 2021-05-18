package com.example.pokedex.data.callback;

import com.example.pokedex.domain.PokemonDetail;

import java.util.ArrayList;
import java.util.List;


public interface FavoritesCallback {

    void onSuccess(List<PokemonDetail> list);

    void onError(String errorMessage);

    void onDataBaseResponse(PokemonDetail pokemon);

}
