package com.example.sarabrdo.sandbox.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.sarabrdo.sandbox.banco.PokemonRepository;
import com.example.sarabrdo.sandbox.entity.Pokemon;

import java.util.List;

public class PokemonViewModel extends AndroidViewModel {

    private PokemonRepository repository;
    private LiveData<List<Pokemon>> allPokemons;

    public PokemonViewModel(Application application) {
        super(application);
        repository = new PokemonRepository(application);
        allPokemons = repository.getAllPokemons();
    }

    public LiveData<List<Pokemon>> getAllPokemons() {
        return allPokemons;
    }

    public void insert(Pokemon pokemon){
        repository.insert(pokemon);
    }
}
