package com.example.bookcatalog.data;

import com.example.bookcatalog.domain.Book;

import java.util.List;

public interface BooksCallback {

    void onSuccess(List<Book> list);

    void onError(String errorMessage);

}


