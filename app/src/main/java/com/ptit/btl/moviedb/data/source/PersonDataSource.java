package com.ptit.btl.moviedb.data.source;

import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.model.person.Person;

import java.util.List;

/**
 * Created by CuongHQ on 5/18/2018.
 */

public interface PersonDataSource {

    interface LoadPersonCallback{
        void onLoadedPersonInformation(Person person);

        void dataNotAvailable();
    }

    interface LoadPersonImagesCallback{
        void onLoadedPersonImages(List<String> images);

        void dataNotAvailable();
    }

    interface LoadRelatedMoviesCallback{
        void onLoadedRelatedMovie(List<Movie> movies);

        void dataNotAvailable();
    }

    interface RemoteDataSource{
        void loadPersonInformation(String personId, LoadPersonCallback callback);

        void loadPersonImages(String personId, LoadPersonImagesCallback callback);

        void loadPersonRelatedMovie(String personId, LoadRelatedMoviesCallback callback);
    }
}
