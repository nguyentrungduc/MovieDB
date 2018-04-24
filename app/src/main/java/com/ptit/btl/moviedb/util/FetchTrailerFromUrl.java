package com.ptit.btl.moviedb.util;

import android.os.AsyncTask;

import com.ptit.btl.moviedb.data.model.Trailer;
import com.ptit.btl.moviedb.data.source.TrailerDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class FetchTrailerFromUrl extends AsyncTask<String, Void, List<Trailer>> {
    private TrailerDataSource.LoadTrailersCallback mCallback;

    public FetchTrailerFromUrl(
        TrailerDataSource.LoadTrailersCallback callback) {
        mCallback = callback;
    }

    @Override
    protected List<Trailer> doInBackground(String... strings) {
        try {
            return RequestAPIUtils
                .parseJsonToTrailer(RequestAPIUtils.getJsonStringFromUrl(strings[0]));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Trailer> trailers) {
        if (mCallback == null) return;
        if (trailers == null || trailers.size() == 0) {
            mCallback.onDataNotAvailable();
        } else {
            mCallback.onTrailersLoaded(trailers);
        }
    }
}
