package com.example.pokedex.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pokedex.R;
import com.example.pokedex.UI.adapters.FavoriteAdapter;
import com.example.pokedex.UI.adapters.PokemonAdapter;
import com.example.pokedex.UI.viewholders.FavoriteViewModel;
import com.example.pokedex.data.favorites.FavoriteList;
import com.example.pokedex.databinding.ActivityFavoritesBinding;

import java.util.ArrayList;
import java.util.List;


public class Favorites extends Fragment {
    private ActivityFavoritesBinding binding;
    private FavoriteViewModel viewModel;
    private FavoriteAdapter adapter;
    private ArrayList<FavoriteList> pokemonList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityFavoritesBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);

        initRecyclerView();
        setUpItemTouchHelper();
        observeData();
        //viewModel.getFavoritePokemon();
    }

    private void observeData() {
        viewModel.getFavoritePokemonList().observe(getViewLifecycleOwner(), new Observer<List<FavoriteList>>() {
            @Override
            public void onChanged(List<FavoriteList> pokemons) {

                if(pokemons == null || pokemons.size() == 0)
                    binding.noFavoritesText.setVisibility(View.VISIBLE);
                else{
                    ArrayList<FavoriteList> list = new ArrayList<>();
                    list.addAll(pokemons);
                    adapter.updateList(list);
                }
            }
        });
    }

    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPokemonPosition = viewHolder.getAdapterPosition();
                FavoriteList pokemon = adapter.getPokemonAt(swipedPokemonPosition);
                viewModel.deletePokemon(pokemon.getName());
                Toast.makeText(getContext(),"Pokemon removed from favorites.",Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.favoritesRecyclerView);
    }


    private void initRecyclerView() {
        binding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FavoriteAdapter(getContext(),pokemonList);
        binding.favoritesRecyclerView.setAdapter(adapter);
    }

}
