package com.example.pokedex.data;

import com.example.pokedex.domain.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("pokemon/?limit=50")
    Call<List<Pokemon>> getPokemons();
}
