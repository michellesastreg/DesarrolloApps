package com.example.bookcatalog.UI;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookcatalog.domain.Book;

class BooksViewHolder extends RecyclerView.ViewHolder {

    com.example.bookcatalog.databinding.ItemBookBinding itemBookBinding;


    public BooksViewHolder(com.example.bookcatalog.databinding.ItemBookBinding binding) {
        super(binding.getRoot());
        itemBookBinding = binding;
    }


    public void decorateWith(Book model) {
        Glide.with(itemView.getContext()).load(model.url).into(itemBookBinding.ivCover);
        itemBookBinding.tvTitle.setText("Título: " + model.name);
        itemBookBinding.tvEditorial.setText("Editorial: " + model.publishingHouse);
        itemBookBinding.tvAuthor.setText("Autor: " + model.author);
        itemBookBinding.tvPages.setText("Páginas: " + String.valueOf(model.pages));

    }

    public void isBestSeller (Book model){

        if (model.bestSeller){
            itemBookBinding.ivBestSeller.setVisibility(View.VISIBLE);
        }
        else {
            itemBookBinding.ivBestSeller.setVisibility(View.INVISIBLE);
        }
    }


}