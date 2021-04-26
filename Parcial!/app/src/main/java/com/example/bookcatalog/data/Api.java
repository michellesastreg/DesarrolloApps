package com.example.bookcatalog.data;

import com.example.bookcatalog.domain.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("simple-api/books.json")
    Call<List<Book>> getBooks();
}
