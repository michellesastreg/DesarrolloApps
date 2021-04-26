package com.example.books.data;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.books.databinding.ItemBooksBinding;
import com.example.books.domain.Books;

public class BooksViewHolder extends RecyclerView.ViewHolder {

    ItemBooksBinding itemBooksBinding;

    public BooksViewHolder(ItemBooksBinding binding) {
        super(binding.getRoot());
        itemBooksBinding = binding;
    }

    public void decorateWith(Books model) {
        Glide.with(itemView.getContext()).load(model.poster).into(itemBooksBinding.ivPoster);
        itemBooksBinding.name.setText(model.name);
    }
}