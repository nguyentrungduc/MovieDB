package com.ptit.btl.moviedb.util;

import android.os.AsyncTask;

import com.ptit.btl.moviedb.data.source.PersonDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by CuongHQ on 5/19/2018.
 */

public class FetchImagesFromUrl extends AsyncTask<String, Void, List<String>> {

    private PersonDataSource.LoadPersonImagesCallback callback;

    public FetchImagesFromUrl(PersonDataSource.LoadPersonImagesCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<String> doInBackground(String... strings) {
        try {
            return RequestAPIUtils
                    .parseJsonToImages(RequestAPIUtils.getJsonStringFromUrl(strings[0]));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        super.onPostExecute(strings);
        if (callback == null)
            return;
        if (strings == null) {
            callback.dataNotAvailable();
            return;
        }
        callback.onLoadedPersonImages(strings);
    }
}
