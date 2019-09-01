package com.spikart.sceneloader.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.spikart.sceneloader.domain.Movie;
import com.spikart.sceneloader.domain.NetworkState;
import com.spikart.sceneloader.repository.MoviesRepository;

public class MoviesListViewModel extends AndroidViewModel {

    private MoviesRepository repository;

    public MoviesListViewModel(@NonNull Application application) {
        super(application);
        repository = MoviesRepository.getInstance(application);
    }

    public LiveData<PagedList<Movie>> getMovies() {
        return repository.getMergedMovies();
    }

    public LiveData<NetworkState> getNetworkState() {
        return repository.getNetworkState();
    }
}
