package com.example.sarabrdo.sandbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarabrdo.sandbox.banco.PokemonDaoOld;
import com.example.sarabrdo.sandbox.entity.Pokemon;
import com.example.sarabrdo.sandbox.models.PokemonModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rodolfo on 18/04/2018.
 */

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonAdapterHolder> {

    private List<Pokemon> pokemons;
    private OnItemClickListener clickListener;
    private OnItemRemoved itemRemoved;
    private PokemonDaoOld crud;


    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public PokemonAdapter(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    @Override
    public PokemonAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_pokemon, parent, false);
        return new PokemonAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonAdapterHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.lblNomeValor.setText(pokemon.getPokemon_name());
        holder.lblTipoValor.setText(pokemon.getTipo());
        holder.lblSexoValor.setText(pokemon.getSexo());

    }
    public void setPokemons(List<Pokemon> pokemons){
        this.pokemons = pokemons;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return pokemons != null ? pokemons.size() : 0;
    }

    public List<Pokemon> getItems(){
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
//                    crud.excluirPokemon(pokemonsOld.get(getAdapterPosition()).id);
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
        void onClick(View view, int position, Pokemon pokemonModel);
    }

}
