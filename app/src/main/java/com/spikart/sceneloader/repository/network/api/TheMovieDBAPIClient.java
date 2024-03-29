package com.spikart.sceneloader.repository.network.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spikart.sceneloader.domain.Movie;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TheMovieDBAPIClient {

    private static final String POPULAR_MOVIES_BASE_URL = "https://api.themoviedb.org/3/movie/popular/";

    public static MoviesApiInterface getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new ArrayList<Movie>().getClass(), new MoviesJsonDeserializer())
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(POPULAR_MOVIES_BASE_URL);

        return builder.build().create(MoviesApiInterface.class);
    }

}
