package com.ptit.btl.moviedb.data.source;

import com.ptit.btl.moviedb.data.model.User;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */

public interface UserDataSource {
    interface  LocalDataSource {
        void saveUser(User user);

        User getUser();

        void deleteUser();

        void clearCache();
    }

    interface RemoteDataSource {
    }
}
