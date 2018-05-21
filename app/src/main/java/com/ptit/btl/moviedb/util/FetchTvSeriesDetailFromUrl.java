package com.ptit.btl.moviedb.util;

import android.os.AsyncTask;

import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.data.source.TvDataSource;
import com.ptit.btl.moviedb.data.source.remote.TvSeriesRemoteDataSource;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public class FetchTvSeriesDetailFromUrl extends AsyncTask<String, Void, TvSeries> {

    private TvDataSource.LoadTvSeriesDetailCallback callback;

    public FetchTvSeriesDetailFromUrl(TvDataSource.LoadTvSeriesDetailCallback callback) {
        this.callback = callback;
    }

    @Override
    protected TvSeries doInBackground(String... strings) {
        try {
            return RequestAPIUtils
                    .parseJsonToTvDetail(RequestAPIUtils.getJsonStringFromUrl(strings[0]));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(TvSeries tvSeries) {
        super.onPostExecute(tvSeries);
        if (callback == null) {
            return;
        }
        if (tvSeries == null) {
            callback.dataNotAvailable();
            return;
        }
        callback.onTvLoaded(tvSeries);
    }
}
