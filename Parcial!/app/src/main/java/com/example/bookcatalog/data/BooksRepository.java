package com.example.bookcatalog.data;

import com.example.bookcatalog.domain.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksRepository {

    private Api api;

    public BooksRepository(Api api) {
        this.api = api;
    }

    public void getMovies(BooksCallback callback) {
        Call<List<Book>> call = api.getBooks();
        ((Call) call).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Hubo un error!");
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                callback.onError("Hubo un error!");
            }
        });
    }






}

