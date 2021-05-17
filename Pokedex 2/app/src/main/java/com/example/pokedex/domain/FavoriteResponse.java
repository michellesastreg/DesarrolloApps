package com.example.pokedex.domain;

import com.example.pokedex.data.favorites.FavoriteList;

import java.util.ArrayList;

public class FavoriteResponse {

    private Integer count;
    private String next,previous;
    private ArrayList<FavoriteList> results;

    public FavoriteResponse(Integer count, String next, String previous, ArrayList<FavoriteList> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public ArrayList<FavoriteList> getResults() {
        return results;
    }

    public void setResults(ArrayList<FavoriteList> results) {
        this.results = results;
    }
}

