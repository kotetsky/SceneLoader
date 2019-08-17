package com.spikart.sceneloader.repository.storage.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.spikart.sceneloader.domain.Movie;
import com.spikart.sceneloader.repository.storage.dao.MoviesDao;

import java.util.List;

public class DBMoviesPageKeyedDataSource extends PageKeyedDataSource<String, Movie> {

    public static final String TAG = DBMoviesPageKeyedDataSource.class.getSimpleName();

    private final MoviesDao moviesDao;

    public DBMoviesPageKeyedDataSource(MoviesDao dao) {
        moviesDao = dao;
    }


    @Override
    public void loadInitial(
            @NonNull LoadInitialParams<String> params,
            @NonNull LoadInitialCallback<String, Movie> callback) {
        Log.d(TAG, "loadInitial params.requestedLoadSize = " + params.requestedLoadSize);
        List<Movie> movies = moviesDao.getMovies();
        if (movies.size() != 0) {
            callback.onResult(movies, "0", "1");
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {
        // no implementation needed
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {
        // no implementation needed
    }
}
