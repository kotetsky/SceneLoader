package com.spikart.sceneloader.repository.network.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.spikart.sceneloader.domain.Movie;
import com.spikart.sceneloader.domain.NetworkState;
import com.spikart.sceneloader.repository.network.api.MoviesApiInterface;
import com.spikart.sceneloader.repository.network.api.TheMovieDBAPIClient;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;
import rx.subjects.ReplaySubject;

import static com.spikart.sceneloader.repository.network.api.MoviesApiInterface.API_KEY;
import static com.spikart.sceneloader.repository.network.api.MoviesApiInterface.LANGUAGE;

public class NetMoviesPageKeyedDataSource extends PageKeyedDataSource<String, Movie> {

    private final static String TAG = NetMoviesPageKeyedDataSource.class.getSimpleName();

    private final MoviesApiInterface moviesApiInterface;
    private final MutableLiveData<NetworkState> networkState;

    private final ReplaySubject<Movie> moviesSubject;

    public NetMoviesPageKeyedDataSource() {
        moviesApiInterface = TheMovieDBAPIClient.getClient();
        networkState = new MutableLiveData<>();
        moviesSubject = ReplaySubject.create();
    }

    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public ReplaySubject<Movie> getMovies() {
        return moviesSubject;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Movie> callback) {
        Log.d(TAG, "load initial Rang, Count " + params.requestedLoadSize);

        networkState.postValue(NetworkState.LOADED);
        Call<ArrayList<Movie>> callBack = moviesApiInterface.getMovies(API_KEY, LANGUAGE, 1);

        callBack.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResult(response.body(), Integer.toString(1), Integer.toString(2));
                    networkState.postValue(NetworkState.LOADED);
                    response.body().forEach(moviesSubject::onNext);
                } else {
                    Log.d("API CALL ", response.message());
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                String errorMessage;
                if (t.getMessage() == null) {
                    errorMessage = "unknown error";
                } else {
                    errorMessage = t.getMessage();
                }
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                callback.onResult(new ArrayList<>(), Integer.toString(1), Integer.toString(2));
            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {
        Log.d(TAG, "loading page " + params.key);
        networkState.postValue(NetworkState.LOADING);
        final AtomicInteger page = new AtomicInteger(0);
        try {
            page.set(Integer.parseInt(params.key));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Call<ArrayList<Movie>> callBack = moviesApiInterface.getMovies(API_KEY, LANGUAGE, page.get());
        callBack.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                if (response.isSuccessful()) {
                    callback.onResult(response.body(), Integer.toString(page.get() + 1));
                    networkState.postValue(NetworkState.LOADED);
                    response.body().forEach(moviesSubject::onNext);
                } else {
                    Log.d("API CALL", response.message());
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                String errorMessage = (t.getMessage() == null) ? "unknown error" : t.getMessage();
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                callback.onResult(new ArrayList<Movie>(), Integer.toString(page.get()));
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {

    }
}
