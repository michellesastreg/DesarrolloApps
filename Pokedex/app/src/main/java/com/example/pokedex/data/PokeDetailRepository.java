package com.example.pokedex.data;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokedex.UI.DetailActivity;
import com.example.pokedex.UI.MainActivity;
import com.example.pokedex.databinding.ActivityDetailBinding;
import com.example.pokedex.domain.PokemonDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokeDetailRepository {

    ApiPokeDetail api;

    public PokeDetailRepository(ApiPokeDetail api) {
        this.api = api;
    }



    public void getPokeDetails(PokeDetailCallback callback) {

        DetailActivity daInstance = new DetailActivity();
        String idCatch = daInstance.pokemonId;
        Log.d("displayedIdRepo", idCatch);


        Call<PokemonDetail> call = api.getDetail(idCatch);
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
