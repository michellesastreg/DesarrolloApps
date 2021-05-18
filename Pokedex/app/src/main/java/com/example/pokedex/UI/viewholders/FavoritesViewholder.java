package com.example.pokedex.UI.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokedex.databinding.ItemFavpokemonBinding;
import com.example.pokedex.databinding.ItemPokemonBinding;
import com.example.pokedex.domain.PokemonDetail;
import com.example.pokedex.domain.Pokemons;

public class FavoritesViewholder extends RecyclerView.ViewHolder {

    ItemFavpokemonBinding itemFavpokemonBinding;

    public FavoritesViewholder(@NonNull ItemFavpokemonBinding binding) {
        super(binding.getRoot());
        itemFavpokemonBinding = binding;
    }


    public void decorateWith(PokemonDetail model) {
        Glide.with(itemView.getContext())
                .load(model.getImageUrl())
                .into(itemFavpokemonBinding.ivPokemon);
        itemFavpokemonBinding.tvPokemon.setText(model.name);

    }
}
