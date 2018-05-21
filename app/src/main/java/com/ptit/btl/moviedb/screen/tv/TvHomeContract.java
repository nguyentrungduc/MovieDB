package com.ptit.btl.moviedb.screen.tv;

import com.ptit.btl.moviedb.data.model.User;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;

import java.util.List;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public interface TvHomeContract {

    public interface View{

        void onLoadPopularTvSuccess(List<TvSeries> tvSeries);

        void onLoadPopularTvFailed();

        void onLoadTopRateTvSuccess(List<TvSeries> tvSeries);

        void onLoadTopRateTvFailed();
    }

    public interface Presenter {
        void loadPopularTv();

        void loadTopRateTv();
    }
}
