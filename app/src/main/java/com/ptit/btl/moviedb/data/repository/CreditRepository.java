package com.ptit.btl.moviedb.data.repository;

import com.ptit.btl.moviedb.data.source.CreditDataSource;
import com.ptit.btl.moviedb.data.source.remote.CreditRemoteDataSource;

/**
 * Created by admin on 25/4/18.
 */
public class CreditRepository implements CreditDataSource.RemoteDataSource {
    private static CreditRepository sInstance;
    private CreditRemoteDataSource mCreditRemoteDataSource;

    public static CreditRepository getInstance(
        CreditRemoteDataSource creditRemoteDataSource) {
        if (sInstance == null)
            sInstance = new CreditRepository(creditRemoteDataSource);
        return sInstance;
    }

    private CreditRepository(
        CreditRemoteDataSource creditRemoteDataSource) {
        mCreditRemoteDataSource = creditRemoteDataSource;
    }

    @Override
    public void getCreditByMovieId(String movieId,
                                   CreditDataSource.LoadProductionsCallback callback) {
        mCreditRemoteDataSource.getCreditByMovieId(movieId, callback);
    }
}
