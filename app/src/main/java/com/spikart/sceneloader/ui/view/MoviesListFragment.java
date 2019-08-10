package com.example.moviefetcher.ui.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.moviefetcher.R;
import com.example.moviefetcher.ui.adapter.MoviePageListAdapter;
import com.example.moviefetcher.viewmodel.MovieDetailsViewModel;
import com.example.moviefetcher.viewmodel.MoviesListViewModel;

public class MoviesListFragment extends Fragment implements AdapterView.OnItemClickListener {

    protected MoviesListViewModel viewModel;
    private MovieDetailsViewModel detailsViewModel;

    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView = view.findViewById(R.id.moviesRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        viewModel = ViewModelProviders.of(getActivity()).get(MovieDetailsViewModel.class));

        registerObservers();
        return view;
    }

    private void registerObservers() {
        final MoviePageListAdapter pageListAdapter = new MoviePageListAdapter(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
