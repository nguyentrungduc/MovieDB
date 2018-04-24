package com.ptit.btl.moviedb.screen.movies;

import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.screen.BasePresenter;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public interface MoviesContract {
    interface View {
        void onGetMoviesSuccess(List<Movie> movies);

        void onGetMoviesFailed();

        void onAddFavouriteSuccess(Movie movie);

        void onAddFavouriteFailed();

        void onDeleteFavouriteSuccess(Movie movie);

        void onDeleteFavouriteFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void loadMovieFromApi(String url, String id);

        void getFavouriteMovie();

        void addMovieToFavourite(Movie movie);

        void deleteMovieFromFavourite(Movie movie);

    }
}
