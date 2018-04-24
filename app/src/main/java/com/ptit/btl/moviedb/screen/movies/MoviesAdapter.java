package com.ptit.btl.moviedb.screen.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.screen.BaseRecyclerViewAdapter;
import com.ptit.btl.moviedb.util.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class MoviesAdapter extends BaseRecyclerViewAdapter<MoviesAdapter.ItemViewHolder> {
    private List<Movie> mMovies = new ArrayList<>();
    private LoadMoviesCallback mCallback;
    private FavouriteCallback mFavouriteCallback;

    protected MoviesAdapter(@NonNull Context context,
                            LoadMoviesCallback callback,
                            FavouriteCallback favouriteCallback) {
        super(context);
        mCallback = callback;
        mFavouriteCallback = favouriteCallback;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_movie_horizontal,
            parent, false);
        return new ItemViewHolder(view, mCallback, mFavouriteCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setData(mMovies.get(position));
    }

    void updateData(List<Movie> movies) {
        if (movies == null) return;
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    void updateSingleData(Movie movie) {
        if (movie == null) return;
        int indexChange = mMovies.indexOf(movie);
        if (indexChange > -1) {
            notifyItemChanged(mMovies.indexOf(movie), movie);
        }
    }

    void clearData() {
        if (mMovies == null) return;
        mMovies.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImagePoster;
        private TextView mTextTitle, mTextOverview, mTextRate;
        private Button mButtonFavourite;
        private Movie mMovie;
        private FavouriteCallback mFavouriteCallback;

        ItemViewHolder(View view,
                       final LoadMoviesCallback callback,
                       final FavouriteCallback callbackFavourite) {
            super(view);
            mImagePoster = view.findViewById(R.id.image_card_movie);
            mTextTitle = view.findViewById(R.id.text_card_name);
            mTextOverview = view.findViewById(R.id.text_card_overview);
            mTextRate = view.findViewById(R.id.text_card_rate);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (callback == null) return;
                    callback.onItemClicked(mMovie);
                }
            });
            mFavouriteCallback = callbackFavourite;
            initButton(view);
        }

        private void initButton(View view) {
            mButtonFavourite = view.findViewById(R.id.button_card_favourite);
            mButtonFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mFavouriteCallback == null) return;
                    if (mMovie.isFavourite()) {
                        mFavouriteCallback.onRemoveFavourite(mMovie);
                    } else {
                        mFavouriteCallback.onAddFavourite(mMovie);
                    }
                }
            });
        }

        void setData(Movie movie) {
            if (movie == null) return;
            mMovie = movie;
            ImageUtils.loadImageFromUrl(
                mImagePoster,
                movie.getPosterPath(),
                R.drawable.poster_3);
            mTextTitle.setText(movie.getTitle());
            mTextOverview.setText(movie.getOverview());
            mTextRate.setText(movie.getVoteAverage());
            if (movie.isFavourite()) {
                mButtonFavourite.setBackgroundResource(R.drawable.ic_love_plus);
            } else {
                mButtonFavourite.setBackgroundResource(R.drawable.ic_love_minus);
            }
        }
    }

    interface LoadMoviesCallback {
        void onItemClicked(Movie movie);
    }

    interface FavouriteCallback {
        void onAddFavourite(Movie movie);
        void onRemoveFavourite(Movie movie);
    }
}

