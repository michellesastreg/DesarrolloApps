package com.example.pokedex.UI;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.pokedex.data.PokemonListener;
import com.example.pokedex.databinding.ActivityDetailBinding;
import com.example.pokedex.databinding.ItemPokemonBinding;
import com.example.pokedex.domain.Pokemon;
import com.example.pokedex.domain.PokemonDetail;

import java.util.ArrayList;
import java.util.List;

public class PokeDetailAdapter {
    private List<PokemonDetail> items = new ArrayList<>();

    @NonNull
    public PokeDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokeDetailViewHolder(ActivityDetailBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    public void onBindViewHolder(@NonNull PokeDetailViewHolder holder, int position) {
        holder.decorateWith(items.get(position));
    }

}
