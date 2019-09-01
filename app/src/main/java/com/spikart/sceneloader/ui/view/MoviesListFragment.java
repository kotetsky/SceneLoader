package com.spikart.sceneloader.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spikart.sceneloader.R;
import com.spikart.sceneloader.domain.Movie;
import com.spikart.sceneloader.ui.listener.ItemClickListener;
import com.spikart.sceneloader.ui.adapter.MoviePageListAdapter;
import com.spikart.sceneloader.viewmodel.MovieDetailsViewModel;
import com.spikart.sceneloader.viewmodel.MoviesListViewModel;

public class MoviesListFragment extends Fragment implements ItemClickListener {

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
        final MoviePageListAdapter pageListAdapter = new MoviePageListAdapter(this);
        viewModel.getMovies().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                pageListAdapter.submitList(movies);
            }
        });
        viewModel.getNetworkState().observe(this, networkState -> {
            pageListAdapter.setNetworkState(networkState);
        });

        recyclerView.setAdapter(pageListAdapter);
        detailsViewModel = ViewModelProviders.of(getActivity()).get(MovieDetailsViewModel.class);
    }

    @Override
    public void OnItemClick(Movie movie) {
        detailsViewModel.getMovie().postValue(movie);
        if (!detailsViewModel.getMovie().hasActiveObservers()) {

            // Create fragment and give it an argument specifying the article it should show
            MovieDetailsFragment detailsFragment = new MovieDetailsFragment();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            fragmentTransaction.replace(R.id.fragmentsContainer, detailsFragment);
            fragmentTransaction.addToBackStack(null);

            // Commit the transaction
            fragmentTransaction.commit();
        }
    }
}
