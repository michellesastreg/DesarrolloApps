package com.example.pokedex.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokedex.data.Api;
import com.example.pokedex.data.PokemonListener;
import com.example.pokedex.data.PokemonsCallback;
import com.example.pokedex.data.PokemonsRepository;
import com.example.pokedex.data.RetrofitInstance;
import com.example.pokedex.databinding.ActivityMainBinding;
import com.example.pokedex.domain.Pokemon;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    PokemonAdapter adapter;
    String idd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        adapter = new PokemonAdapter();
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerView.setAdapter(adapter);

        adapter.listener = new PokemonListener() {
            @Override
            public void onPokemonClicked(String id) {
                Toast.makeText(MainActivity.this, id, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("displayedId", id);
                startActivity(intent);
            }
        };



        pokemonsRepository.getPokemons(new PokemonsCallback() {
            @Override
            public void onSuccess(ArrayList<Pokemon> list) {
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