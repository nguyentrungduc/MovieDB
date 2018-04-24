package com.ptit.btl.moviedb.data.source;

import com.ptit.btl.moviedb.data.model.Trailer;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public interface TrailerDataSource {
    interface LoadTrailersCallback {
        void onTrailersLoaded(List<Trailer> trailers);
        void onDataNotAvailable();
    }

    interface RemoteDataSource {
        void getTrailerByMovieId(String id, LoadTrailersCallback loadTrailersCallback);
    }
}
