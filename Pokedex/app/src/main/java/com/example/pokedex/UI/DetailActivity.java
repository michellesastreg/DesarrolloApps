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

import com.example.pokedex.R;
import com.example.pokedex.data.ApiPokeDetail;
import com.example.pokedex.data.PokeDetailCallback;
import com.example.pokedex.data.RetrofitInstance;
import com.example.pokedex.databinding.ActivityDetailBinding;
import com.example.pokedex.domain.PokemonDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    PokeDetailAdapter adapter;
    ImageButton button;


    public class PokeDetailRepository extends MainActivity{

        private ApiPokeDetail api;
        public String idGlobal;


        public PokeDetailRepository(ApiPokeDetail api){
            this.api = api;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Intent i = getIntent();
            String id = i.getStringExtra("id");
            idGlobal = id;
        }


        public void getPokeDetail(PokeDetailCallback callback) {
            Call<PokemonDetail> pokemonDetailCall = api.getDetail(idGlobal);
            pokemonDetailCall.enqueue(new Callback<PokemonDetail>(){
                @Override
                public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess((List<PokemonDetail>) response.body());

                    } else {
                        callback.onError("Hubo un error!");
                    }
                }
                @Override
                public void onFailure(Call<PokemonDetail>   call, Throwable t) {
                    callback.onError("Hubo un error!");
                }
            });
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new PokeDetailAdapter();

        button = findViewById(R.id.bnBack);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));

            }
        });

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