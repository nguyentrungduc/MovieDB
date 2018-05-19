package com.ptit.btl.moviedb.util;

import android.os.AsyncTask;

import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.source.PersonDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by CuongHQ on 5/19/2018.
 */

public class FetchMovieFromUrl extends AsyncTask<String, Void, List<Movie>> {

    private PersonDataSource.LoadRelatedMoviesCallback callback;

    public FetchMovieFromUrl(PersonDataSource.LoadRelatedMoviesCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Movie> doInBackground(String... strings) {
        try {
            return RequestAPIUtils
                    .parseJsonToMovies(RequestAPIUtils.getJsonStringFromUrl(strings[0]));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        if (callback == null) {
            return;
        }
        if (movies == null || movies.size() == 0) {
            callback.dataNotAvailable();
            return;
        }
        callback.onLoadedRelatedMovie(movies);
    }
}
