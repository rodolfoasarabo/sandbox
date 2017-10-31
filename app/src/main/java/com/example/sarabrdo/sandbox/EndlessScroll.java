package com.example.sarabrdo.sandbox;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SARABRDO on 31/10/2017.
 */

public class EndlessScroll extends AppCompatActivity {
    private static final int MAX_ITEMS_PER_REQUEST = 10;
    private static final int NUMBER_OF_ITEMS = 100;
    private static final int SIMULATED_LOADING_TIME_IN_MS = 3000;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rvEndless)
    RecyclerView rvEndless;
    private LinearLayoutManager layoutManager;

    private List<String> items;
    private int page;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endless_scroll);
        ButterKnife.bind(this);
        this.items = createItems();
        initRecyclerView();
    }

    private static List<String> createItems() {
        List<String> itemsLocal = new LinkedList<>();
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            String prefix = i < 10 ? "0" : "";
            itemsLocal.add("Item #".concat(prefix).concat(String.valueOf(i)));
        }

        return itemsLocal;
    }

    private void initRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        rvEndless.setHasFixedSize(true);
        rvEndless.setLayoutManager(layoutManager);
        rvEndless.setAdapter(new RecyclerViewAdapter(items.subList(page, MAX_ITEMS_PER_REQUEST)));
        rvEndless.addOnScrollListener(createInfiniteScrollListener());
    }

    @NonNull
    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(MAX_ITEMS_PER_REQUEST, layoutManager) {
            @Override
            public void onScrolledToEnd(int firstVisibleItemPosition) {
                simulateLoading();
                int start = ++page * MAX_ITEMS_PER_REQUEST;
                final boolean allItemsLoaded = start >= items.size();
                if (allItemsLoaded) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    int end = start + MAX_ITEMS_PER_REQUEST;
                    final List<String> itemsLocal = getItemsToBeLoaded(start, end);
                    refreshView(rvEndless, new RecyclerViewAdapter(itemsLocal), firstVisibleItemPosition);
                }
            }
        };
    }

    @NonNull
    private List<String> getItemsToBeLoaded(int start, int end) {
        List<String> newItems = items.subList(start, end);
        final List<String> oldItems = ((RecyclerViewAdapter) rvEndless.getAdapter()).getItems();
        final List<String> itemsLocal = new LinkedList<>();
        itemsLocal.addAll(oldItems);
        itemsLocal.addAll(newItems);
        return itemsLocal;
    }

    private void simulateLoading() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(SIMULATED_LOADING_TIME_IN_MS);
                } catch (InterruptedException e) {
                    Log.e("MainActivity", e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void param) {
                progressBar.setVisibility(View.GONE);
            }
        }.execute();
    }
}
