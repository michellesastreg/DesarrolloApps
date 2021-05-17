package com.example.pokedex.data.api;

import com.example.pokedex.domain.FavoriteResponse;
import com.example.pokedex.domain.PokemonsResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface FavoriteApi {

    @GET("pokemon")
    Observable<FavoriteResponse> getPokemons();
}
