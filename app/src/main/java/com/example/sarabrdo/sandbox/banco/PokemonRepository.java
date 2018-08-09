package com.example.sarabrdo.sandbox.banco;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.sarabrdo.sandbox.entity.Pokemon;

import java.util.List;

public class PokemonRepository {

    private PokemonDao pokemonDao;
    private LiveData<List<Pokemon>> allPokemons;

    public PokemonRepository(Application application){
        PokemonRoomDatabase db = PokemonRoomDatabase.getDatabase(application);
        pokemonDao = db.pokemonDao();
        allPokemons = pokemonDao.getAllPokemons();
    }

    public LiveData<List<Pokemon>> getAllPokemons() {
        return allPokemons;
    }

    public void insert (Pokemon pokemon){
        new insertAsyncTask(pokemonDao).execute(pokemon);
    }

    private static class insertAsyncTask extends AsyncTask<Pokemon, Void, Void> {

        private PokemonDao asyncTaskDao;

        insertAsyncTask(PokemonDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Pokemon... pokemons) {
            asyncTaskDao.insert(pokemons[0]);
            return null;
        }
    }
}
