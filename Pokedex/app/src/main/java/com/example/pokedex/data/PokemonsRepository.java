
package com.example.pokedex.data;

import android.util.Log;

import com.example.pokedex.domain.Pokemon;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class PokemonsRepository {
    private Api api;

    public PokemonsRepository(Api api) {
        this.api = api;
    }

    public void getPokemons(PokemonsCallback callback) {
        Call <Wrapper> wrapperCall = api.getPokemons();
        wrapperCall.enqueue(new Callback<Wrapper>() {
            @Override
            public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().getResults());

                } else {
                    callback.onError("Hubo un error!");
                }
            }
            @Override
            public void onFailure(Call<Wrapper> call, Throwable t) {
                callback.onError("Hubo un error!");
            }
        });
    }
}