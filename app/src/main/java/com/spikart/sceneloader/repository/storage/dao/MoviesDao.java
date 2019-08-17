package com.spikart.sceneloader.repository.storage.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.spikart.sceneloader.domain.Movie;

import java.util.List;

@Dao
public interface MoviesDao {

    @Query("SELECT * FROM movies")
    List<Movie> getMovies();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovie(Movie movie);

    @Query("DELETE FROM movies")
    abstract void deleteAllMovies();
}
