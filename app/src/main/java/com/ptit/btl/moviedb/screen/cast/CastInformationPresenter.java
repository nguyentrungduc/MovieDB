package com.ptit.btl.moviedb.screen.cast;

import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.model.person.Person;
import com.ptit.btl.moviedb.data.repository.PersonRepository;
import com.ptit.btl.moviedb.data.source.PersonDataSource;
import com.ptit.btl.moviedb.data.source.remote.PersonRemoteDataSource;

import java.util.List;

/**
 * Created by CuongHQ on 5/18/2018.
 */

public class CastInformationPresenter implements CastInformationContract.Presenter{

    private CastInformationContract.View view;
    private PersonRepository personRepository;

    public CastInformationPresenter() {
        personRepository = PersonRepository.getInstance(PersonRemoteDataSource.getInstance());
    }

    public void setView(CastInformationContract.View view) {
        this.view = view;
    }
    private boolean isLoadPersonInforSuccess, isLoadPersonImagesSuccess, isLoadPersonRelatedMovieSuccess;

    @Override
    public void loadPersonInformationByPersonId(String personId) {
        isLoadPersonInforSuccess = false;
        personRepository.getPersonInformation(personId, new PersonDataSource.LoadPersonCallback() {
            @Override
            public void onLoadedPersonInformation(Person person) {
                isLoadPersonImagesSuccess = true;
                view.loadPersonInformationSuccess(person);
            }

            @Override
            public void dataNotAvailable() {
                view.loadPersonInformationFailed();
            }
        });
    }

    @Override
    public void loadPersonImagesByPersonId(String personId) {
        isLoadPersonImagesSuccess = false;
        personRepository.getImages(personId, new PersonDataSource.LoadPersonImagesCallback() {
            @Override
            public void onLoadedPersonImages(List<String> images) {
                isLoadPersonImagesSuccess = true;
                view.loadPersonImagesSuccess(images);
            }

            @Override
            public void dataNotAvailable() {
                view.loadPersonImagesFailed();
            }
        });
    }

    @Override
    public void loadPersonRelatedMovieByPersonId(String personId) {
        isLoadPersonRelatedMovieSuccess = false;
        personRepository.getRelatedMovie(personId, new PersonDataSource.LoadRelatedMoviesCallback() {
            @Override
            public void onLoadedRelatedMovie(List<Movie> movies) {
                isLoadPersonRelatedMovieSuccess = true;
                view.loadPersonRelatedMovieSuccess(movies);
            }

            @Override
            public void dataNotAvailable() {
                view.loadPersonRelatedMovieFailed();
            }
        });
    }
}
