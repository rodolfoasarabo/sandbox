package com.example.sarabrdo.sandbox.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "pokemon_table")
public class Pokemon {

    @PrimaryKey
    @NonNull
    private String pokemon_name;
    private String tipo;
    private String sexo;

    public Pokemon(@NonNull String pokemon_name, String tipo, String sexo) {
        this.pokemon_name = pokemon_name;
        this.tipo = tipo;
        this.sexo = sexo;
    }

    @NonNull
    public String getPokemon_name() {
        return pokemon_name;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSexo() {
        return sexo;
    }
}
