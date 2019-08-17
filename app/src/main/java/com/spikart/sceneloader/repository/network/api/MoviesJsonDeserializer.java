package com.spikart.sceneloader.repository.network.api;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.spikart.sceneloader.domain.Movie;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MoviesJsonDeserializer implements JsonDeserializer {

    private static final String TAG = MoviesJsonDeserializer.class.getSimpleName();

    private static final String MOVIES_ARRAY_DATA_TAG = "results";

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<Movie> movies = null;
        try {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray moviesJsonArray = jsonObject.getAsJsonArray(MOVIES_ARRAY_DATA_TAG);
            movies = new ArrayList<>(moviesJsonArray.size());
            for (int i = 0; i < moviesJsonArray.size(); i++) {
                Movie deserializedMovie = context.deserialize(moviesJsonArray.get(i), Movie.class);
                movies.add(deserializedMovie);
            }
        } catch (JsonParseException e) {
            Log.e(TAG, String.format("Could not deserialise movie element: %s", json.toString()));
        }
        return movies;
    }
}
