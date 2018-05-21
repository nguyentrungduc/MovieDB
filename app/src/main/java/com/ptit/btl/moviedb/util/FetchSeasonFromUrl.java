package com.ptit.btl.moviedb.util;

import android.os.AsyncTask;

import com.ptit.btl.moviedb.data.model.tv.Season;
import com.ptit.btl.moviedb.data.source.TvDataSource;
import com.ptit.btl.moviedb.data.source.remote.TvSeriesRemoteDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by CuongHQ on 5/22/2018.
 */

public class FetchSeasonFromUrl extends AsyncTask<String, Void, List<Season>> {

    private TvDataSource.LoadSeasonCallback callback;

    public FetchSeasonFromUrl(TvDataSource.LoadSeasonCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Season> doInBackground(String... strings) {
        try {
            return RequestAPIUtils
                    .parseJsonToSeason(RequestAPIUtils.getJsonStringFromUrl(strings[0]));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Season> seasons) {
        super.onPostExecute(seasons);
        if (callback == null) {
            return;
        }
        if (seasons == null || seasons.size() == 0) {
            callback.dataNotAvailable();
            return;
        }
        callback.onSeasonLoaded(seasons);
    }
}
