package com.example.bookcatalog.UI;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookcatalog.data.Api;
import com.example.bookcatalog.data.BooksCallback;
import com.example.bookcatalog.data.BooksRepository;
import com.example.bookcatalog.data.RetrofitInstance;
import com.example.bookcatalog.databinding.ActivityMainBinding;
import com.example.bookcatalog.domain.Book;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    BooksAdapter adapter;

    BooksRepository booksRepository = new BooksRepository(RetrofitInstance.getRetrofitInstance().create(Api.class));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new BooksAdapter();
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        binding.recyclerView.setAdapter(adapter);

        booksRepository.getBooks(new BooksCallback() {
            @Override
            public void onSuccess(List<Book> list) {
                adapter.setItems(list);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}