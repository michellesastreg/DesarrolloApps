package com.example.pokedex.UI;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokedex.databinding.ItemPokemonBinding;
import com.example.pokedex.domain.Pokemon;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    ItemPokemonBinding itemPokemonBinding;

    public PokemonViewHolder(@NonNull ItemPokemonBinding binding) {
        super(binding.getRoot());
        itemPokemonBinding = binding;
    }

    public void decorateWith(Pokemon model) {
        Glide.with(itemView.getContext())
                .load(model.getImageUrl())
                .into(itemPokemonBinding.ivPokemon);
        itemPokemonBinding.tvPokemon.setText(model.name);
    }
}
