package com.example.pokedex.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.UI.viewholders.FavoriteViewModel;
import com.example.pokedex.data.api.ApiPokeDetail;
import com.example.pokedex.data.favorites.FavoriteList;
import com.example.pokedex.data.pokedetail.PokeDetailCallback;
import com.example.pokedex.data.pokedetail.PokeDetailRepository;
import com.example.pokedex.data.RetrofitInstance;
import com.example.pokedex.databinding.ActivityDetailBinding;
import com.example.pokedex.domain.PokemonDetail;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    ImageButton backButton;
    Button favButton;
    FavoriteViewModel viewModel;

    public String pokemonId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();
        pokemonId = i.getStringExtra("displayedId");
        Log.d("displayedIdDetail", pokemonId);


        backButton = findViewById(R.id.bnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
            }
        });

        viewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);

        favButton = findViewById(R.id.bnFavorito);
        favButton.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v) {
             if (v==favButton){
                 FavoriteList pokemon = null;
                 viewModel.deletePokemon(pokemon.getName());
                 MainActivity.favoriteDatabase.favoriteDao().deletePokemon(pokemon.getName());
                 Toast.makeText(DetailActivity.this,"Pokemon removed from favorites.",Toast.LENGTH_SHORT).show();             }else {

             }
         }
        });





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
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(DetailActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        }, pokemonId);



    }


    PokeDetailRepository pokeDetailRepository = new PokeDetailRepository(RetrofitInstance.getRetrofitInstance().create(ApiPokeDetail.class));

}