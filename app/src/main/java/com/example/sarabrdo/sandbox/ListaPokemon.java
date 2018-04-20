package com.example.sarabrdo.sandbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.sarabrdo.sandbox.banco.PokemonController;
import com.example.sarabrdo.sandbox.banco.PokemonDao;
import com.example.sarabrdo.sandbox.models.PokemonModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rodolfo on 18/04/2018.
 */

public class ListaPokemon extends AppCompatActivity implements PokemonAdapter.OnItemRemoved{

    @BindView(R.id.rvPokemons)
    RecyclerView rvPokemons;

    PokemonAdapter mAdapter;
    private LinearLayoutManager layoutManager;
    PokemonDao crud = new PokemonDao();
    List<PokemonModel> pokemons = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.lista_pokemon);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
        pokemons = crud.carregaDados();
        if (pokemons != null)
            setupRecycler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupRecycler();
    }

    private void setupRecycler(){
        if (mAdapter == null) {
            rvPokemons.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvPokemons.setLayoutManager(layoutManager);
            mAdapter = new PokemonAdapter(pokemons, crud, this);
            rvPokemons.setAdapter(mAdapter);
            rvPokemons.getViewTreeObserver().addOnPreDrawListener(
                    new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            rvPokemons.getViewTreeObserver().removeOnPreDrawListener(this);

                            for (int i = 0; i < rvPokemons.getChildCount(); i++) {
                                View v = rvPokemons.getChildAt(i);
                                v.setAlpha(0.0f);
                                v.animate().alpha(1.0f)
                                        .setDuration(300)
                                        .setStartDelay(i * 50)
                                        .start();
                            }
                            return true;
                        }
                    });
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void removed(int position) {
        pokemons.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
