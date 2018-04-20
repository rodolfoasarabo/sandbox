package com.example.sarabrdo.sandbox.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Rodolfo on 18/04/2018.
 */

public class CriaBanco extends SQLiteOpenHelper {

    private static final String TAG = CriaBanco.class.getSimpleName();
    public static final String NOME_BANCO = "pokemon.db";
    private static final int versao = 2;
    private Context context;
    private static CriaBanco singleton;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, versao);
        this.context = context;
        Log.d(TAG, "Uma instancia da classe foi criada");
    }

    public static CriaBanco getInstance(Context context){
        if (singleton == null){
            singleton = new CriaBanco(context);
        }

        return singleton;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            CriacaoDeTabelas.executarScript(sqLiteDatabase, context);
        } catch (Exception ex){
            throw new RuntimeException("Um problema aconteceu ao tentar criar as tabelas do banco de dados...", ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NOME_BANCO);
        onCreate(sqLiteDatabase);
    }
}
