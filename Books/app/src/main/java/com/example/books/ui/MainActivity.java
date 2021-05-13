package com.example.books.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.books.R;
import com.example.books.data.Api;
import com.example.books.data.BooksCallback;
import com.example.books.data.BooksRepository;
import com.example.books.data.RetrofitInstance;
import com.example.books.databinding.ActivityMainBinding;
import com.example.books.domain.Books;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    BooksRepository booksRepository =
            new BooksRepository(
                    RetrofitInstance.getRetrofitInstance().create(Api.class)
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        booksRepository.getBooks(new BooksCallback() {
            @Override
            public void onSuccess(List<Books> list) {
                for (Books books:list) {
                    Log.e("onSuccess", Books.name);
                }

            }
            @Override
            public void onError(String errorMessage) {
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}