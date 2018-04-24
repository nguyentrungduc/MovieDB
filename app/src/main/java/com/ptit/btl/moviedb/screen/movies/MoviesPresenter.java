package com.ptit.btl.moviedb.screen.movies;

import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.repository.MovieRepository;
import com.ptit.btl.moviedb.data.source.MovieDataSource;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class MoviesPresenter implements MoviesContract.Presenter {
    private MoviesContract.View mView;
    private MovieRepository mMovieRepository;

    MoviesPresenter(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
    }

    @Override
    public void setView(MoviesContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void loadMovieFromApi(String url, String id) {
        mMovieRepository.getMoviesByUrl(id, url, new MovieDataSource.LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                mView.onGetMoviesSuccess(initFavouriteMovieStatus(movies));
            }

            @Override
            public void onDataNotAvailable() {
                mView.onGetMoviesFailed();
            }
        });
    }

    @Override
    public void getFavouriteMovie() {
        List<Movie> movies = mMovieRepository.getMoviesFromLocal();
        if (movies == null || movies.size() == 0)
            mView.onGetMoviesFailed();
        else
            mView.onGetMoviesSuccess(initFavouriteMovieStatus(movies));
    }

    @Override
    public void addMovieToFavourite(Movie movie) {
        try {
            mMovieRepository.addMovieToLocal(movie);
            mView.onAddFavouriteSuccess(movie);
        } catch (Exception e) {
            mView.onAddFavouriteFailed();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMovieFromFavourite(Movie movie) {
        try {
            mMovieRepository.deleteMovieFromLocal(movie);
            mView.onDeleteFavouriteSuccess(movie);
        } catch (Exception e) {
            mView.onDeleteFavouriteFailed();
            e.printStackTrace();
        }
    }

    private List<Movie> initFavouriteMovieStatus(List<Movie> movies) {
        for (int i = 0; i < movies.size(); i++) {
            try {
                movies.get(i)
                    .setFavourite(mMovieRepository.isFavouriteMovie(movies.get(i).getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return movies;
    }
}
