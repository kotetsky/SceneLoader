package com.spikart.sceneloader.repository.network.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.spikart.sceneloader.domain.Movie;

import rx.subjects.ReplaySubject;

public class NetMoviesDataSourceFactory<K,V> extends DataSource.Factory {

    private static final String TAG = NetMoviesDataSourceFactory.class.getSimpleName();

    private MutableLiveData<NetMoviesPageKeyedDataSource> networkStatus;
    private NetMoviesPageKeyedDataSource moviesPageKeyedDataSource;

    public NetMoviesDataSourceFactory() {
        this.networkStatus = new MutableLiveData<>();
        moviesPageKeyedDataSource = new NetMoviesPageKeyedDataSource();
    }

    @NonNull
    @Override
    public DataSource create() {
        networkStatus.postValue(moviesPageKeyedDataSource);
        return moviesPageKeyedDataSource;
    }

    public MutableLiveData<NetMoviesPageKeyedDataSource> getNetworkStatus() {
        return networkStatus;
    }

    public ReplaySubject<Movie> getMovies() {
        return moviesPageKeyedDataSource.getMovies();
    }
}
