package com.ptit.btl.moviedb.data.source;

import com.ptit.btl.moviedb.data.model.tv.Review;
import com.ptit.btl.moviedb.data.model.tv.Season;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;

import java.util.List;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public interface TvDataSource {

    interface LoadTvSeriesCallback {
        void onTvSeriesLoaded(List<TvSeries> tvSeries);

        void dataNotAvailable();
    }

    interface LoadSeasonCallback {
        void onSeasonLoaded(List<Season> seasons);

        void dataNotAvailable();
    }

    interface LoadReviewCallback {
        void onReviewLoaded(List<Review> reviews);

        void dataNotAvailable();
    }

    interface LoadTvSeriesDetailCallback {
        void onTvLoaded(TvSeries tvSeries);

        void dataNotAvailable();
    }

    interface LoadTvCallback {
        void onTvLoaded(List<TvSeries> tvSeries);

        void dataNotAvailable();
    }

    interface RemoteDataSource {
        void getTvSeriesByCategory(String category, String language, int page, LoadTvCallback callback);

        void getTvSeriesDetail(int id, LoadTvSeriesDetailCallback callback);

        void getReview(int id, LoadReviewCallback callback);

        void getSeason(int id, LoadSeasonCallback callback);

        void getSimilarTvShows(int tvId, LoadTvCallback callback);

        void getCredits(int tvId, CreditDataSource.LoadProductionsCallback callback);
    }


}
