package com.spikart.sceneloader.repository.storage.paging;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.spikart.sceneloader.domain.Movie;
import com.spikart.sceneloader.repository.storage.dao.MoviesDao;

public class DBMoviesDataSourceFactory extends DataSource.Factory {

    private static final String TAG = DBMoviesDataSourceFactory.class.getSimpleName();

    private DBMoviesPageKeyedDataSource moviesPageKeyedDataSource;

    public DBMoviesDataSourceFactory(MoviesDao moviesDao) {
        moviesPageKeyedDataSource = new DBMoviesPageKeyedDataSource(moviesDao);
    }


    @Override
    @NonNull
    public DataSource create() {
        return moviesPageKeyedDataSource;
    }
}
