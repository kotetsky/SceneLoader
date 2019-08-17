package com.spikart.sceneloader.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.spikart.sceneloader.domain.Movie;

public class MoviesListViewModel extends AndroidViewModel {

    private MoviesRepository repository;

    public MoviesListViewModel(@NonNull Application application){
        super(application);
        repository = MoviesRepository.getInstance(application);
    }
}
