package com.ptit.btl.moviedb.util;

import android.os.AsyncTask;

import com.ptit.btl.moviedb.data.model.Genre;
import com.ptit.btl.moviedb.data.source.GenreDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class FetchGenreFromUrl extends AsyncTask<String, Void, List<Genre>> {
    private GenreDataSource.LoadGenresCallback mCallback;

    public FetchGenreFromUrl(
        GenreDataSource.LoadGenresCallback callback) {
        mCallback = callback;
    }

    @Override
    protected List<Genre> doInBackground(String... strings) {
        try {
            return RequestAPIUtils
                .parseJsonToGenre(RequestAPIUtils.getJsonStringFromUrl(strings[0]));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Genre> genres) {
        if (mCallback == null) return;
        if (genres == null) {
            mCallback.onDataNotAvailable();
        } else {
            mCallback.onGenresLoaded(genres);
        }
    }
}
