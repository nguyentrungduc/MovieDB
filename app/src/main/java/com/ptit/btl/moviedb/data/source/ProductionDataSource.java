package com.ptit.btl.moviedb.data.source;

import com.ptit.btl.moviedb.data.model.Production;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public interface ProductionDataSource {
    interface LoadProductionsCallback {
        void onProductionsLoaded(List<Production> productions);

        void onDataNotAvailable();
    }

    interface RemoteDataSource {
        void getProductionByMovieId(String id, LoadProductionsCallback callback);
    }
}
