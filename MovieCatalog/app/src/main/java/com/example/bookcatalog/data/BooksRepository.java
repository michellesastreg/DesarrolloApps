package com.example.bookcatalog.data;

import com.example.bookcatalog.domain.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    private Api api;

    public MoviesRepository(Api api) {
        this.api = api;
    }

    public void getMovies(MoviesCallback callback) {
        Call<List<Movie>> call = api.getMovies();
        ((Call) call).enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Hubo un error!");
                }
            }
            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                callback.onError("Hubo un error!");
            }
        });
    }






}

