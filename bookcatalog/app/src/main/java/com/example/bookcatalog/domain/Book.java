package com.example.bookcatalog.domain;

import java.util.List;

public class Book {

    public String url;
    public String name;
    public String author;
    public String publishingHouse;

    public Book(int pages) {
        this.pages = pages;
    }

    public int pages;

}