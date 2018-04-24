package com.ptit.btl.moviedb.data.repository;

import com.ptit.btl.moviedb.data.source.GenreDataSource;
import com.ptit.btl.moviedb.data.source.remote.GenreRemoteDataSource;

/**
 * Created by admin on 25/4/18.
 */
public class GenreRepository implements GenreDataSource.RemoteDataSource {
    private GenreRemoteDataSource mGenreRemoteDataSource;
    private static GenreRepository sInstance;

    public static GenreRepository getInstance(GenreRemoteDataSource genreRemoteDataSource) {
        if (sInstance == null) {
            sInstance = new GenreRepository(genreRemoteDataSource);
        }
        return sInstance;
    }

    private GenreRepository(
        GenreRemoteDataSource genreRemoteDataSource) {
        mGenreRemoteDataSource = genreRemoteDataSource;
    }

    @Override
    public void loadGenres(GenreDataSource.LoadGenresCallback callback) {
        mGenreRemoteDataSource.loadGenres(callback);
    }
}
