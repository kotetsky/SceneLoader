package com.spikart.sceneloader.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.paging.PagedList;

import com.spikart.sceneloader.domain.Movie;
import com.spikart.sceneloader.domain.NetworkState;
import com.spikart.sceneloader.repository.network.MoviesNetwork;
import com.spikart.sceneloader.repository.network.paging.NetMoviesDataSourceFactory;
import com.spikart.sceneloader.repository.storage.MoviesDatabase;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public class MoviesRepository {

    private final static String TAG = MoviesRepository.class.getSimpleName();

    private static MoviesRepository instance;
    private final MoviesNetwork network;
    private final MoviesDatabase database;
    private final MediatorLiveData<PagedList<Movie>> liveDataMerger;

    private MoviesRepository(Context context) {

        NetMoviesDataSourceFactory<String, Movie> dataSourceFactory = new NetMoviesDataSourceFactory<>();

        network = new MoviesNetwork(dataSourceFactory, boundaryCallback);
        database = MoviesDatabase.getInstance(context.getApplicationContext());

        liveDataMerger = new MediatorLiveData<PagedList<Movie>>();
        liveDataMerger.addSource(
                network.getMoviesPaged(),
                value -> {
                    Log.d(TAG, value.toString());
                    liveDataMerger.setValue(value);
                }
        );

        dataSourceFactory
                .getMovies()
                .observeOn(Schedulers.io())
                .subscribe(movie -> {
                    database.moviesDao().insertMovie(movie);
                        }
                );
    }

    private PagedList.BoundaryCallback<Movie> boundaryCallback = new PagedList.BoundaryCallback<Movie>() {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
            liveDataMerger.addSource(database.getMovies(), value -> {
                liveDataMerger.setValue(value);
                liveDataMerger.removeSource(database.getMovies());
            });
        }
    };

    public static MoviesRepository getInstance(Context context) {
        if (instance == null) {
            instance = new MoviesRepository(context);
        }
        return instance;
    }

    public LiveData<PagedList<Movie>> getMergedMovies() {
        return liveDataMerger;
    }

    public LiveData<NetworkState> getNetworkState () {
        return network.getNetworkState();
    }
}
