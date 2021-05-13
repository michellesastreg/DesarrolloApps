package com.example.moviecatalog.UI;

import android.graphics.Movie;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

class MoviesViewHolder extends RecyclerView.ViewHolder {

    com.example.moviecatalog.databinding.ItemMovieBinding itemMovieBinding;

    public MoviesViewHolder(com.example.moviecatalog.databinding.ItemMovieBinding binding) {
        super(binding.getRoot());
        itemMovieBinding = binding;
    }

    }