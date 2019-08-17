package com.spikart.sceneloader.repository.network.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spikart.sceneloader.domain.Movie;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class TheMovieDBAPIClient {

    public static MoviesApiInterface getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new ArrayList<Movie>().getClass(), new MoviesJsonDeserializer())
                .create()
    }

}
