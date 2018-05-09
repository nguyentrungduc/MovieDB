package com.ptit.btl.moviedb.screen.home;

import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.ptit.btl.moviedb.data.model.Genre;
import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.model.User;
import com.ptit.btl.moviedb.data.repository.GenreRepository;
import com.ptit.btl.moviedb.data.repository.MovieRepository;
import com.ptit.btl.moviedb.data.repository.UserRepository;
import com.ptit.btl.moviedb.data.source.GenreDataSource;
import com.ptit.btl.moviedb.data.source.MovieDataSource;
import com.ptit.btl.moviedb.data.source.remote.GenreRemoteDataSource;
import com.ptit.btl.moviedb.util.Constant;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;
    private MovieRepository mMovieRepository;
    private GenreRepository mGenreRepository;
    private UserRepository mUserRepository;
    private int mPopularPage = 1;
    private int mNowPlayingPage = 1;
    private int mUpcomingPage = 1;
    private int mTopRatePage = 1;
    private User mUser;
    private CallbackManager mCallbackManager;
    private static final String TAG = HomePresenter.class.toString();
    private boolean mIsPopularSuccess, mIsNowPlayingSuccess, mIsUpcomingSuccess,
        mIsTopRateSuccess, mIsGenresSuccess;

    HomePresenter(MovieRepository movieRepository , UserRepository userRepository) {
        mMovieRepository = movieRepository;
        mGenreRepository =
            GenreRepository.getInstance(GenreRemoteDataSource.getInstance());
        mUserRepository = userRepository;

    }

    @Override
    public void setView(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void loadPopularMovies() {
        mIsPopularSuccess = false;
        mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_POPULAR,
            Constant.ApiParameter.API_URL_LANGUAGE,
            mPopularPage,
            new MovieDataSource
                .LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mPopularPage++;
                    mIsPopularSuccess = true;
                    mView.onGetPopularMoviesSuccess(movies);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onGetPopularMoviesFailed();
                }
            });
    }

    @Override
    public void loadNowPlayingMovies() {
        mIsNowPlayingSuccess = false;
        mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_NOW_PLAYING,
            Constant.ApiParameter.API_URL_LANGUAGE,
            mNowPlayingPage,
            new MovieDataSource.LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mNowPlayingPage++;
                    mIsNowPlayingSuccess = true;
                    mView.onGetNowPlayingMoviesSuccess(movies);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onGetNowPlayingMoviesFailed();
                }
            });
    }

    @Override
    public void loadUpcomingMovies() {
        mIsUpcomingSuccess = false;
        mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_UPCOMING,
            Constant.ApiParameter.API_URL_LANGUAGE,
            mUpcomingPage,
            new MovieDataSource.LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mUpcomingPage++;
                    mIsUpcomingSuccess = true;
                    mView.onGetUpcomingMoviesSuccess(movies);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onGetUpcomingMoviesFailed();
                }
            });
    }

    @Override
    public void loadTopRateMovies() {
        mIsTopRateSuccess = false;
        mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_TOP_RATED,
            Constant.ApiParameter.API_URL_LANGUAGE,
            mTopRatePage,
            new MovieDataSource.LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mTopRatePage++;
                    mIsTopRateSuccess = true;
                    mView.onGetTopRateMoviesSuccess(movies);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onGetTopRateMoviesFailed();
                }
            });
    }

    @Override
    public void loadGenresMovies() {
        mIsGenresSuccess = false;
        mGenreRepository.loadGenres(new GenreDataSource.LoadGenresCallback() {
            @Override
            public void onGenresLoaded(List<Genre> genres) {
                mIsGenresSuccess = true;
                mView.onGetGenresSuccess(genres);
            }

            @Override
            public void onDataNotAvailable() {
                mView.onGetGenresMoviesFailed();
            }
        });
    }

    //Checking if network available after reconnect, keep reload data
    @Override
    public void loadAfterNetworkChange() {
        if (!mIsPopularSuccess) {
            loadPopularMovies();
        }
        if (!mIsNowPlayingSuccess) {
            loadNowPlayingMovies();
        }
        if (!mIsTopRateSuccess) {
            loadTopRateMovies();
        }
        if (!mIsUpcomingSuccess) {
            loadUpcomingMovies();
        }
        if (!mIsGenresSuccess) {
            loadGenresMovies();
        }
    }

    @Override
    public void loadUser() {
        mUser = mUserRepository.getUser();
        if (mUser != null) {
            mView.onLoadUserSucess(mUser);
        }
        else {
            mView.onLoadUserFailed();
        }

    }

    @Override
    public void onClickUser() {

    }

    @Override
    public void checkLogin() {
    }

    @Override
    public void logOut() {
       // LoginManager.getInstance().logOut();
    }

    @Override
    public void saveUser(User user) {
        Log.d(TAG, "save"+user.toString());
        mUserRepository.saveUser(user);
        mView.onSaveUserSucess(user);
    }

}
