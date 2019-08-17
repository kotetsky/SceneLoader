package com.spikart.sceneloader.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spikart.sceneloader.R;
import com.spikart.sceneloader.ui.adapter.MoviePageListAdapter;
import com.spikart.sceneloader.viewmodel.MovieDetailsViewModel;
import com.spikart.sceneloader.viewmodel.MoviesListViewModel;

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
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        viewModel = ViewModelProviders.of(getActivity()).get(MoviesListViewModel.class);

        registerObservers();
        return view;
    }

    private void registerObservers() {
        final MoviePageListAdapter pageListAdapter = new MoviePageListAdapter(this.getContext());
        // todo Aerol write code
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // todo aerol write code here
    }
}
