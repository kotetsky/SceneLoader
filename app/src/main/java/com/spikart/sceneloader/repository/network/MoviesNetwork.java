package com.spikart.sceneloader.repository.network;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.spikart.sceneloader.domain.Movie;
import com.spikart.sceneloader.domain.NetworkState;
import com.spikart.sceneloader.repository.network.paging.NetMoviesDataSourceFactory;
import com.spikart.sceneloader.repository.network.paging.NetMoviesPageKeyedDataSource;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MoviesNetwork {

    final private static String TAG = MoviesNetwork.class.getSimpleName();

    private static final int LOADING_PAGE_SIZE = 20;
    private static final int NUMBERS_OF_THREADS = 3;

    final private LiveData<PagedList<Movie>> moviesPaged;
    final private LiveData<NetworkState> networkStateLiveData;

    public MoviesNetwork(
            NetMoviesDataSourceFactory<String, Movie> dataSourceFactory,
            PagedList.BoundaryCallback<Movie> boundaryCallback
    ) {
        PagedList.Config pagedListConfig = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(LOADING_PAGE_SIZE)
                .setPageSize(LOADING_PAGE_SIZE)
                .build();

        networkStateLiveData = Transformations.switchMap(
                dataSourceFactory.getNetworkStatus(),
                (Function<NetMoviesPageKeyedDataSource, LiveData<NetworkState>>)
                        NetMoviesPageKeyedDataSource::getNetworkState
        );

        Executor executor = Executors.newFixedThreadPool(NUMBERS_OF_THREADS);
        LivePagedListBuilder<String, Movie> livePagedListBuilder =
                new LivePagedListBuilder<>(dataSourceFactory, pagedListConfig);

        moviesPaged = livePagedListBuilder
                .setFetchExecutor(executor)
                .setBoundaryCallback(boundaryCallback)
                .build();
    }

    public LiveData<PagedList<Movie>> getMoviesPaged() {
        return moviesPaged;
    }

    public LiveData<NetworkState> getNetworkState() {
        return networkStateLiveData;
    }

}
