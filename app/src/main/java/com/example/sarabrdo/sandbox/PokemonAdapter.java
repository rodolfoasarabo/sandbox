package com.example.sarabrdo.sandbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarabrdo.sandbox.banco.PokemonController;
import com.example.sarabrdo.sandbox.banco.PokemonDao;
import com.example.sarabrdo.sandbox.models.PokemonModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rodolfo on 18/04/2018.
 */

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonAdapterHolder> {

    private final List<PokemonModel> pokemons;
    private OnItemClickListener clickListener;
    private OnItemRemoved itemRemoved;
    private PokemonDao crud;


    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public PokemonAdapter(List<PokemonModel> pokemons, PokemonDao crud, OnItemRemoved itemRemoved) {
        this.pokemons = pokemons;
        this.crud = crud;
        this.itemRemoved = itemRemoved;
    }

    @Override
    public PokemonAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_pokemon, parent, false);
        return new PokemonAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonAdapterHolder holder, int position) {
        PokemonModel pokemon = pokemons.get(position);
        holder.lblNomeValor.setText(pokemon.nome);
        holder.lblTipoValor.setText(pokemon.tipo);
        holder.lblSexoValor.setText(pokemon.sexo);

    }

    @Override
    public int getItemCount() {
        return pokemons != null ? pokemons.size() : 0;
    }

    public List<PokemonModel> getItems(){
        return pokemons;
    }

    public class PokemonAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @BindView(R.id.lblNomeValor)
        TextView lblNomeValor;
        @BindView(R.id.lblTipoValor)
        TextView lblTipoValor;
        @BindView(R.id.lblSexoValor)
        TextView lblSexoValor;


        public PokemonAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAdapterPosition(), pokemons.get(getAdapterPosition()));
        }

        @Override
        public boolean onLongClick(final View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("AVISO");
            builder.setMessage("Deseja excluir?");
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    crud.excluirPokemon(pokemons.get(getAdapterPosition()).id);
                    itemRemoved.removed(getAdapterPosition());
                    dialogInterface.dismiss();
                }
            });
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(view.getContext(), "NEGATIVO", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }
    }

    public interface OnItemRemoved {
        void removed(int position);
    }

    public interface OnItemClickListener {
        void onClick(View view, int position, PokemonModel pokemonModel);
    }

}
