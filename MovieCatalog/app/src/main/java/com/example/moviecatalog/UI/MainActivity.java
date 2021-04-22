package com.example.moviecatalog.UI;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.moviecatalog.data.Api;
import com.example.moviecatalog.data.MoviesCallback;
import com.example.moviecatalog.data.MoviesRepository;
import com.example.moviecatalog.data.RetrofitInstance;
import com.example.moviecatalog.databinding.ActivityMainBinding;
import com.example.moviecatalog.domain.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MoviesAdapter adapter;

    MoviesRepository moviesRepository = new MoviesRepository(RetrofitInstance.getRetrofitInstance().create(Api.class));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        adapter = new MoviesAdapter();
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerView.setAdapter(adapter);

        moviesRepository.getMovies(new MoviesCallback() {
            @Override
            public void onSuccess(List<Movie> list) {
                adapter.setItems(list);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}