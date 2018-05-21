package com.ptit.btl.moviedb.screen.tvdetail.tvseason;

import com.ptit.btl.moviedb.data.model.tv.Season;
import com.ptit.btl.moviedb.data.repository.TvRepository;
import com.ptit.btl.moviedb.data.source.TvDataSource;
import com.ptit.btl.moviedb.data.source.remote.TvSeriesRemoteDataSource;

import java.util.List;

/**
 * Created by CuongHQ on 5/22/2018.
 */

public class TvSeasonPresenter implements TvSeasonContract.Presenter{

    private TvSeasonContract.View view;
    private TvRepository repository;

    public TvSeasonPresenter(TvSeasonContract.View view) {
        this.view = view;
        repository = TvRepository.getInstance(TvSeriesRemoteDataSource.getInstance());
    }

    @Override
    public void getSeason(int id) {
        repository.getSeason(id, new TvDataSource.LoadSeasonCallback() {
            @Override
            public void onSeasonLoaded(List<Season> seasons) {
                view.onLoadedSeason(seasons);
            }

            @Override
            public void dataNotAvailable() {
                view.onLoadSeasonFailed();
            }
        });
    }


}
