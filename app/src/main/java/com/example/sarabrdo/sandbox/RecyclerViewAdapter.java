package com.example.sarabrdo.sandbox;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by SARABRDO on 31/10/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<String> items;

    public RecyclerViewAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.listitem_endless_scroll, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String itemText = items.get(position);
        holder.idTeste.setText(itemText);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<String> getItems() {
        return items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView idTeste;

        public ViewHolder(View itemView) {
            super(itemView);
            idTeste = itemView.findViewById(R.id.idTeste);
        }
    }
}
