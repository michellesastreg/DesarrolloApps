package com.example.pokedex.data.api;

import com.example.pokedex.data.wrappers.Wrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("pokemon")
    Call<Wrapper> getPokemons();
}
