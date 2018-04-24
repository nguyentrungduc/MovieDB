package com.ptit.btl.moviedb.data.source;

import com.ptit.btl.moviedb.data.model.credit.Credit;

/**
 * Created by admin on 25/4/18.
 */
public interface CreditDataSource {
    interface LoadProductionsCallback {
        void onCreditLoaded(Credit credits);
        void onDataNotAvailable();
    }

    interface RemoteDataSource {
        void getCreditByMovieId(String movieId, CreditDataSource.LoadProductionsCallback callback);
    }
}
