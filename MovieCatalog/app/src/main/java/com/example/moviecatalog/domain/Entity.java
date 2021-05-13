package com.example.moviecatalog.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

    @Entity
    class Movie {

        @PrimaryKey
        @NonNull
        public String tittle;
        public double rate;
        public int year;
        public String director;
        @Ignore
        public List<String> stars;
        public String summary;
        public String poster;
        public String genere;

        public Movie() {
            tittle = "";
        }
    }


