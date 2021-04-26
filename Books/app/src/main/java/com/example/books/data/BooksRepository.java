package com.example.books.data;

import com.example.books.domain.Books;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksRepository {
    private Api api;

    public BooksRepository(Api api) {
        this.api = api;
    }
    public void getBooks(BooksCallback callback) {
        Call<List<Books>> call = api.getBooks();
        call.enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Hubo un error!");
                }
            }
            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {
                callback.onError("Hubo un error!");
            }
        });
    }
}
