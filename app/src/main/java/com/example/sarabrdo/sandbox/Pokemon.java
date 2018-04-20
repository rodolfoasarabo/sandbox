package com.example.sarabrdo.sandbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rodolfo on 18/04/2018.
 */

public class Pokemon extends AppCompatActivity {

    @BindView(R.id.btnCadastro)
    Button btnCadastro;
    @BindView(R.id.btnLista)
    Button btnLista;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.main_pokemon);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.btnCadastro, R.id.btnLista})
    public void onViewClicked(View view) {
        Intent i = null;
        switch (view.getId()) {
            case R.id.btnCadastro:
                i = new Intent(Pokemon.this, CadastroPokemon.class);
                break;
            case R.id.btnLista:
                i = new Intent(Pokemon.this, ListaPokemon.class);
                break;
        }
        startActivity(i);
    }
}
