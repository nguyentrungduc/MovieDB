package com.ptit.btl.moviedb.screen.tvdetail.credits;

import com.ptit.btl.moviedb.data.model.credit.Credit;
import com.ptit.btl.moviedb.data.repository.TvRepository;
import com.ptit.btl.moviedb.data.source.CreditDataSource;
import com.ptit.btl.moviedb.data.source.remote.TvSeriesRemoteDataSource;

/**
 * Created by CuongHQ on 5/22/2018.
 */

public class TvCreditPresenter implements TvCreditsContract.Presenter {

    private TvCreditsContract.View view;
    private TvRepository repository;

    public TvCreditPresenter(TvCreditsContract.View view) {
        this.view = view;
        repository = TvRepository.getInstance(TvSeriesRemoteDataSource.getInstance());
    }

    @Override
    public void getCredit(int tvId) {
        repository.getCredit(tvId, new CreditDataSource.LoadProductionsCallback() {
            @Override
            public void onCreditLoaded(Credit credits) {
                view.onLoadedCredit(credits);
            }

            @Override
            public void onDataNotAvailable() {
                view.onLoadCreditFailed();
            }
        });
    }

}
