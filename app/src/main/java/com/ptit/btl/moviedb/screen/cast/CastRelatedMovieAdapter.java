package com.ptit.btl.moviedb.screen.cast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.screen.home.HomeAdapter;
import com.ptit.btl.moviedb.util.ImageUtils;

import java.util.List;

/**
 * Created by CuongHQ on 5/19/2018.
 */

public class CastRelatedMovieAdapter extends RecyclerView.Adapter<CastRelatedMovieAdapter.CastRelatedMovieViewHolder>{

    private List<Movie> movies;
    private LoadAdapterDataCallback callback;

    public CastRelatedMovieAdapter(LoadAdapterDataCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public CastRelatedMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new CastRelatedMovieViewHolder(view, callback);

    }

    @Override
    public void onBindViewHolder(@NonNull CastRelatedMovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return (movies == null) ? 0 : movies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    class CastRelatedMovieViewHolder extends RecyclerView.ViewHolder {

        Movie movie;
        private ImageView mImageMovie;
        private TextView mTextName, mTextRate;

        public CastRelatedMovieViewHolder(View itemView, final LoadAdapterDataCallback mCallback) {
            super(itemView);
            initView();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onItemClick(movie);
                }
            });
        }


        private void initView() {
            mImageMovie = itemView.findViewById(R.id.image_card_movie);
            mTextName = itemView.findViewById(R.id.text_card_name);
            mTextRate = itemView.findViewById(R.id.text_card_rate);
        }

        void bind(Movie movie) {
            this.movie = movie;
            ImageUtils.loadImageFromUrlPicasso(mImageMovie, movie.getPosterPath(), R.drawable.poster_3);
            mTextName.setText(movie.getTitle());
            mTextRate.setText(movie.getVoteAverage());
        }
    }

    public interface LoadAdapterDataCallback {
        void onItemClick(Movie movie);
    }

}
