package com.ptit.btl.moviedb.data.repository;

import com.ptit.btl.moviedb.data.source.PersonDataSource;
import com.ptit.btl.moviedb.data.source.remote.PersonRemoteDataSource;

/**
 * Created by CuongHQ on 5/18/2018.
 */

public class PersonRepository {

    private static PersonRepository personRepository;
    private PersonRemoteDataSource personRemoteDataSource;

    public static PersonRepository getInstance(PersonRemoteDataSource personRemoteDataSource) {
        if (personRepository == null) {
            personRepository = new PersonRepository(personRemoteDataSource);
        }
        return personRepository;
    }

    private PersonRepository(PersonRemoteDataSource personRemoteDataSource) {
        this.personRemoteDataSource = personRemoteDataSource;
    }

    public void getPersonInformation(String personId, PersonDataSource.LoadPersonCallback callback) {
        personRemoteDataSource.loadPersonInformation(personId, callback);
    }

    public void getImages(String personId, PersonDataSource.LoadPersonImagesCallback callback) {
        personRemoteDataSource.loadPersonImages(personId, callback);
    }

    public void getRelatedMovie(String personId, PersonDataSource.LoadRelatedMoviesCallback callback) {
        personRemoteDataSource.loadPersonRelatedMovie(personId, callback);
    }
}
