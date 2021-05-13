package com.example.books.data;

import com.example.books.domain.Books;

import java.util.List;

public interface BooksCallback {
    void onSuccess(List<Books> list);

    void onError(String errorMessage);
}
