package com.ptit.btl.moviedb.data.repository;

import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.source.DbCallBack;
import com.ptit.btl.moviedb.data.source.MovieDataSource;
import com.ptit.btl.moviedb.data.source.local.MovieLocalDataSource;
import com.ptit.btl.moviedb.data.source.remote.MovieRemoteDataSource;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class MovieRepository  {
    private static MovieRepository sInstance;
    private MovieRemoteDataSource mMovieRemoteDataSource;
    private MovieLocalDataSource mMovieLocalDataSource;

    private MovieRepository(MovieRemoteDataSource movieRemoteDataSource,
                            MovieLocalDataSource movieLocalDataSource) {
        mMovieRemoteDataSource = movieRemoteDataSource;
        mMovieLocalDataSource = movieLocalDataSource;
    }

    public static MovieRepository getInstance(
        MovieRemoteDataSource movieRemoteDataSource,
        MovieLocalDataSource movieLocalDataSource) {
        if (sInstance == null)
            sInstance = new MovieRepository(movieRemoteDataSource, movieLocalDataSource);
        return sInstance;
    }

    public void getMoviesByCategories(String categories, String language,
                                      int page,
                                      MovieDataSource.LoadMoviesCallback callback) {
        mMovieRemoteDataSource.getMoviesByCategories(
            categories,
            language,
            page,
            callback);
    }

    public void getMoviesByUrl(String id, String url,
                               MovieDataSource.LoadMoviesCallback callback) {
        mMovieRemoteDataSource.getMoviesByUrl(id, url, callback);
    }

    public void addMovieToLocal(Movie movie, DbCallBack dbCallBack) throws Exception {
        mMovieLocalDataSource.addMovieToLocal(movie, dbCallBack);
    }

    public void deleteMovieFromLocal(Movie movie, DbCallBack dbCallBack) throws Exception {
        mMovieLocalDataSource.deleteMovieFromLocal(movie, dbCallBack);
    }

    public List<Movie> getMoviesFromLocal(DbCallBack dbCallBack) {
        return mMovieLocalDataSource.getMoviesFromLocal(dbCallBack);
    }

    public boolean isFavouriteMovie(String id, DbCallBack dbCallBack) throws Exception {
        return mMovieLocalDataSource.isFavouriteMovie(id, dbCallBack);
    }
}
