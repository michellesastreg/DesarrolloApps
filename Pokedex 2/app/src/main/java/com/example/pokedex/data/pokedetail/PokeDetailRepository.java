package com.example.pokedex.data.pokedetail;

import com.example.pokedex.data.api.ApiPokeDetail;
import com.example.pokedex.domain.PokemonDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokeDetailRepository {

    ApiPokeDetail api;

    public PokeDetailRepository(ApiPokeDetail api) {
        this.api = api;
    }



    public void getPokeDetails(PokeDetailCallback callback, String idRepo) {


        Call<PokemonDetail> call = api.getDetail(idRepo);
        call.enqueue(new Callback<PokemonDetail>() {
            @Override
            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Hubo un error! Error");
                }
            }
            @Override
            public void onFailure(Call<PokemonDetail> call, Throwable t) {
                callback.onError("Hubo un error! Failure ");
            }
        });
    }
}
