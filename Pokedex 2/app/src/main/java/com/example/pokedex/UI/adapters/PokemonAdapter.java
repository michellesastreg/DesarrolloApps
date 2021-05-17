package com.example.pokedex.UI.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.UI.viewholders.PokemonsViewHolder;
import com.example.pokedex.data.favorites.FavoriteList;
import com.example.pokedex.data.PokemonListener;
import com.example.pokedex.databinding.ItemPokemonBinding;
import com.example.pokedex.domain.PokemonsResponse;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonsViewHolder> {
    private ArrayList<PokemonsResponse> items = new ArrayList<>();
    private ArrayList<FavoriteList> favItems = new ArrayList<>();
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

    public  void updateList(ArrayList<FavoriteList> updatedList){
        favItems = updatedList;
        notifyDataSetChanged();
    }

    public void setItems(ArrayList<PokemonsResponse> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    public FavoriteList getPokemonAt(int position){
        return favItems.get(position);
    }

}
