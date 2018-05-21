package com.ptit.btl.moviedb.util;

import android.os.AsyncTask;

import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.data.source.TvDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public class FetchTvSeriesFromUrl extends AsyncTask<String, Void, List<TvSeries>> {

    private TvDataSource.LoadTvCallback callback;

    public FetchTvSeriesFromUrl(TvDataSource.LoadTvCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<TvSeries> doInBackground(String... strings) {
        try {
            return RequestAPIUtils.parseJsonToTv(RequestAPIUtils.getJsonStringFromUrl(strings[0]));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(List<TvSeries> tvSeries) {
        super.onPostExecute(tvSeries);
        if (callback == null) {
            return;
        }
        if (tvSeries == null || tvSeries.size() == 0) {
            callback.dataNotAvailable();
            return;
        }
        callback.onTvLoaded(tvSeries);
    }
}
