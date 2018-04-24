package com.ptit.btl.moviedb.screen.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.screen.BaseRecyclerViewAdapter;
import com.ptit.btl.moviedb.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class HomeAdapter extends BaseRecyclerViewAdapter<HomeAdapter.ItemViewHolder> {
    private LoadAdapterDataCallback mCallback;
    private List<Movie> mMovies = new ArrayList<>();

    HomeAdapter(@NonNull Context context,
                LoadAdapterDataCallback callback) {
        super(context);
        mCallback = callback;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
        return new ItemViewHolder(view, mCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setData(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    void updateData(List<Movie> movies) {
        if (movies == null) return;
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageMovie;
        private TextView mTextName, mTextRate;
        private Movie mMovie;

        ItemViewHolder(View view, final LoadAdapterDataCallback mCallback) {
            super(view);
            mImageMovie = view.findViewById(R.id.image_card_movie);
            mTextName = view.findViewById(R.id.text_card_name);
            mTextRate = view.findViewById(R.id.text_card_rate);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onItemClick(mMovie);
                }
            });
        }

        public void setData(Movie movie) {
            if (movie == null) return;
            mMovie = movie;
            mTextName.setText(movie.getTitle());
            mTextRate.setText(movie.getVoteAverage());
            Glide.with(itemView.getContext())
                .load(String.format(Constant.ApiRequestUrl.API_IMAGE_URL, movie.getPosterPath()))
                .into(mImageMovie);
        }
    }

    public interface LoadAdapterDataCallback {
        void onItemClick(Movie movie);
    }
}
