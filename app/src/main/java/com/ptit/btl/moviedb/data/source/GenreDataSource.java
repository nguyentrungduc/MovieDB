package com.ptit.btl.moviedb.data.source;

import com.ptit.btl.moviedb.data.model.Genre;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public interface GenreDataSource {
    interface LoadGenresCallback {
        void onGenresLoaded(List<Genre> genres);

        void onDataNotAvailable();
    }

    interface RemoteDataSource {
        void loadGenres(LoadGenresCallback callback);
    }
}
