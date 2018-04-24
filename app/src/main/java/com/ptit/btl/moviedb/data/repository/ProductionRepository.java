package com.ptit.btl.moviedb.data.repository;

import com.ptit.btl.moviedb.data.source.ProductionDataSource;
import com.ptit.btl.moviedb.data.source.remote.ProductionRemoteDataSource;

/**
 * Created by admin on 25/4/18.
 */
public class ProductionRepository implements ProductionDataSource.RemoteDataSource {
    private static ProductionRepository sInstance;
    private ProductionRemoteDataSource mProductionRemoteDataSource;

    private ProductionRepository(
        ProductionRemoteDataSource productionRemoteDataSource) {
        mProductionRemoteDataSource = productionRemoteDataSource;
    }

    public static ProductionRepository getInstance(
        ProductionRemoteDataSource mProductionRemoteDataSource) {
        if (sInstance == null) {
            sInstance = new ProductionRepository(mProductionRemoteDataSource);
        }
        return sInstance;
    }

    @Override
    public void getProductionByMovieId(String id,
                                       ProductionDataSource.LoadProductionsCallback callback) {
        mProductionRemoteDataSource.getProductionByMovieId(id, callback);
    }
}
