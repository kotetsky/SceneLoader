package com.spikart.sceneloader.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.spikart.sceneloader.R;
import com.spikart.sceneloader.domain.Movie;
import com.spikart.sceneloader.domain.NetworkState;
import com.spikart.sceneloader.ui.listener.ItemClickListener;
import com.spikart.sceneloader.ui.viewholder.MovieViewHolder;
import com.spikart.sceneloader.ui.viewholder.NetworkStateItemHolder;

public class MoviePageListAdapter extends PagedListAdapter<Movie, RecyclerView.ViewHolder> {

    private NetworkState networkState;
    private ItemClickListener itemClickListener;

    public MoviePageListAdapter(ItemClickListener itemClickListener) {
        super(Movie.DIFF_CALLBACK);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            @LayoutRes int viewType
    ) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == R.layout.movie_item) {
            View view = layoutInflater.inflate(R.layout.movie_item, parent, false);
            MovieViewHolder viewHolder = new MovieViewHolder(view, itemClickListener);
            ;
            return viewHolder;
        } else if (viewType == R.layout.network_state_item) {
            return new NetworkStateItemHolder(parent);
        } else {
            throw new IllegalArgumentException("unknown View type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.movie_item:
                ((MovieViewHolder) viewHolder).bindTo(getItem(position));
                break;
            case R.layout.network_state_item:
                ((NetworkStateItemHolder) viewHolder).bindView(networkState);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.network_state_item;
        } else {
            return R.layout.movie_item;
        }
    }

    private boolean hasExtraRow() {
        return networkState != null && networkState != NetworkState.LOADED;
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousNetworkState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousNetworkState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }
}
