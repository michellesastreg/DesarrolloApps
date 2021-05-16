package com.example.pokedex.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.data.ApiPokeDetail;
import com.example.pokedex.data.PokeDetailCallback;
import com.example.pokedex.data.PokeDetailRepository;
import com.example.pokedex.data.RetrofitInstance;
import com.example.pokedex.databinding.ActivityDetailBinding;
import com.example.pokedex.domain.PokemonDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    ImageButton button;

    PokemonDetail detail;
    public String pokemonId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();
        pokemonId = i.getStringExtra("displayedId");
        Log.d("displayedIdDetail", pokemonId);


        button = findViewById(R.id.bnBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
            }
        });



        pokeDetailRepository.getPokeDetails(new PokeDetailCallback() {

            @Override
            public void onSuccess(PokemonDetail pokemonDetail) {
                binding.tvNombre.setText(detail.name);
                binding.tvExperiencia.setText(detail.base_experience);
                binding.tvPeso.setText(detail.weight);
                binding.tvAltura.setText(detail.height);
                binding.tvId.setText(detail.id);

            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(DetailActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });

    }


    PokeDetailRepository pokeDetailRepository = new PokeDetailRepository(RetrofitInstance.getRetrofitInstance().create(ApiPokeDetail.class));

}