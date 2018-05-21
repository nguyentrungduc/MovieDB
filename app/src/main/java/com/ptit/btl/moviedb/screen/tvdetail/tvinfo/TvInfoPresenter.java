package com.ptit.btl.moviedb.screen.tvdetail.tvinfo;

import com.ptit.btl.moviedb.data.model.tv.Review;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.data.repository.TvRepository;
import com.ptit.btl.moviedb.data.source.TvDataSource;
import com.ptit.btl.moviedb.data.source.remote.TvSeriesRemoteDataSource;

import java.util.List;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public class TvInfoPresenter implements TvInfoContract.Presenter {

    private TvInfoContract.View view;
    private TvRepository repository;

    public TvInfoPresenter(TvInfoContract.View view) {
        this.view = view;
        repository = TvRepository.getInstance(TvSeriesRemoteDataSource.getInstance());
    }

    @Override
    public void loadTvSeries(int id) {
        repository.getTvSeriesDetail(id, new TvDataSource.LoadTvSeriesDetailCallback() {
            @Override
            public void onTvLoaded(TvSeries tvSeries) {
                view.onLoadedTvSeries(tvSeries);
            }

            @Override
            public void dataNotAvailable() {
                view.onLoadTvSeriesFailed();
            }
        });
    }

    @Override
    public void loadReview(int id) {
        repository.getReview(id, new TvDataSource.LoadReviewCallback() {
            @Override
            public void onReviewLoaded(List<Review> reviews) {
                view.onLoadedReviews(reviews);
            }

            @Override
            public void dataNotAvailable() {
                view.onLoadReviewsFailed();
            }
        });
    }
}
