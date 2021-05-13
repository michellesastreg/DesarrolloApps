package com.example.pokedex.UI;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokedex.data.Api;
import com.example.pokedex.data.ApiPokeDetail;
import com.example.pokedex.data.PokeDetailCallback;
import com.example.pokedex.data.PokeDetailRepository;
import com.example.pokedex.data.PokemonsRepository;
import com.example.pokedex.data.RetrofitInstance;
import com.example.pokedex.databinding.ActivityDetailBinding;
import com.example.pokedex.domain.Pokemon;
import com.example.pokedex.domain.PokemonDetail;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    PokemonAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pokeDetailRepository.getPokeDetail(new PokeDetailCallback() {
            @Override
            public void onSuccess(List<PokemonDetail> list) {

            }
            @Override
            public void onError(String errorMessage) {
                Toast.makeText(DetailActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    PokeDetailRepository pokeDetailRepository = new PokeDetailRepository(RetrofitInstance.getRetrofitInstance().create(ApiPokeDetail.class));
}