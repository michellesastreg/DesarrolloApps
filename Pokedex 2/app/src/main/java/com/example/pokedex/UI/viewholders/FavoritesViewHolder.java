package com.example.pokedex.UI.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokedex.data.favorites.FavoriteList;
import com.example.pokedex.databinding.ItemPokemonBinding;

public class FavoritesViewHolder extends RecyclerView.ViewHolder {

    ItemPokemonBinding itemPokemonBinding;

    public FavoritesViewHolder(@NonNull ItemPokemonBinding binding) {
        super(binding.getRoot());
        itemPokemonBinding = binding;
    }


    public void decorateWith(FavoriteList model) {
        Glide.with(itemView.getContext())
                .load(model.getImageUrl())
                .into(itemPokemonBinding.ivPokemon);
        itemPokemonBinding.tvPokemon.setText(model.getName());
    }
}
