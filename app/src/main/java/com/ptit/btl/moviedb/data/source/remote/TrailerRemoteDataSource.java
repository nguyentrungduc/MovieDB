package com.ptit.btl.moviedb.data.source.remote;

import com.ptit.btl.moviedb.data.source.TrailerDataSource;
import com.ptit.btl.moviedb.util.Constant;
import com.ptit.btl.moviedb.util.FetchTrailerFromUrl;

/**
 * Created by admin on 25/4/18.
 */
public class TrailerRemoteDataSource implements TrailerDataSource.RemoteDataSource {
    private static TrailerRemoteDataSource sInstance;

    public static TrailerRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new TrailerRemoteDataSource();
        }
        return sInstance;
    }

    private TrailerRemoteDataSource() {
    }

    @Override
    public void getTrailerByMovieId(String id,
                                    TrailerDataSource.LoadTrailersCallback callback) {
        new FetchTrailerFromUrl(callback).execute(
            String.format(Constant.ApiRequestUrl.API_TRAILER_BY_MOVIE_ID, id));
    }
}
