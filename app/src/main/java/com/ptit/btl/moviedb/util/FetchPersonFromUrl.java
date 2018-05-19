package com.ptit.btl.moviedb.util;

import android.os.AsyncTask;

import com.ptit.btl.moviedb.data.model.person.Person;
import com.ptit.btl.moviedb.data.source.PersonDataSource;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by CuongHQ on 5/19/2018.
 */

public class FetchPersonFromUrl extends AsyncTask<String, Void, Person> {

    private PersonDataSource.LoadPersonCallback callback;

    public FetchPersonFromUrl(PersonDataSource.LoadPersonCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Person doInBackground(String... strings) {
        try {
            return RequestAPIUtils
                    .parseJsonToPerson(RequestAPIUtils.getJsonStringFromUrl(strings[0]));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Person person) {
        if (callback == null) return;
        if (person == null) {
            callback.dataNotAvailable();
        } else {
            callback.onLoadedPersonInformation(person);
        }
    }
}
