package com.example.pokedex.data.repositories;

import android.os.Handler;

import com.example.pokedex.data.callback.FavoritesCallback;
import com.example.pokedex.data.callback.PokeDetailCallback;
import com.example.pokedex.data.api.ApiPokeDetail;
import com.example.pokedex.data.callback.PokemonsCallback;
import com.example.pokedex.data.database.FavDao;
import com.example.pokedex.domain.PokemonDetail;
import com.example.pokedex.domain.Pokemons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokeDetailRepository {

    ApiPokeDetail api;
    private final Executor executor;
    private final Handler handler;
    private final FavDao dao;



    public PokeDetailRepository(ApiPokeDetail api, Executor executor, Handler handler, FavDao dao) {
        this.api = api;
        this.executor = executor;
        this.handler = handler;
        this.dao = dao;
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


    public void addPokemonToFav(PokemonDetail pokemon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertAll(pokemon);
            }
        });
    }

    public void removePokemonFromFav(PokemonDetail pokemon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete(pokemon);
            }
        });
    }

    public void getFavoritePokemons(FavoritesCallback callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<PokemonDetail> list = dao.getAll();
                postSuccessResult(callback, list);
            }
        });
    }

    public void getPokemon(PokemonDetail pokemon, FavoritesCallback favoritesCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                postResult(favoritesCallback, dao.getPokemon(pokemon.name));
            }
        });
    }

    private void postSuccessResult(FavoritesCallback callback, List<PokemonDetail> list) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(list);
            }
        });
    }

    private void postResult(FavoritesCallback callback, PokemonDetail pokemon) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onDataBaseResponse(pokemon);
            }
        });
    }

}
