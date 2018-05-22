package com.ptit.btl.moviedb.screen.tvdetail.similar;

import com.ptit.btl.moviedb.data.model.tv.TvSeries;

import java.util.List;

/**
 * Created by CuongHQ on 5/22/2018.
 */

public interface TvSimilarContract {

    interface View {
        void onLoadedSimilarTvShows(List<TvSeries> tvSeries);

        void onLoadSimilarTvShowsFailed();
    }

    interface Presenter {
        void getSimilarTvShow(int tvId);
    }
}
