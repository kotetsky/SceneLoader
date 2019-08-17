package com.spikart.sceneloader.repository.network.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class NetMoviesDataSourceFactory extends DataSource.Factory {

    private static final String TAG = NetMoviesDataSourceFactory.class.getSimpleName();

    private MutableLiveData<NetMoviesPageKeyedDataSource> networkStatus;

    private NetMoviesDataSourceFactory() {

    }

}
