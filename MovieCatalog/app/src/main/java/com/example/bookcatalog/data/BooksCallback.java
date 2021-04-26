package com.example.bookcatalog.data;

import com.example.bookcatalog.domain.Movie;

import java.util.List;

public interface MoviesCallback {

    void onSuccess(List<Movie> list);

    void onError(String errorMessage);

}


