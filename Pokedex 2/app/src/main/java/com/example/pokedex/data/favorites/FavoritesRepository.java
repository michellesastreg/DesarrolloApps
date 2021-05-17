package com.example.pokedex.data.favorites;

import androidx.lifecycle.LiveData;

import com.example.pokedex.data.api.Api;
import com.example.pokedex.data.api.FavoriteApi;
import com.example.pokedex.data.database.FavoriteDao;
import com.example.pokedex.domain.FavoriteResponse;
import com.example.pokedex.domain.PokemonsResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class FavoritesRepository {

    private FavoriteDao favoriteDao;
    private FavoriteApi apiService;

    @Inject
    public FavoritesRepository(FavoriteDao favoriteDao, FavoriteApi apiService) {
        this.favoriteDao = favoriteDao;
        this.apiService = apiService;
    }


    public Observable<FavoriteResponse> getPokemons(){
        return apiService.getPokemons();
    }

    public void insertPokemon(FavoriteList pokemon){
        favoriteDao.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName){
        favoriteDao.deletePokemon(pokemonName);
    }

    public void deleteAll(){
        favoriteDao.deleteAll();
    }

    public LiveData<List<FavoriteList>> getFavoritePokemon(){
        return favoriteDao.getFavoritePokemons();
    }
}