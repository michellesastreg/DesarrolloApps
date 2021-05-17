package com.example.pokedex.UI.viewholders;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokedex.data.favorites.FavoriteList;
import com.example.pokedex.data.pokemons.PokemonsRepository;
import com.example.pokedex.databinding.ItemPokemonBinding;
import com.example.pokedex.domain.PokemonsResponse;

import java.util.ArrayList;
import java.util.List;

public class PokemonsViewHolder extends RecyclerView.ViewHolder {

    ItemPokemonBinding itemPokemonBinding;

    private PokemonsRepository repository;



    public PokemonsViewHolder(@NonNull ItemPokemonBinding binding) {
        super(binding.getRoot());
        itemPokemonBinding = binding;
    }


    public void decorateWith(PokemonsResponse model) {
        Glide.with(itemView.getContext())
                .load(model.getImageUrl())
                .into(itemPokemonBinding.ivPokemon);
        itemPokemonBinding.tvPokemon.setText(model.name);
    }


}
