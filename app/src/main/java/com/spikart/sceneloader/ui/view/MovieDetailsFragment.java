package com.spikart.sceneloader.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.spikart.sceneloader.R;
import com.spikart.sceneloader.databinding.FragmentDetailsBinding;
import com.spikart.sceneloader.viewmodel.MovieDetailsViewModel;
import com.squareup.picasso.Picasso;

public class MovieDetailsFragment extends Fragment {

    private static final String IMAGE_URL_PREFIX = "https://image.tmdb.org/t/p/";
    public static final String BIG_IMAGE_URL_PREFIX = IMAGE_URL_PREFIX + "w500";

    private MovieDetailsViewModel viewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        FragmentDetailsBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_details,
                container,
                false
        );

        viewModel = ViewModelProviders.of(getActivity()).get(MovieDetailsViewModel.class);
        View view = binding.getRoot();
        viewModel.getMovie().observe(this, binding::setMovie);
        return view;
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView image, String url) {
        if (url != null) {
            Picasso.get().load(BIG_IMAGE_URL_PREFIX + url).into(image);
        }
    }
}
