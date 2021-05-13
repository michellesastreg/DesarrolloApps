package com.example.books.data;

import com.example.books.domain.Books;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("simple-api/books.json")
    Call<List<Books>> getBooks();
}
