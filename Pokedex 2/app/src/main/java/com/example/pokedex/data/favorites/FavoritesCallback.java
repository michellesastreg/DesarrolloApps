package com.example.pokedex.data.favorites;

import java.util.ArrayList;

public interface FavoritesCallback {

    void onSuccess(ArrayList<FavoriteList> list);

    void onError(String errorMessage);
}