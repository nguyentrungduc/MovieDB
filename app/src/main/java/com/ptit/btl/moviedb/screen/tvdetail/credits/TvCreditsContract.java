package com.ptit.btl.moviedb.screen.tvdetail.credits;

import com.ptit.btl.moviedb.data.model.credit.Credit;

/**
 * Created by CuongHQ on 5/22/2018.
 */

public interface TvCreditsContract {

    interface View {
        void onLoadedCredit(Credit credit);

        void onLoadCreditFailed();
    }

    interface Presenter {
        void getCredit(int tvId);
    }
}
