package com.example.moviecatalog.data;

import com.example.moviecatalog.domain.Movie;

import java.util.List;

public interface MoviesCallback {

    void onSuccess(List<Movie> list);

    void onError(String errorMessage);

}


