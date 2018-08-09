package com.example.sarabrdo.sandbox.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sarabrdo.sandbox.models.PokemonModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodolfo on 19/04/2018.
 */

public class PokemonDaoOld {

    private SQLiteDatabase db;
    public static final String TABLE_POKEMON = "pokemon";

    public static final String COLUNA_ID = "id_pokemon";
    public static final String COLUNA_NOME = "nome_pokemon";
    public static final String COLUNA_TIPO_ID = "id_tipo_fk";
    public static final String COLUNA_SEXO_ID = "id_sexo_fk";
    private Context context;

    public static final String CREATE_TABLE_POKEMON = "CREATE TABLE IF NOT EXISTS " +
            TABLE_POKEMON + " (" +
            COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUNA_NOME + " TEXT DEFAULT '', " +
            COLUNA_TIPO_ID + " INTEGER DEFAULT 0, " +
            COLUNA_SEXO_ID + " INTEGER DEFAULT 0" +
            ")";

    public String addPokemon(Context context, String nome, String tipo, String sexo){
        ContentValues values = new ContentValues();
        long result;

        db = CriaBanco.getInstance(context).getWritableDatabase();
        values.put(COLUNA_NOME, nome);
        values.put(COLUNA_TIPO_ID, tipo);
        values.put(COLUNA_SEXO_ID, sexo);

        result = db.insert(TABLE_POKEMON, null, values);

        if (result == -1)
            return "Erro ao inserir o registro";
        else
            return "Registro inserido com sucesso!";
    }

    public List<PokemonModel> carregaDados(){
        List<PokemonModel> listPokemon = new ArrayList<>();
        Cursor cursor;
        String[] campos = {COLUNA_TIPO_ID, COLUNA_NOME, COLUNA_TIPO_ID, COLUNA_SEXO_ID};
        db = CriaBanco.getInstance(context).getReadableDatabase();
        cursor = db.query(TABLE_POKEMON, campos, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
            do{
                PokemonModel pokemon = new PokemonModel();
                pokemon.id = cursor.getInt(cursor.getColumnIndexOrThrow("id_pokemon"));
                pokemon.nome = cursor.getString(cursor.getColumnIndexOrThrow("nome_pokemon"));
                pokemon.tipo = cursor.getString(cursor.getColumnIndexOrThrow("id_tipo_fk"));
                pokemon.sexo = cursor.getString(cursor.getColumnIndexOrThrow("id_sexo_fk"));
                listPokemon.add(pokemon);
            }while (cursor.moveToNext());
        }
        db.close();
        return listPokemon;
    }

    public boolean excluirPokemon(int id){

        String where = COLUNA_ID + " = " + id;
        db = CriaBanco.getInstance(context).getReadableDatabase();
        db.delete(TABLE_POKEMON, where, null);
        db.close();

        return true;
    }
}
