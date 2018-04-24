package com.ptit.btl.moviedb.data.source.remote;

import com.ptit.btl.moviedb.data.source.GenreDataSource;
import com.ptit.btl.moviedb.util.Constant;
import com.ptit.btl.moviedb.util.FetchGenreFromUrl;

/**
 * Created by admin on 25/4/18.
 */
public class GenreRemoteDataSource implements GenreDataSource.RemoteDataSource {
    private static GenreRemoteDataSource sInstance;

    public static GenreRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new GenreRemoteDataSource();
        }
        return sInstance;
    }

    private GenreRemoteDataSource() {
    }

    @Override
    public void loadGenres(GenreDataSource.LoadGenresCallback callback) {
        new FetchGenreFromUrl(callback).execute(
            Constant.ApiRequestUrl.API_GENRES_REQUEST);
    }
}
