package com.spikart.sceneloader.ui.viewholder;

import android.service.autofill.TextValueSanitizer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.spikart.sceneloader.R;
import com.spikart.sceneloader.domain.Movie;
import com.spikart.sceneloader.ui.listener.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String IMAGE_URL_PREFIX = "https://image.tmdb.org/t/p/";
    public static final String SMALL_IMAGE_URL_PREFIX = IMAGE_URL_PREFIX + "w300";
    public static final String BIG_IMAGE_URL_PREFIX = IMAGE_URL_PREFIX + "w500";

    private Movie movie;
    private TextView titleTextView;
    private TextView userRatingTextView;
    private ImageView thumbnailImageView;
    private ItemClickListener itemClickListener;

    public MovieViewHolder(View view, ItemClickListener itemClickListener) {
        super(view);
        this.titleTextView = view.findViewById(R.id.title);
        this.userRatingTextView = view.findViewById(R.id.userrating);
        this.thumbnailImageView = view.findViewById(R.id.thumbnail);
        this.itemClickListener = itemClickListener;
        view.setOnClickListener(this);
    }

    public void bindTo(Movie movie) {
        this.movie = movie;
        titleTextView.setText(movie.getTitle());
        userRatingTextView.setText(String.format(Locale.getDefault(), "%1$,.2f", movie.getVoteAverage()));
        if (movie.getPosterPath() != null) {
            String poster = SMALL_IMAGE_URL_PREFIX + movie.getPosterPath();
            Picasso.get().load(poster).into(thumbnailImageView);
        }
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.OnItemClick(movie);
        }
    }
}
