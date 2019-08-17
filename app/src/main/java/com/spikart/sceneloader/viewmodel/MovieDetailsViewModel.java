package com.spikart.sceneloader.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.spikart.sceneloader.domain.Movie;

public class MovieDetailsViewModel extends ViewModel {

    final private MutableLiveData<Movie> movie;

    public MovieDetailsViewModel() {
        movie = new MutableLiveData<>();
    }

    public MutableLiveData<Movie> getMovie() {
        return movie;
    }
}
