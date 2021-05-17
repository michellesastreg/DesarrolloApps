
package com.example.pokedex.data.repositories;

import android.os.Handler;

import com.example.pokedex.data.callback.PokemonsCallback;
import com.example.pokedex.data.database.FavDao;
import com.example.pokedex.data.wrappers.Wrapper;
import com.example.pokedex.data.api.Api;
import com.example.pokedex.domain.Pokemons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonsRepository {

    private final Executor executor;
    private final Handler handler;
    private final Api api;
    private final FavDao dao;

    public PokemonsRepository(
            Api api,
            Executor executor,
            Handler handler,
            FavDao dao
    ) {
        this.api = api;
        this.executor = executor;
        this.handler = handler;
        this.dao = dao;
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

    public void addPokemonToFavorite(Pokemons pokemon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertAll(pokemon);
            }
        });
    }

    public void removePokemonFromFavorite(Pokemons pokemon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete(pokemon);
            }
        });
    }

    public void getFavoritePokemons(PokemonsCallback callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<Pokemons> list = (ArrayList<Pokemons>) dao.getAll();
                postSuccessResult(callback, list);
            }
        });
    }

    public void getPokemon(Pokemons pokemon, PokemonsCallback pokemonsCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                postResult(pokemonsCallback, dao.getPokemon(pokemon.name));
            }
        });
    }

    private void postSuccessResult(PokemonsCallback callback, ArrayList<Pokemons> list) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(list);
            }
        });
    }

    private void postResult(PokemonsCallback callback, Pokemons pokemon) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onDataBaseResponse(pokemon);
            }
        });
    }

}