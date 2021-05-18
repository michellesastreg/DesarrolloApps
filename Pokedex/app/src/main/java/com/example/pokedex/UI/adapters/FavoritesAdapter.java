package com.example.pokedex.UI.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.UI.viewholders.FavoritesViewholder;
import com.example.pokedex.data.listeners.PokemonListener;
import com.example.pokedex.databinding.ItemFavpokemonBinding;
import com.example.pokedex.domain.PokemonDetail;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewholder> {
    private List<PokemonDetail> items = new ArrayList<PokemonDetail>();
    public PokemonListener listener;

    @NonNull
    @Override
    public FavoritesViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoritesViewholder(ItemFavpokemonBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(FavoritesViewholder holder, int position) {
        holder.decorateWith(items.get(position));
        holder.itemView.setOnClickListener(view -> listener.onPokemonClicked(items.get(position).getPokemonId()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void setItems(List<PokemonDetail> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

}