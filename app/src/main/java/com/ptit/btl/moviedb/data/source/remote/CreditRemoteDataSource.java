package com.ptit.btl.moviedb.data.source.remote;

import com.ptit.btl.moviedb.data.source.CreditDataSource;
import com.ptit.btl.moviedb.util.Constant;
import com.ptit.btl.moviedb.util.FetchCreditFromUrl;
/**
 * Created by admin on 25/4/18.
 */
public class CreditRemoteDataSource implements CreditDataSource.RemoteDataSource {
    private static CreditRemoteDataSource sInstance;

    public static CreditRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new CreditRemoteDataSource();
        }
        return sInstance;
    }

    private CreditRemoteDataSource() {
    }

    @Override
    public void getCreditByMovieId(String movieId,
                                   CreditDataSource.LoadProductionsCallback callback) {
        new FetchCreditFromUrl(callback).execute(
            String.format(Constant.ApiRequestUrl.API_CREDIT_BY_MOVIE_REQUEST, movieId)
        );
    }
}
