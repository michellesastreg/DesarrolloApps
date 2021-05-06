package com.example.pokedex.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.pokedex.R;
import com.example.pokedex.data.Api;
import com.example.pokedex.data.PokemonsCallback;
import com.example.pokedex.data.PokemonsRepository;
import com.example.pokedex.data.RetrofitInstance;
import com.example.pokedex.databinding.ActivityMainBinding;
import com.example.pokedex.domain.Pokemon;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    PokemonAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        adapter = new PokemonAdapter();
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerView.setAdapter(adapter);


        pokemonsRepository.getPokemons(new PokemonsCallback() {
            @Override
            public void onSuccess(List<Pokemon> list) {
                adapter.setItems(list);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    PokemonsRepository pokemonsRepository = new PokemonsRepository(RetrofitInstance.getRetrofitInstance().create(Api.class));
}