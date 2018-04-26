package com.ptit.btl.moviedb.data.source.local;

import android.util.Log;

import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.source.DbCallBack;
import com.ptit.btl.moviedb.data.source.MovieDataSource;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ADMIN on 4/26/2018.
 */

public class MovieLocalDataSourceR implements MovieDataSource.LocalDataSource {
    private Realm mRealm;

    public MovieLocalDataSourceR() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void addMovieToLocal(final Movie movie, final DbCallBack dbCallBack) throws Exception {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyFromRealm(movie);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                dbCallBack.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                dbCallBack.onError();
            }
        });

    }

    @Override
    public void deleteMovieFromLocal(final Movie movie, final DbCallBack dbCallBack) throws Exception {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmResults<Movie> result = bgRealm.where(Movie.class).equalTo("mId",
                        movie.getId()).findAll();
                result.deleteAllFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                dbCallBack.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                dbCallBack.onError();
            }
        });


    }

    @Override
    public List<Movie> getMoviesFromLocal(final DbCallBack dbCallBack) throws Exception {
        return mRealm.where(Movie.class).findAll();
    }

    @Override
    public boolean isFavouriteMovie(String movieId, DbCallBack dbCallBack) throws Exception {
        return mRealm.where(Movie.class).equalTo("mId", movieId).findAll() != null;
    }
}
