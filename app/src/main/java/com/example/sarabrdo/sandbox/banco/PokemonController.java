package com.example.sarabrdo.sandbox.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sarabrdo.sandbox.Pokemon;
import com.example.sarabrdo.sandbox.models.PokemonModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodolfo on 18/04/2018.
 */

public class PokemonController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public PokemonController(Context context){
        banco = new CriaBanco(context);
    }

//    public String addPokemon(String nome, String tipo, String sexo){
//        ContentValues values = new ContentValues();
//        long result;
//
//        db = banco.getWritableDatabase();
//        values.put(CriaBanco.NOME, nome);
//        values.put(CriaBanco.TIPO, tipo);
//        values.put(CriaBanco.SEXO, sexo);
//
//        result = db.insert(CriaBanco.TABELA, null, values);
//
//        if (result == -1)
//            return "Erro ao inserir o registro";
//        else
//            return "Registro inserido com sucesso!";
//    }

//    public List<PokemonModel> carregaDados(){
//        List<PokemonModel> listPokemon = new ArrayList<>();
//        Cursor cursor;
//        String[] campos = {banco.ID, banco.NOME, banco.TIPO, banco.SEXO};
//        db = banco.getReadableDatabase();
//        cursor = db.query(banco.TABELA, campos, null, null, null, null, null);
//        if (cursor != null){
//            cursor.moveToFirst();
//            do{
//                PokemonModel pokemon = new PokemonModel();
//                pokemon.id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
//                pokemon.nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
//                pokemon.tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo"));
//                pokemon.sexo = cursor.getString(cursor.getColumnIndexOrThrow("sexo"));
//                listPokemon.add(pokemon);
//            }while (cursor.moveToNext());
//        }
//        db.close();
//        return listPokemon;
//    }

//    public boolean excluirPokemon(int id){
//
//        String where = CriaBanco.ID + " = " + id;
//        db = banco.getReadableDatabase();
//        db.delete(CriaBanco.TABELA, where, null);
//        db.close();
//
//        return true;
//    }

}
