package com.ptit.btl.moviedb.screen.tvdetail.tvseason;

import com.ptit.btl.moviedb.data.model.tv.Season;

import java.util.List;

/**
 * Created by CuongHQ on 5/22/2018.
 */

public interface TvSeasonContract {
    interface View {
        void onLoadedSeason(List<Season> seasons);

        void onLoadSeasonFailed();
    }

    interface Presenter {
        void getSeason(int id);
    }
}
