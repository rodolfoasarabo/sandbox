package com.example.sarabrdo.sandbox.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Rodolfo on 19/04/2018.
 */

public class CriacaoDeTabelas {
    public static String TAG = CriacaoDeTabelas.class.getSimpleName();

    public static void executarScript(SQLiteDatabase db, Context ctx){

        db.execSQL(PokemonDaoOld.CREATE_TABLE_POKEMON);
        Log.d(TAG, "Tabela Criada");

    }

}
