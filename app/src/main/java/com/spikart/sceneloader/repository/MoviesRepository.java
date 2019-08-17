package com.spikart.sceneloader.repository;

import android.content.Context;

import androidx.lifecycle.MediatorLiveData;

import com.spikart.sceneloader.repository.network.MoviesNetwork;
import com.spikart.sceneloader.repository.storage.MoviesDatabase;

public class MoviesRepository {

    private final static String TAG = MoviesRepository.class.getSimpleName();

    private static MoviesRepository instance;
    private final MoviesNetwork network;
    private final MoviesDatabase database;
    private final MediatorLiveData liveDataManager;

    private MoviesRepository(Context context) {
        NetMoviesDataSourceFactory dataSourceFactory = new NetMoviesDataSourceFactory();

        network = MoviesNetwork(dataSourceFactory, boundaryCallback);
        database = MoviesDatabase.getInstance(context.getApplicationContext());
    }

}