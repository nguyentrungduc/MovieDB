package com.ptit.btl.moviedb.screen.tvdetail.tvinfo;

import com.ptit.btl.moviedb.data.model.tv.Review;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;

import java.util.List;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public interface TvInfoContract {

    interface View {
        void onLoadedTvSeries(TvSeries tvSeries);

        void onLoadTvSeriesFailed();

        void onLoadedReviews(List<Review> reviews);

        void onLoadReviewsFailed();
    }

    interface Presenter {
        void loadTvSeries(int id);

        void loadReview(int id);
    }
}
