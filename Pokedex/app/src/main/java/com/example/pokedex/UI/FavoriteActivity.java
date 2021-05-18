package com.example.pokedex.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.pokedex.UI.adapters.FavoritesAdapter;
import com.example.pokedex.data.RetrofitInstance;
import com.example.pokedex.data.api.ApiPokeDetail;
import com.example.pokedex.data.callback.FavoritesCallback;
import com.example.pokedex.data.database.DataBaseInstance;
import com.example.pokedex.data.database.FavDao;
import com.example.pokedex.data.repositories.PokeDetailRepository;
import com.example.pokedex.databinding.ActivityFavoriteBinding;
import com.example.pokedex.domain.PokemonDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteActivity extends AppCompatActivity {

        ActivityFavoriteBinding binding;
        FavoritesAdapter adapter;
        PokeDetailRepository pokeDetailRepository;

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            setSupportActionBar(binding.toolbar);

            adapter = new FavoritesAdapter();

            binding.recyclerViewFavs.setLayoutManager(new GridLayoutManager(this, 2));
            binding.recyclerViewFavs.setAdapter(adapter);


            Intent intent = getIntent();


            pokeDetailRepository = new PokeDetailRepository
                    (RetrofitInstance.getRetrofitInstance().create(ApiPokeDetail.class),
                    executorService,
                    mainThreadHandler,
                    DataBaseInstance.getRetrofitInstance
                    (getApplicationContext()).favDao());


            pokeDetailRepository.getFavoritePokemons(new FavoritesCallback() {
                @Override
                public void onSuccess(List<PokemonDetail> list) {
                    adapter.setItems(list);
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(FavoriteActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onDataBaseResponse(PokemonDetail pokemon) {

                }
            });

            binding.bnNoSort.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent gotoMain = new Intent(FavoriteActivity.this, MainActivity.class);
                    startActivity(gotoMain);
                }
            });



        }

}

