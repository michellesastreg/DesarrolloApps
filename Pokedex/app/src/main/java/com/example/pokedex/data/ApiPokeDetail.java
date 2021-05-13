package com.example.pokedex.data;

import com.example.pokedex.domain.PokemonDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiPokeDetail {
    @GET("pokemon/{pokemonId}")
    Call<PokemonDetail> getDetail(
            @Path("pokemonId") String pokemon
    );
}
