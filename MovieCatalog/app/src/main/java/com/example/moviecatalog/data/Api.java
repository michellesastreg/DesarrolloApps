package com.example.moviecatalog.data;

import com.example.moviecatalog.domain.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("simple-api/static_list_example.json")
    Call<List<Movie>> getMovies();
}
