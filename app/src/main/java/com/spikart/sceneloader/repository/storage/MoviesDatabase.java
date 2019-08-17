package com.spikart.sceneloader.repository.storage;


import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.spikart.sceneloader.domain.Movie;
import com.spikart.sceneloader.repository.storage.dao.MoviesDao;
import com.spikart.sceneloader.repository.storage.paging.DBMoviesDataSourceFactory;

import java.lang.reflect.Executable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {Movie.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {

    private static final String DATA_BASE_NAME = "scenedatabase.db";
    private static final int NUMBERS_OF_THREAD = 3;

    private static MoviesDatabase instance;

    public abstract MoviesDao moviesDao();

    private static final Object lock = new Object();

    private LiveData<PagedList<Movie>> moviesPaged;

    public static MoviesDatabase getInstance(Context context) {
        synchronized (lock) {
            if (instance == null) {
                instance = Room
                        .databaseBuilder(
                                context.getApplicationContext(),
                                MoviesDatabase.class,
                                DATA_BASE_NAME)
                        .build();
            }
            return instance;
        }
    }

    private void init() {
        PagedList.Config pageListConfig = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(Integer.MAX_VALUE)
                .setPageSize(Integer.MAX_VALUE)
                .build();

        Executor executor = Executors.newFixedThreadPool(NUMBERS_OF_THREAD);
        DBMoviesDataSourceFactory dataSourceFactory = new DBMoviesDataSourceFactory(moviesDao());

    }

}
