package com.example.pokedex.UI.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokedex.databinding.ItemPokemonBinding;
import com.example.pokedex.domain.Pokemons;

public class PokemonsViewHolder extends RecyclerView.ViewHolder {

    ItemPokemonBinding itemPokemonBinding;

    public PokemonsViewHolder(@NonNull ItemPokemonBinding binding) {
        super(binding.getRoot());
        itemPokemonBinding = binding;
    }


    public void decorateWith(Pokemons model) {
        Glide.with(itemView.getContext())
                .load(model.getImageUrl())
                .into(itemPokemonBinding.ivPokemon);
        itemPokemonBinding.tvPokemon.setText(model.name);
    }
}
