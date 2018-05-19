package com.ptit.btl.moviedb.data.source.remote;

import com.ptit.btl.moviedb.data.source.PersonDataSource;
import com.ptit.btl.moviedb.util.Constant;
import com.ptit.btl.moviedb.util.FetchDataFromUrl;
import com.ptit.btl.moviedb.util.FetchImagesFromUrl;
import com.ptit.btl.moviedb.util.FetchMovieFromUrl;
import com.ptit.btl.moviedb.util.FetchPersonFromUrl;

/**
 * Created by CuongHQ on 5/18/2018.
 */

public class PersonRemoteDataSource implements PersonDataSource.RemoteDataSource {

    private static PersonRemoteDataSource instance;

    public static PersonRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new PersonRemoteDataSource();
        }
        return instance;
    }

    private PersonRemoteDataSource() {

    }

    @Override
    public void loadPersonInformation(String personId, PersonDataSource.LoadPersonCallback callback) {
        new FetchPersonFromUrl(callback).execute(String.format(Constant.ApiRequestUrl.API_PERSON_INFO_REQUEST, personId));
    }

    @Override
    public void loadPersonImages(String personId, PersonDataSource.LoadPersonImagesCallback callback) {
        new FetchImagesFromUrl(callback).execute(String.format(Constant.ApiRequestUrl.API_PERSON_IMAGES_REQUEST, personId));
    }

    @Override
    public void loadPersonRelatedMovie(String personId, PersonDataSource.LoadRelatedMoviesCallback callback) {
        new FetchMovieFromUrl(callback).execute(String.format(Constant.ApiRequestUrl.API_MOVIES_BY_PEOPLE_REQUEST, personId));
    }

}
