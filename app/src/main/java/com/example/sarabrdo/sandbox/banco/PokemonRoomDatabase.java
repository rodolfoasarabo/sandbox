package com.example.sarabrdo.sandbox.banco;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.sarabrdo.sandbox.entity.Pokemon;

@Database(entities = {Pokemon.class}, version = 1)
public abstract class PokemonRoomDatabase extends RoomDatabase {
    public abstract PokemonDao pokemonDao();

    private static PokemonRoomDatabase instance;

    static PokemonRoomDatabase getDatabase(final Context context){
        if (instance == null){
            synchronized (PokemonRoomDatabase.class) {
                if (instance == null){
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            PokemonRoomDatabase.class,
                            "pokemon_database")
                            .build();
                }
            }
        }

        return instance;
    }

}
