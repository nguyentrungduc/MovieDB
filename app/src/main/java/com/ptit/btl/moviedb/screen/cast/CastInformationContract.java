package com.ptit.btl.moviedb.screen.cast;

import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.model.Production;
import com.ptit.btl.moviedb.data.model.Trailer;
import com.ptit.btl.moviedb.data.model.credit.Credit;
import com.ptit.btl.moviedb.data.model.person.Person;
import com.ptit.btl.moviedb.screen.BasePresenter;
import com.ptit.btl.moviedb.screen.detail.DetailContract;

import java.util.List;

/**
 * Created by CuongHQ on 5/18/2018.
 */

public interface CastInformationContract {
    interface Presenter {
        void loadPersonInformationByPersonId(String personId);

        void loadPersonImagesByPersonId(String personId);

        void loadPersonRelatedMovieByPersonId(String personId);

    }

    interface View {
        void loadPersonInformationSuccess(Person person);

        void loadPersonImagesSuccess(List<String> images);

        void loadPersonRelatedMovieSuccess(List<Movie> movies);

        void loadPersonInformationFailed();

        void loadPersonImagesFailed();

        void loadPersonRelatedMovieFailed();
    }

}
