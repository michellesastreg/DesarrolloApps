package com.example.pokedex.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pokedex.R;
import com.example.pokedex.UI.adapters.PokemonAdapter;
import com.example.pokedex.data.api.Api;
import com.example.pokedex.data.database.DataBaseInstance;
import com.example.pokedex.data.database.FavDao;
import com.example.pokedex.data.listeners.PokemonListener;
import com.example.pokedex.data.callback.PokemonsCallback;
import com.example.pokedex.data.repositories.PokemonsRepository;
import com.example.pokedex.data.RetrofitInstance;
import com.example.pokedex.databinding.ActivityMainBinding;
import com.example.pokedex.domain.Pokemons;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    PokemonAdapter adapter;
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    Button sortFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        adapter = new PokemonAdapter();

        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerView.setAdapter(adapter);

        FavDao dao = DataBaseInstance.getRetrofitInstance(getApplicationContext()).favDao();
        PokemonsRepository pokemonsRepository = new PokemonsRepository(
                RetrofitInstance.getRetrofitInstance().create(Api.class)
                ,executorService,mainThreadHandler,
                DataBaseInstance.getRetrofitInstance(getApplicationContext()).favDao());


        adapter.listener = new PokemonListener() {
            @Override
            public void onPokemonClicked(String id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("displayedId", id);
                Log.d("displayedIdMAIN", id);
                startActivity(intent);
            }
        };

        sortFav = findViewById(R.id.bnSortFav);
        sortFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pokemonsRepository.getFavoritePokemons(new PokemonsCallback() {
                    @Override
                    public void onSuccess(ArrayList<Pokemons> list) {
                        adapter.setItems(list);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onDataBaseResponse(Pokemons pokemon) {
                    }
                });
            }
        });



        pokemonsRepository.getPokemons(new PokemonsCallback() {
            @Override
            public void onSuccess(ArrayList<Pokemons> list) {
                adapter.setItems(list);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDataBaseResponse(Pokemons pokemon) {
            }

        });
    }

}