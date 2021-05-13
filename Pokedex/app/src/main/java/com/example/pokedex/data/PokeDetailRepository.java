package com.example.pokedex.data;

import android.content.Intent;

import com.example.pokedex.UI.MainActivity;
import com.example.pokedex.domain.Pokemon;
import com.example.pokedex.domain.PokemonDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class PokeDetailRepository extends MainActivity{
    public PokeDetailRepository (){}
    private ApiPokeDetail api;
    Intent intent = getIntent();
    String id = intent.getExtras().getString("id");


    public PokeDetailRepository(ApiPokeDetail api){
        this.api = api;
    }


    public void getPokeDetail(PokeDetailCallback callback) {

        Call<PokemonDetail> pokemonDetailCall = api.getDetail(id);
        pokemonDetailCall.enqueue(new Callback<PokemonDetail>  (){
            @Override
            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess((List<PokemonDetail>) response.body());

                } else {
                    callback.onError("Hubo un error!");
                }
            }
            @Override
            public void onFailure(Call<PokemonDetail>   call, Throwable t) {
                callback.onError("Hubo un error!");
            }
        });
    }
}
