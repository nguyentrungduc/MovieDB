package com.ptit.btl.moviedb.data.source.remote;

import com.ptit.btl.moviedb.data.source.UserDataSource;
import com.ptit.btl.moviedb.util.LoginUser;

/**
 * Created by ADMIN on 5/23/2018.
 */

public class UserRemoteDataSource implements UserDataSource.RemoteDataSource {

    private static UserRemoteDataSource instance;

    public static UserRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new UserRemoteDataSource();
        }
        return instance;
    }
    @Override
    public void login(String username, String password, UserDataSource.GetUserCallback callback) {
        new LoginUser(callback).execute(
                getURL(username, password));

    }

    public static String getURL(String username, String password ) {
        return "https://movie-shelff.herokuapp.com/api/user?username=" + username + "&password=" + password;
    }
}
