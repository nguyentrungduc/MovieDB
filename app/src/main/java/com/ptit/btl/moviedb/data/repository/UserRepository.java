package com.ptit.btl.moviedb.data.repository;

import com.ptit.btl.moviedb.data.model.User;
import com.ptit.btl.moviedb.data.source.UserDataSource;

/**
 * Created by ADMIN on 4/24/2018.
 */

public class UserRepository implements UserDataSource.LocalDataSource, UserDataSource.RemoteDataSource {
    private UserDataSource.LocalDataSource mLocalDataSource;
    private static UserRepository sInstance;
    private UserDataSource.RemoteDataSource mRemoteDataSource;

    public static UserRepository getInstance(
            UserDataSource.LocalDataSource localDataSource, UserDataSource.RemoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new UserRepository(localDataSource, remoteDataSource);
        }
        return sInstance;
    }

    public static UserRepository getInstance(
            UserDataSource.LocalDataSource localDataSource) {
        if (sInstance == null) {
            sInstance = new UserRepository(localDataSource);
        }
        return sInstance;
    }

    public UserRepository(
            UserDataSource.LocalDataSource localDataSource, UserDataSource.RemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }
    public UserRepository(
            UserDataSource.LocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public void saveUser(User user) {
        mLocalDataSource.saveUser(user);
    }

    @Override
    public User getUser() {
        return mLocalDataSource.getUser();
    }

    @Override
    public void deleteUser() {
        mLocalDataSource.deleteUser();
    }

    @Override
    public void clearCache() {
        mLocalDataSource.clearCache();
    }

    @Override
    public void login(String username, String password, UserDataSource.GetUserCallback callback) {
        mRemoteDataSource.login(username, password, callback);
    }
}
