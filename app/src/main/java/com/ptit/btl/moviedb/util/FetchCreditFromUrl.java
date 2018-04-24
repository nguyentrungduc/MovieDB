package com.ptit.btl.moviedb.util;

import android.os.AsyncTask;

import com.ptit.btl.moviedb.data.model.credit.Credit;
import com.ptit.btl.moviedb.data.source.CreditDataSource;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by vungoctuan on 3/5/18.
 */
public class FetchCreditFromUrl extends AsyncTask<String, Void, Credit> {
    CreditDataSource.LoadProductionsCallback mCallback;

    public FetchCreditFromUrl(
        CreditDataSource.LoadProductionsCallback callback) {
        mCallback = callback;
    }

    @Override
    protected Credit doInBackground(String... strings) {
        try {
            return RequestAPIUtils.parseJsonToCredit(
                RequestAPIUtils.getJsonStringFromUrl(strings[0]));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Credit credit) {
        if (mCallback == null) return;
        if (credit == null) {
            mCallback.onDataNotAvailable();
        } else {
            mCallback.onCreditLoaded(credit);
        }
    }
}
