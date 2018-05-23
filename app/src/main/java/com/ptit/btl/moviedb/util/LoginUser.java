package com.ptit.btl.moviedb.util;

import android.os.AsyncTask;
import android.util.Log;

import com.ptit.btl.moviedb.data.model.User;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.data.source.CreditDataSource;
import com.ptit.btl.moviedb.data.source.UserDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by ADMIN on 5/22/2018.
 */

public class LoginUser extends AsyncTask<String, Void, User> {
    UserDataSource.GetUserCallback mCallback;

    public LoginUser(
            UserDataSource.GetUserCallback callback) {
        mCallback = callback;
    }

    @Override
    protected User doInBackground(String... strings) {
        try {
            return RequestAPIUtils.parseJsonToUser(
                    RequestAPIUtils.getJsonStringFromUrl(strings[0]));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        if (mCallback == null) return;
        if (user == null) {
            mCallback.onFailed();
        } else {
            mCallback.onSucess(user);
        }
    }
}
