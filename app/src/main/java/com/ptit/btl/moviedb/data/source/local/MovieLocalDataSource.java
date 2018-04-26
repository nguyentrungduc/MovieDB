package com.ptit.btl.moviedb.data.source.local;

import android.content.Context;

import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.source.DbCallBack;
import com.ptit.btl.moviedb.data.source.MovieDataSource;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class MovieLocalDataSource implements MovieDataSource.LocalDataSource {
    private static MovieLocalDataSource sInstance;
    private MoviesDatabaseHelper mDatabase;

    private MovieLocalDataSource(Context context) {
        mDatabase = MoviesDatabaseHelper.getInstance(context);
    }

    public static MovieLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MovieLocalDataSource(context);
        }
        return sInstance;
    }

    @Override
    public void addMovieToLocal(Movie movie, DbCallBack dbCallBack) throws Exception {
        mDatabase.addMovies(movie);
    }

    @Override
    public void deleteMovieFromLocal(Movie movie, DbCallBack dbCallBack) throws Exception {
        mDatabase.deleteMovies(movie);
    }

    @Override
    public List<Movie> getMoviesFromLocal(DbCallBack dbCallBack) {
        return mDatabase.getAllMovies();
    }

    @Override
    public boolean isFavouriteMovie(String movieId, DbCallBack dbCallBack) throws Exception {
        return mDatabase.checkExistMovie(movieId);
    }
}
