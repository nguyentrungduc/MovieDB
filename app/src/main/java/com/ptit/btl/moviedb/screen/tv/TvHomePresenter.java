package com.ptit.btl.moviedb.screen.tv;

import com.ptit.btl.moviedb.data.model.User;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.data.repository.TvRepository;
import com.ptit.btl.moviedb.data.source.TvDataSource;
import com.ptit.btl.moviedb.data.source.remote.TvSeriesRemoteDataSource;

import java.util.List;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public class TvHomePresenter implements TvHomeContract.Presenter{

    private TvHomeContract.View view;
    private TvRepository repository;

    public TvHomePresenter(TvHomeContract.View view) {
        this.view = view;
        repository = TvRepository.getInstance(TvSeriesRemoteDataSource.getInstance());
    }


    @Override
    public void loadPopularTv() {
        repository.getTvSeriesByCategory("tv/popular", "en-US", 1, new TvDataSource.LoadTvCallback() {
            @Override
            public void onTvLoaded(List<TvSeries> tvSeries) {
                view.onLoadPopularTvSuccess(tvSeries);
            }

            @Override
            public void dataNotAvailable() {
                view.onLoadPopularTvFailed();
            }
        });
    }

    @Override
    public void loadTopRateTv() {
        repository.getTvSeriesByCategory("tv/top_rated", "en-US", 1, new TvDataSource.LoadTvCallback() {
            @Override
            public void onTvLoaded(List<TvSeries> tvSeries) {
                view.onLoadTopRateTvSuccess(tvSeries);
            }

            @Override
            public void dataNotAvailable() {
                view.onLoadTopRateTvFailed();
            }
        });
    }
}
