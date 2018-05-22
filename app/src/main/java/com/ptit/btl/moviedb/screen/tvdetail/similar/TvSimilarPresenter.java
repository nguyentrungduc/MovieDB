package com.ptit.btl.moviedb.screen.tvdetail.similar;

import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.data.repository.TvRepository;
import com.ptit.btl.moviedb.data.source.TvDataSource;
import com.ptit.btl.moviedb.data.source.remote.TvSeriesRemoteDataSource;

import java.util.List;

/**
 * Created by CuongHQ on 5/22/2018.
 */

public class TvSimilarPresenter implements TvSimilarContract.Presenter {

    TvRepository repository;
    TvSimilarContract.View view;

    public TvSimilarPresenter(TvSimilarContract.View view) {
        this.view = view;
        repository = TvRepository.getInstance(TvSeriesRemoteDataSource.getInstance());
    }

    @Override
    public void getSimilarTvShow(int tvId) {
        repository.getSimilarTvShows(tvId, new TvDataSource.LoadTvCallback() {
            @Override
            public void onTvLoaded(List<TvSeries> tvSeries) {
                view.onLoadedSimilarTvShows(tvSeries);
            }

            @Override
            public void dataNotAvailable() {
                view.onLoadSimilarTvShowsFailed();
            }
        });
    }
}
