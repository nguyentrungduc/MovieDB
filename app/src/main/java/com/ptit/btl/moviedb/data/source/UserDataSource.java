package com.ptit.btl.moviedb.data.source;

import com.ptit.btl.moviedb.data.model.User;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */

public interface UserDataSource {

    interface GetUserCallback {
        void onSucess(User user);

        void onFailed();
    }
    interface  LocalDataSource {
        void saveUser(User user);

        User getUser();

        void deleteUser();

        void clearCache();
    }

    interface RemoteDataSource {
        void login(String username, String password, GetUserCallback callback);
    }
}
