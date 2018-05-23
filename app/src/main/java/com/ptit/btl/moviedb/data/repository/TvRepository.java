package com.ptit.btl.moviedb.data.repository;

import com.ptit.btl.moviedb.data.source.CreditDataSource;
import com.ptit.btl.moviedb.data.source.TvDataSource;
import com.ptit.btl.moviedb.data.source.remote.TvSeriesRemoteDataSource;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public class TvRepository {
    public static TvRepository instance;

    public static TvRepository getInstance(TvSeriesRemoteDataSource remoteDataSource) {
        if (instance == null)
            instance = new TvRepository(remoteDataSource);
        return instance;
    }

    private TvSeriesRemoteDataSource remoteDataSource;

    public TvRepository(TvSeriesRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public void getTvSeriesByCategory(String category, String language, int page, TvDataSource.LoadTvCallback callback) {
        remoteDataSource.getTvSeriesByCategory(category, language, page, callback);
    }

    public void getTvSeriesDetail(int id, TvDataSource.LoadTvSeriesDetailCallback callback) {
        remoteDataSource.getTvSeriesDetail(id, callback);
    }

    public void getReview(int id, TvDataSource.LoadReviewCallback callback) {
        remoteDataSource.getReview(id, callback);
    }

    public void getSeason(int id, TvDataSource.LoadSeasonCallback callback) {
        remoteDataSource.getSeason(id, callback);
    }

    public void getSimilarTvShows(int id, TvDataSource.LoadTvCallback callback) {
        remoteDataSource.getSimilarTvShows(id, callback);
    }

    public void getCredit(int id, CreditDataSource.LoadProductionsCallback callback) {
        remoteDataSource.getCredits(id, callback);
    }
}
