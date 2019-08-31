package com.spikart.sceneloader.repository.network.api;

import com.spikart.sceneloader.domain.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApiInterface {

    public static final String API_KEY = "04f2f288263683f12131ae2ae1d348c6";
    public static final String LANGUAGE = "en";

    public static final String API_KEY_REQUEST_PARAM = "api_key";
    public static final String LANGUAGE_REQUEST_PARAM = "language";
    public static final String PAGE_REQUEST_PARAM = "page";

    @GET(".")
    Call<ArrayList<Movie>> getMovies(
            @Query(API_KEY_REQUEST_PARAM) String key,
            @Query(LANGUAGE_REQUEST_PARAM) String language,
            @Query(PAGE_REQUEST_PARAM) int page
    );

}
