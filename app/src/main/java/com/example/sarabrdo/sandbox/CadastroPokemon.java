package com.example.sarabrdo.sandbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sarabrdo.sandbox.banco.PokemonController;
import com.example.sarabrdo.sandbox.banco.PokemonDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rodolfo on 18/04/2018.
 */

public class CadastroPokemon extends AppCompatActivity {

    @BindView(R.id.edtNome)
    EditText edtNome;
    @BindView(R.id.tilNome)
    TextInputLayout tilNome;
    @BindView(R.id.edtTipo)
    EditText edtTipo;
    @BindView(R.id.tilTipo)
    TextInputLayout tilTipo;
    @BindView(R.id.viewClickTipo)
    View viewClickTipo;
    @BindView(R.id.edtSexo)
    EditText edtSexo;
    @BindView(R.id.tilSexo)
    TextInputLayout tilSexo;
    @BindView(R.id.viewClickSexo)
    View viewClickSexo;
    @BindView(R.id.btnSalvar)
    Button btnSalvar;
    @BindView(R.id.btnArrowTipo)
    ImageView btnArrowTipo;
    @BindView(R.id.btnArrowSexo)
    ImageView btnArrowSexo;

    PopupMenu popupSexo;
    PopupMenu popupTipo;
    List<String> tipos = new ArrayList<>();
    List<String> sexos = new ArrayList<>();

    String tipo, sexo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.cadastrar_pokemon);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
        popupSexo = new PopupMenu(this, btnArrowSexo);
        popupTipo = new PopupMenu(this, btnArrowTipo);

        tipos.add("Fogo");
        tipos.add("Agua");
        tipos.add("Inseto");
        tipos.add("Planta");

        sexos.add("Masculino");
        sexos.add("Feminino");
        sexos.add("Outros");

        popupTipo.getMenu().clear();
        for (int i = 0; i < tipos.size(); i++){
            popupTipo.getMenu().add(i, i, i, tipos.get(i));
        }
        popupSexo.getMenu().clear();
        for (int i = 0; i < sexos.size(); i++){
            popupSexo.getMenu().add(i, i, i, sexos.get(i));
        }

        popupTipo.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                edtTipo.setText(item.getTitle());
                tipo = String.valueOf(item.getTitle());
                return true;
            }
        });

        popupSexo.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                edtSexo.setText(item.getTitle());
                sexo = String.valueOf(item.getTitle());
                return true;
            }
        });



    }

    @OnClick(R.id.viewClickTipo)
    public void selecionaTipo(){
        popupTipo.show();
        popupTipo.setGravity(Gravity.END);
    }

    @OnClick(R.id.viewClickSexo)
    public void selecionaSexo(){
        popupSexo.show();
        popupSexo.setGravity(Gravity.END);
    }

    @OnClick(R.id.btnSalvar)
    public void salvarPokemon(){
        PokemonDao crud = new PokemonDao();
        String nome = edtNome.getText().toString();
        String result;

        result = crud.addPokemon(this, nome, tipo, sexo);

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        finish();
    }

}
