package com.example.pokedex.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.data.api.ApiPokeDetail;
import com.example.pokedex.data.callback.PokeDetailCallback;
import com.example.pokedex.data.database.DataBaseInstance;
import com.example.pokedex.data.repositories.PokeDetailRepository;
import com.example.pokedex.data.RetrofitInstance;
import com.example.pokedex.data.repositories.PokemonsRepository;
import com.example.pokedex.databinding.ActivityDetailBinding;
import com.example.pokedex.domain.PokemonDetail;
import com.example.pokedex.domain.Pokemons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    ExecutorService executorService = Executors.newFixedThreadPool(4);
    Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());


    public String pokemonId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();
        pokemonId = i.getStringExtra("displayedId");
        Log.d("displayedIdDetail", pokemonId);


        binding.bnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
            }
        });


        PokeDetailRepository pokeDetailRepository = new PokeDetailRepository(
                RetrofitInstance.getRetrofitInstance().create(ApiPokeDetail.class)
                ,executorService,mainThreadHandler,
                DataBaseInstance.getRetrofitInstance(getApplicationContext()).favDao());

        pokeDetailRepository.getPokeDetails(new PokeDetailCallback() {

            @Override
            public void onSuccess(PokemonDetail detail) {
                Glide.with(DetailActivity.this)
                        .load(detail.getImageUrl())
                        .into(binding.ivPokemon);

                binding.tvNombre.setText(detail.name);
                binding.tvAltura.setText(
                        getString(R.string.label_height, detail.height));
                binding.tvPeso.setText(
                        getString(R.string.label_weight, detail.weight));
                binding.tvExperiencia.setText(
                        getString(R.string.label_xp, detail.base_experience));
                binding.tvId.setText(
                        getString(R.string.label_id, detail.id));

                binding.bnAddFav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pokeDetailRepository.addPokemonToFav(detail);
                        String toastPokemonAdd = "Se agregó a " + detail.name + " a tus favoritos";
                        Toast.makeText(DetailActivity.this, toastPokemonAdd , Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(DetailActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDataBaseResponse(PokemonDetail pokemon) {

            }

        }, pokemonId);

    }


}