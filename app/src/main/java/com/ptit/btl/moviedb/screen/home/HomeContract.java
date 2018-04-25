package com.ptit.btl.moviedb.screen.home;

import com.ptit.btl.moviedb.data.model.Genre;
import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.screen.BasePresenter;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class HomeContract {
    public interface View {
        void onGetPopularMoviesSuccess(List<Movie> movies);

        void onGetNowPlayingMoviesSuccess(List<Movie> movies);

        void onGetUpcomingMoviesSuccess(List<Movie> movies);

        void onGetTopRateMoviesSuccess(List<Movie> movies);

        void onGetGenresSuccess(List<Genre> genres);

        void onGetPopularMoviesFailed();

        void onGetNowPlayingMoviesFailed();

        void onGetUpcomingMoviesFailed();

        void onGetTopRateMoviesFailed();

        void onGetGenresMoviesFailed();

        void onLoadUserSucess();

        void onLoadUserFailed();

        void onLoginFacebook();

        void onLoginFacebookFailed();

        void onLoginFacebookCanceled();

        void openUserScreen();
    }

    public interface Presenter extends BasePresenter<View> {
        void loadPopularMovies();

        void loadNowPlayingMovies();

        void loadUpcomingMovies();

        void loadTopRateMovies();

        void loadGenresMovies();

        void loadAfterNetworkChange();

        void loadUser();

        void onClickUser();

        void checkLogin();

        void logOut();
    }
}
