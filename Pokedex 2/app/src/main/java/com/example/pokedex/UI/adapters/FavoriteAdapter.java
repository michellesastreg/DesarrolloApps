package com.example.pokedex.UI.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.UI.viewholders.FavoritesViewHolder;
import com.example.pokedex.data.favorites.FavoriteList;
import com.example.pokedex.data.PokemonListener;
import com.example.pokedex.databinding.ItemPokemonBinding;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {

    private List<FavoriteList> items;
    public PokemonListener listener;
    Context context;
    private ItemPokemonBinding binding;

    public FavoriteAdapter(Context context, ArrayList<FavoriteList> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoritesViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        holder.decorateWith(items.get(position));
        holder.itemView.setOnClickListener(view -> listener.onPokemonClicked(items.get(position).getPokemonId()));
    }



    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class PokemonViewHolder extends RecyclerView.ViewHolder{
        private  ItemPokemonBinding itemBinding;

        public PokemonViewHolder(ItemPokemonBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }

    public  void updateList(ArrayList<FavoriteList> updatedList){
        items = updatedList;
        notifyDataSetChanged();
    }

    public FavoriteList getPokemonAt(int position){
        return items.get(position);
    }
}


