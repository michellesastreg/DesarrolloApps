package com.example.pokedex.data;

import com.example.pokedex.domain.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonsRepository {
    private Api api;

    public PokemonsRepository(Api api) {
        this.api = api;
    }

    public void getPokemons(PokemonsCallback callback) {
        Call<List<Pokemon>> call = api.getPokemons();
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Hubo un error!");
                }
            }
            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                callback.onError("Hubo un error!");
            }
        });
    }
}

