package com.example.sarabrdo.sandbox.banco;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sarabrdo.sandbox.entity.Pokemon;

import java.util.List;

@Dao
public interface PokemonDao {

    @Insert
    void insert(Pokemon pokemon);

    @Query("DELETE FROM pokemon_table")
    void deleteAll();

    @Query("DELETE FROM pokemon_table WHERE pokemon_name = :name")
    void delete(String name);

    @Query("SELECT * FROM pokemon_table ORDER BY pokemon_name")
    LiveData<List<Pokemon>> getAllPokemons();


}
