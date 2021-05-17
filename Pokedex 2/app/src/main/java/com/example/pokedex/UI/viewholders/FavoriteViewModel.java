package com.example.pokedex.UI.viewholders;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokedex.data.favorites.FavoriteList;
import com.example.pokedex.data.favorites.FavoritesRepository;
import com.example.pokedex.data.pokemons.PokemonsRepository;
import com.example.pokedex.domain.FavoriteResponse;
import com.example.pokedex.domain.PokemonsResponse;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteViewModel extends ViewModel {
    private static final String TAG = "PokemonViewModel";

    private FavoritesRepository repository;
    private MutableLiveData<ArrayList<FavoriteList>> pokemonList = new MutableLiveData<>();
    private LiveData<List<FavoriteList>> favoritePokemonList = null;

    @ViewModelInject
    public FavoriteViewModel(FavoritesRepository repository) {
        this.repository = repository;
        favoritePokemonList = repository.getFavoritePokemon();
    }

    public MutableLiveData<ArrayList<FavoriteList>> getPokemonList() {
        return pokemonList;
    }

    public void getPokemons(){
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<FavoriteResponse, ArrayList<FavoriteList>>() {
                    @Override
                    public ArrayList<FavoriteList> apply(FavoriteResponse favoriteResponse) throws Throwable {
                        ArrayList<FavoriteList> list = favoriteResponse.getResults();
                        for(FavoriteList pokemon : list){
                            String url = pokemon.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/"+pokemonIndex[pokemonIndex.length-1] +".png");
                        }
                        Log.e(TAG, "apply: "+list.get(2).getUrl());
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> pokemonList.setValue(result),
                        error-> Log.e(TAG, "getPokemons: " + error.getMessage() ));
    }

    public void insertPokemon(FavoriteList pokemon){
        repository.insertPokemon(pokemon);
    }
    public void deletePokemon(String pokemonName){
        repository.deletePokemon(pokemonName);
    }

    public LiveData<List<FavoriteList>> getFavoritePokemonList() {
        return favoritePokemonList;
    }

    public void getFavoritePokemon(){
        favoritePokemonList = repository.getFavoritePokemon();
    }



}