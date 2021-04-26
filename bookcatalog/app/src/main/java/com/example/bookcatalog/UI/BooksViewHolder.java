package com.example.bookcatalog.UI;

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
        itemBookBinding.tvTitle.setText(model.name);
        itemBookBinding.tvEditorial.setText(model.publishingHouse);
        itemBookBinding.tvAuthor.setText(model.author);
    }


}