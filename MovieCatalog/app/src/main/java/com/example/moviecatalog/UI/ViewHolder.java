package com.example.moviecatalog.UI;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviecatalog.domain.Movie;

class MoviesViewHolder extends RecyclerView.ViewHolder {

    com.example.moviecatalog.databinding.ItemMovieBinding itemMovieBinding;

    public MoviesViewHolder(com.example.moviecatalog.databinding.ItemMovieBinding binding) {
        super(binding.getRoot());
        itemMovieBinding = binding;
    }

    public void decorateWith(Movie model) {
        Glide.with(itemView.getContext()).load(model.poster).into(itemMovieBinding.ivPoster);
        itemMovieBinding.title.setText(model.title);
    }
}