package com.example.moviefetcher.ui.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.moviefetcher.domain.Movie;

public class MoviePageListAdapter extends PagedListAdapter<Movie, RecyclerView.ViewHolder> {


    public MoviePageListAdapter(Context context) {
        super(Movie.);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }
}
