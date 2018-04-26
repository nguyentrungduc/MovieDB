package com.ptit.btl.moviedb.screen.detail;

import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.model.Production;
import com.ptit.btl.moviedb.data.model.Trailer;
import com.ptit.btl.moviedb.data.model.credit.Credit;
import com.ptit.btl.moviedb.data.repository.CreditRepository;
import com.ptit.btl.moviedb.data.repository.MovieRepository;
import com.ptit.btl.moviedb.data.repository.ProductionRepository;
import com.ptit.btl.moviedb.data.repository.TrailerRepository;
import com.ptit.btl.moviedb.data.source.CreditDataSource;
import com.ptit.btl.moviedb.data.source.DbCallBack;
import com.ptit.btl.moviedb.data.source.ProductionDataSource;
import com.ptit.btl.moviedb.data.source.TrailerDataSource;
import com.ptit.btl.moviedb.data.source.remote.CreditRemoteDataSource;
import com.ptit.btl.moviedb.data.source.remote.ProductionRemoteDataSource;
import com.ptit.btl.moviedb.data.source.remote.TrailerRemoteDataSource;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class DetailPresenter implements DetailContract.Presenter {
    private DetailContract.View mView;
    private ProductionRepository mProductionRepository;
    private CreditRepository mCreditRepository;
    private TrailerRepository mTrailerRepository;
    private MovieRepository mMovieRepository;
    private boolean mIsProductionSuccess, mIsCreditSuccess, mIsTralerSuccess;

    DetailPresenter(MovieRepository movieRepository) {
        mProductionRepository = ProductionRepository
            .getInstance(ProductionRemoteDataSource.getInstance());
        mCreditRepository = CreditRepository
            .getInstance(CreditRemoteDataSource.getInstance());
        mTrailerRepository = TrailerRepository
            .getInstance(TrailerRemoteDataSource.getInstance());
        mMovieRepository = movieRepository;
    }

    @Override
    public void setView(DetailContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void loadProductionsByMovieId(String movieId) {
        mIsProductionSuccess = false;
        mProductionRepository.getProductionByMovieId(movieId,
            new ProductionDataSource.LoadProductionsCallback() {
                @Override
                public void onProductionsLoaded(List<Production> productions) {
                    mIsProductionSuccess = true;
                    mView.onLoadProductionSuccess(productions);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onLoadProductionFailed();
                }
            });
    }

    @Override
    public void loadCreditByMovieId(String movieId) {
        mIsCreditSuccess = false;
        mCreditRepository.getCreditByMovieId(movieId,
            new CreditDataSource.LoadProductionsCallback() {
                @Override
                public void onCreditLoaded(Credit credits) {
                    mIsCreditSuccess = true;
                    mView.onLoadCreditSuccess(credits);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onLoadCreditFailed();
                }
            });
    }

    @Override
    public void loadTrailerByMovieId(String movieId) {
        mIsTralerSuccess = false;
        mTrailerRepository.getTrailerByMovieId(movieId,
            new TrailerDataSource.LoadTrailersCallback() {
                @Override
                public void onTrailersLoaded(List<Trailer> trailers) {
                    mIsTralerSuccess = true;
                    mView.onLoadTrailerSuccess(trailers);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onLoadTrailerFailed();
                }
            });
    }

    @Override
    public void addMovieToFavourite(Movie movie) {
        try {
            mMovieRepository.addMovieToLocal(movie, new DbCallBack() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                }
            });
            mView.onAddFavouriteSuccess(movie);
        } catch (Exception e) {
            mView.onAddFavouriteFailed();
        }
    }

    @Override
    public void deleteMovieFromFavourite(Movie movie) {
        try {
            mMovieRepository.deleteMovieFromLocal(movie, new DbCallBack() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                }
            });
            mView.onDeleteFavouriteSuccess(movie);
        } catch (Exception e) {
            e.printStackTrace();
            mView.onDeleteFavouriteFailed();
        }
    }

    @Override
    public boolean checkMovieFavouriteExisting(String movieId) {
        try {
            boolean isFavourite = mMovieRepository.isFavouriteMovie(movieId, new DbCallBack() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                }
            });
            mView.isFavouriteMovie(isFavourite);
            return isFavourite;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void loadAfterNetworkChange(String movieId) {
        if(!mIsTralerSuccess){
            loadTrailerByMovieId(movieId);
        }
        if(!mIsCreditSuccess){
            loadCreditByMovieId(movieId);
        }
        if(!mIsProductionSuccess){
            loadProductionsByMovieId(movieId);
        }
    }
}
