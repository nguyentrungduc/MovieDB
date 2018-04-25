package com.ptit.btl.moviedb.screen.home;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;
import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.Genre;
import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.repository.UserRepository;
import com.ptit.btl.moviedb.data.source.local.MoviesDatabaseHelper;
import com.ptit.btl.moviedb.data.source.local.UserLocalDataSource;
import com.ptit.btl.moviedb.screen.BaseActivity;
import com.ptit.btl.moviedb.screen.detail.DetailActivity;
import com.ptit.btl.moviedb.screen.movies.MoviesByGenreActivity;
import com.ptit.btl.moviedb.screen.movies.MoviesBySearchActivity;
import com.ptit.btl.moviedb.util.EndlessRecyclerOnScrollListener;
import com.ptit.btl.moviedb.util.NetworkReceiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class HomeActivity extends BaseActivity implements HomeContract.View {
    private HomeContract.Presenter mPresenter;
    private HomeAdapter mPopularMoviesAdapter, mNowPlayingMoviesAdapter,
        mUpcomingMoviesAdapter, mTopRateMoviesAdapter;
    private HomeGenresAdapter mHomeGenresAdapter;
    private ProgressBar mProgressBarPopular, mProgressBarNowPlaying,
        mProgressBarUpcoming, mProgressBarTopRate, mProgressBarGenres;
    private EndlessRecyclerOnScrollListener mPopularOnScrollListener,
        mNowPlayingOnScrollListener, mUpcomingOnScrollListener, mTopRateOnScrollListener;
    CallbackManager mCallbackManager;
    LoginButton mLoginButton;
    private static final String TAG = HomeActivity.class.toString();

    public static Intent getInstance(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_home);
        MoviesDatabaseHelper.getInstance(this);
        mPresenter = new HomePresenter(getMovieRepository(),
                new UserRepository(UserLocalDataSource.getInstance(this)));
        mPresenter.setView(this);
        mPresenter.loadUser();
        initMoviesAdapters();
        initLayoutPopular();
        initLayoutNowPlaying();
        initLayoutUpcoming();
        initLayoutTopRate();
        initLayoutGenres();
        initToolbar();
        loadMovies();
        initNetworkBroadcast();
      //  mPresenter.logOut();
        mLoginButton = findViewById(R.id.loginButton);
        onLoginFacebook();

    }

    private void initMoviesAdapters() {
        HomeAdapter.LoadAdapterDataCallback callback =
            new HomeAdapter.LoadAdapterDataCallback() {
                @Override
                public void onItemClick(final Movie movie) {
                    startActivity(
                        DetailActivity.getInstance(getApplicationContext(), movie));
                }
            };
        mPopularMoviesAdapter = new HomeAdapter(this, callback);
        mNowPlayingMoviesAdapter = new HomeAdapter(this, callback);
        mUpcomingMoviesAdapter = new HomeAdapter(this, callback);
        mTopRateMoviesAdapter = new HomeAdapter(this, callback);
        mHomeGenresAdapter = new HomeGenresAdapter(this,
            new HomeGenresAdapter.LoadGenresAdapterCallback() {
                @Override
                public void onItemClick(Genre genre) {
                    startActivity(
                        MoviesByGenreActivity.getInstance(getApplicationContext(), genre));
                }
            });
    }

    private void initLayoutPopular() {
        View include = findViewById(R.id.include_popular);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_popular);
        mProgressBarPopular = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mPopularMoviesAdapter);
        mPopularOnScrollListener = new EndlessRecyclerOnScrollListener(
            new EndlessRecyclerOnScrollListener.LoadMoreMovies() {
                @Override
                public void loadMoreMovies() {
                    mPresenter.loadPopularMovies();
                    mProgressBarPopular.setVisibility(View.VISIBLE);
                }
            });
        recyclerView.addOnScrollListener(mPopularOnScrollListener);
    }

    private void initLayoutNowPlaying() {
        View include = findViewById(R.id.include_now_playing);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_now_playing);
        mProgressBarNowPlaying = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mNowPlayingMoviesAdapter);
        mNowPlayingOnScrollListener = new EndlessRecyclerOnScrollListener(
            new EndlessRecyclerOnScrollListener.LoadMoreMovies() {
                @Override
                public void loadMoreMovies() {
                    mPresenter.loadNowPlayingMovies();
                    mProgressBarNowPlaying.setVisibility(View.VISIBLE);
                }
            });
        recyclerView.addOnScrollListener(mNowPlayingOnScrollListener);
    }

    private void initLayoutUpcoming() {
        View include = findViewById(R.id.include_upcoming);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_upcoming);
        mProgressBarUpcoming = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mUpcomingMoviesAdapter);
        mUpcomingOnScrollListener = new EndlessRecyclerOnScrollListener(
            new EndlessRecyclerOnScrollListener.LoadMoreMovies() {
                @Override
                public void loadMoreMovies() {
                    mPresenter.loadUpcomingMovies();
                    mProgressBarUpcoming.setVisibility(View.VISIBLE);
                }
            });
        recyclerView.addOnScrollListener(mUpcomingOnScrollListener);
    }

    private void initLayoutTopRate() {
        View include = findViewById(R.id.include_top_rate);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_top_rate);
        mProgressBarTopRate = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mTopRateMoviesAdapter);
        mTopRateOnScrollListener = new EndlessRecyclerOnScrollListener(
            new EndlessRecyclerOnScrollListener.LoadMoreMovies() {
                @Override
                public void loadMoreMovies() {
                    mPresenter.loadTopRateMovies();
                    mProgressBarTopRate.setVisibility(View.VISIBLE);
                }
            });
        recyclerView.addOnScrollListener(mTopRateOnScrollListener);
    }

    private void initLayoutGenres() {
        View include = findViewById(R.id.include_genres);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_genres);
        mProgressBarGenres = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mHomeGenresAdapter);
    }

    private void initToolbar() {
        View include = findViewById(R.id.toolbar);
        final SearchView searchView = include.findViewById(R.id.search_home);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.setQuery("", false);
                searchView.setIconified(true);
                startActivity(MoviesBySearchActivity
                    .getInstance(getApplicationContext(), query));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });
        ImageView imv = include.findViewById(R.id.imv_user);
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onClickUser();
            }
        });
    }

    private void loadMovies() {
        mPresenter.loadPopularMovies();
        mPresenter.loadNowPlayingMovies();
        mPresenter.loadUpcomingMovies();
        mPresenter.loadTopRateMovies();
        mPresenter.loadGenresMovies();
        mProgressBarPopular.setVisibility(View.VISIBLE);
        mProgressBarNowPlaying.setVisibility(View.VISIBLE);
        mProgressBarTopRate.setVisibility(View.VISIBLE);
        mProgressBarUpcoming.setVisibility(View.VISIBLE);
        mProgressBarGenres.setVisibility(View.VISIBLE);
    }

    private void initNetworkBroadcast() {
        initNetworkBroadcastReceiver(new NetworkReceiver.NetworkStateCallback() {
            @Override
            public void onNetworkConnected() {
                mPresenter.loadAfterNetworkChange();
            }

            @Override
            public void onNetworkDisconnected() {
                Snackbar.make(findViewById(R.id.home_parent), R.string.home_check_network,
                    Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onGetPopularMoviesSuccess(List<Movie> movies) {
        mPopularOnScrollListener.setLoadingStatus(false);
        mProgressBarPopular.setVisibility(View.GONE);
        mPopularMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetNowPlayingMoviesSuccess(List<Movie> movies) {
        mNowPlayingOnScrollListener.setLoadingStatus(false);
        mProgressBarNowPlaying.setVisibility(View.GONE);
        mNowPlayingMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetUpcomingMoviesSuccess(List<Movie> movies) {
        mUpcomingOnScrollListener.setLoadingStatus(false);
        mProgressBarUpcoming.setVisibility(View.GONE);
        mUpcomingMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetTopRateMoviesSuccess(List<Movie> movies) {
        mTopRateOnScrollListener.setLoadingStatus(false);
        mProgressBarTopRate.setVisibility(View.GONE);
        mTopRateMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetGenresSuccess(List<Genre> genres) {
        mProgressBarGenres.setVisibility(View.GONE);
        mHomeGenresAdapter.updateData(genres);
    }

    @Override
    public void onGetPopularMoviesFailed() {
        mPopularOnScrollListener.setLoadingStatus(false);
    }

    @Override
    public void onGetNowPlayingMoviesFailed() {
        mNowPlayingOnScrollListener.setLoadingStatus(false);
    }

    @Override
    public void onGetUpcomingMoviesFailed() {
        mUpcomingOnScrollListener.setLoadingStatus(false);
    }

    @Override
    public void onGetTopRateMoviesFailed() {
        mTopRateOnScrollListener.setLoadingStatus(false);
    }

    @Override
    public void onGetGenresMoviesFailed() {
        Toast.makeText(this, R.string.home_load_genres_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadUserSucess() {


    }

    @Override
    public void onLoadUserFailed() {

    }

    @Override
    public void onLoginFacebook() {

        Log.d(TAG, "ddd");
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "sc");
                Log.d(TAG, loginResult.getAccessToken().getToken() +" "+  loginResult.getAccessToken().getUserId());

                final GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object,
                                                    GraphResponse response) {

                            }
                        });



            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    public void onLoginFacebookFailed() {

    }

    @Override
    public void onLoginFacebookCanceled() {

    }

    @Override
    public void openUserScreen() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
