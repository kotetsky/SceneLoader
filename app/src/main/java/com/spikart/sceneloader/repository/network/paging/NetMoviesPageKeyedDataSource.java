package com.spikart.sceneloader.repository.network.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.spikart.sceneloader.domain.Movie;

import rx.subjects.ReplaySubject;

public class NetMoviesPageKeyedDataSource extends PageKeyedDataSource<String, Movie> {

    private final static String TAG = NetMoviesPageKeyedDataSource.class.getSimpleName();

    private final MoviesApiInterface moviesApiInterface;
    private final MutableLiveData networkState;

    private final ReplaySubject<Movie> moviesSubject;

    public NetMoviesPageKeyedDataSource() {
        moviesApiInterface = TheMovieDBAPIClient.getClient();

    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Movie> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {

    }
}
