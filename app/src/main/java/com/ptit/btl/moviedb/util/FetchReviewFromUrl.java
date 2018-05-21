package com.ptit.btl.moviedb.util;

import android.os.AsyncTask;

import com.ptit.btl.moviedb.data.model.tv.Review;
import com.ptit.btl.moviedb.data.source.TvDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by CuongHQ on 5/22/2018.
 */

public class FetchReviewFromUrl extends AsyncTask<String, Void, List<Review>> {

    private TvDataSource.LoadReviewCallback callback;

    public FetchReviewFromUrl(TvDataSource.LoadReviewCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Review> doInBackground(String... strings) {
        try {
            return RequestAPIUtils
                    .parseJsonToReviewList(RequestAPIUtils.getJsonStringFromUrl(strings[0]));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Review> reviews) {
        super.onPostExecute(reviews);
        if (callback == null) {
            return;
        }
        if (reviews == null && reviews.size() == 0) {
            callback.dataNotAvailable();
            return;
        }
        callback.onReviewLoaded(reviews);
    }
}
