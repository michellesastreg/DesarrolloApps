package com.example.pokedex.UI;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;
import com.example.pokedex.data.PokemonListener;
import com.example.pokedex.databinding.ItemPokemonBinding;
import com.example.pokedex.domain.Pokemon;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonsViewHolder> {
    private ArrayList<Pokemon> items = new ArrayList<>();
    public PokemonListener listener;

    @NonNull
    @Override
    public PokemonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PokemonsViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(PokemonsViewHolder holder, int position) {
        holder.decorateWith(items.get(position));
        holder.itemView.setOnClickListener(view -> listener.onPokemonClicked(items.get(position).getPokemonId()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Pokemon> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

}
