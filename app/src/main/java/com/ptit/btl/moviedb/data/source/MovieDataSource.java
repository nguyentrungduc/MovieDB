package com.ptit.btl.moviedb.data.source;

import com.ptit.btl.moviedb.data.model.Movie;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public interface MovieDataSource {
    interface LoadMoviesCallback {
        void onMoviesLoaded(List<Movie> movies);

        void onDataNotAvailable();
    }

    interface LocalDataSource {
        void addMovieToLocal(Movie movie, DbCallBack dbCallBack) throws Exception;

        void deleteMovieFromLocal(Movie movie, DbCallBack dbCallBack) throws Exception;

        List<Movie> getMoviesFromLocal(DbCallBack dbCallBack) throws Exception;

        boolean isFavouriteMovie(String movieId, DbCallBack dbCallBack) throws Exception;

    }

    interface RemoteDataSource {
        void getMoviesByCategories(String categories, String language, int page,
                                   LoadMoviesCallback callback);

        void getMoviesByUrl(String id, String url, LoadMoviesCallback callback);

    }
}
