package com.example.pokedex.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pokedex.R;
import com.example.pokedex.UI.adapters.PokemonAdapter;
import com.example.pokedex.data.api.Api;
import com.example.pokedex.data.database.DatabaseInstance;
import com.example.pokedex.data.database.FavoriteDao;
import com.example.pokedex.data.database.FavoriteDatabase;
import com.example.pokedex.data.favorites.FavoriteList;
import com.example.pokedex.data.PokemonListener;
import com.example.pokedex.data.pokemons.PokemonsCallback;
import com.example.pokedex.data.pokemons.PokemonsRepository;
import com.example.pokedex.data.RetrofitInstance;
import com.example.pokedex.databinding.ActivityMainBinding;
import com.example.pokedex.domain.PokemonsResponse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    PokemonAdapter adapter;
    Button bnSortFav;
    public static FavoriteDatabase favoriteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        FavoriteDao dao = DatabaseInstance.getRetrofitInstance(getApplicationContext()).favoriteDao();


        adapter = new PokemonAdapter();
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerView.setAdapter(adapter);
        adapter.listener = new PokemonListener() {
            @Override
            public void onPokemonClicked(String id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("displayedId", id);
                Log.d("displayedIdMAIN", id);
                startActivity(intent);
            }
        };

        bnSortFav = findViewById(R.id.bnSortFav);
        bnSortFav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (v==bnSortFav){
                    startActivity(new Intent(MainActivity.this, Favorites.class));
                }
            }
        });

        favoriteDatabase= Room.databaseBuilder(getApplicationContext(), FavoriteDatabase.class,"myfavdb").allowMainThreadQueries().build();




        pokemonsRepository.getPokemons(new PokemonsCallback() {
            @Override
            public void onSuccess(ArrayList<PokemonsResponse> list) {
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