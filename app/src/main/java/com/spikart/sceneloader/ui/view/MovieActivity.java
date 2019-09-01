package com.spikart.sceneloader.ui.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.spikart.sceneloader.R;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movies);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragmentsContainer) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            MoviesListFragment moviesListFragment = new MoviesListFragment();
            moviesListFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentsContainer, moviesListFragment)
                    .commit();
        }
    }

}
