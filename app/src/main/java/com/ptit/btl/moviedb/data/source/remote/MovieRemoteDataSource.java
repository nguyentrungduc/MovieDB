package com.ptit.btl.moviedb.data.source.remote;

import com.ptit.btl.moviedb.data.source.MovieDataSource;
import com.ptit.btl.moviedb.util.Constant;
import com.ptit.btl.moviedb.util.FetchDataFromUrl;

/**
 * Created by admin on 25/4/18.
 */
public class MovieRemoteDataSource implements MovieDataSource.RemoteDataSource {
    private static MovieRemoteDataSource sInstance;

    public static MovieRemoteDataSource getInstance() {
        if (sInstance == null) sInstance = new MovieRemoteDataSource();
        return sInstance;
    }

    private MovieRemoteDataSource() {
    }

    @Override
    public void getMoviesByCategories(String categories, String language, int page,
                                      MovieDataSource.LoadMoviesCallback callback) {
        new FetchDataFromUrl(callback)
            .execute(
                String.format(
                    Constant.ApiRequestUrl.API_URL_REQUEST,
                    categories,
                    language,
                    page));
    }

    @Override
    public void getMoviesByUrl(String id, String url,
                               MovieDataSource.LoadMoviesCallback callback) {
        new FetchDataFromUrl(callback)
            .execute(String.format(url, id));
    }
}
