package com.ptit.btl.moviedb.data.source.remote;

import android.util.Log;

import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.data.source.CreditDataSource;
import com.ptit.btl.moviedb.data.source.TvDataSource;
import com.ptit.btl.moviedb.util.FetchCreditFromUrl;
import com.ptit.btl.moviedb.util.FetchReviewFromUrl;
import com.ptit.btl.moviedb.util.FetchSeasonFromUrl;
import com.ptit.btl.moviedb.util.FetchTvSeriesDetailFromUrl;
import com.ptit.btl.moviedb.util.FetchTvSeriesFromUrl;

import static com.ptit.btl.moviedb.util.Constant.ApiRequestUrl.API_CREDITS_REQUEST;
import static com.ptit.btl.moviedb.util.Constant.ApiRequestUrl.API_SIMILAR_TV_SERIES;
import static com.ptit.btl.moviedb.util.Constant.ApiRequestUrl.API_TV_CATEGORY_REQUEST;
import static com.ptit.btl.moviedb.util.Constant.ApiRequestUrl.API_TV_DETAIL_REQUEST;
import static com.ptit.btl.moviedb.util.Constant.ApiRequestUrl.API_TV_REVIEW_REQUEST;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public class TvSeriesRemoteDataSource implements TvDataSource.RemoteDataSource {

    private static TvSeriesRemoteDataSource instance;

    public static TvSeriesRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new TvSeriesRemoteDataSource();
        }
        return instance;
    }

    @Override
    public void getTvSeriesByCategory(String category, String language, int page, TvDataSource.LoadTvCallback callback) {
        new FetchTvSeriesFromUrl(callback).execute(
                String.format(API_TV_CATEGORY_REQUEST, category, language, page)
        );
    }

    @Override
    public void getTvSeriesDetail(int id, TvDataSource.LoadTvSeriesDetailCallback callback) {
        new FetchTvSeriesDetailFromUrl(callback).execute(
                String.format(API_TV_DETAIL_REQUEST, id+"")
        );
    }

    @Override
    public void getReview(int id, TvDataSource.LoadReviewCallback callback) {
        new FetchReviewFromUrl(callback).execute(
                String.format(API_TV_REVIEW_REQUEST, id+"")
        );
    }

    @Override
    public void getSeason(int id, TvDataSource.LoadSeasonCallback callback) {
        new FetchSeasonFromUrl(callback).execute(
                String.format(API_TV_DETAIL_REQUEST, id+"")
        );
    }

    @Override
    public void getSimilarTvShows(int tvId, TvDataSource.LoadTvCallback callback) {
        new FetchTvSeriesFromUrl(callback).execute(
                String.format(API_SIMILAR_TV_SERIES, tvId+"")
        );
    }

    @Override
    public void getCredits(int tvId, CreditDataSource.LoadProductionsCallback callback) {
        new FetchCreditFromUrl(callback).execute(String.format(API_CREDITS_REQUEST, tvId+""));
    }

}
