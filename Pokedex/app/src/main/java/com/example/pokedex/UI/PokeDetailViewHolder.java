package com.example.pokedex.UI;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokedex.databinding.ActivityDetailBinding;
import com.example.pokedex.databinding.ItemPokemonBinding;
import com.example.pokedex.domain.Pokemon;
import com.example.pokedex.domain.PokemonDetail;

public class PokeDetailViewHolder {

    ActivityDetailBinding activityDetailBinding;

    public PokeDetailViewHolder(@NonNull ActivityDetailBinding binding) {
         activityDetailBinding= binding;
    }


    public void decorateWith(PokemonDetail model) {
        activityDetailBinding.tvId.setText(model.getPokemonId());
        activityDetailBinding.tvAltura.setText(model.height);
        activityDetailBinding.tvId.setText(model.weight);
        activityDetailBinding.tvId.setText(model.base_experience);

    }
}
