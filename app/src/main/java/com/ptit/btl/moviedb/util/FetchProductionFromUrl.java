package com.ptit.btl.moviedb.util;

import android.os.AsyncTask;

import com.ptit.btl.moviedb.data.model.Production;
import com.ptit.btl.moviedb.data.source.ProductionDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class FetchProductionFromUrl extends AsyncTask<String, Void, List<Production>> {
    private ProductionDataSource.LoadProductionsCallback mCallback;

    public FetchProductionFromUrl(
        ProductionDataSource.LoadProductionsCallback callback) {
        mCallback = callback;
    }

    @Override
    protected List<Production> doInBackground(String... strings) {
        try {
            return RequestAPIUtils
                .parseJsonToProductions(RequestAPIUtils.getJsonStringFromUrl(strings[0])
                );
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Production> productions) {
        if (mCallback == null) return;
        if (productions == null) {
            mCallback.onDataNotAvailable();
        } else {
            mCallback.onProductionsLoaded(productions);
        }
    }
}
