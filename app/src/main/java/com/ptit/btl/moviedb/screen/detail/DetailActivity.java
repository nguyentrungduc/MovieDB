package com.ptit.btl.moviedb.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.model.Production;
import com.ptit.btl.moviedb.data.model.Trailer;
import com.ptit.btl.moviedb.data.model.credit.Cast;
import com.ptit.btl.moviedb.data.model.credit.Credit;
import com.ptit.btl.moviedb.data.model.credit.Crew;
import com.ptit.btl.moviedb.screen.BaseActivity;
import com.ptit.btl.moviedb.screen.movies.MoviesByCastActivity;
import com.ptit.btl.moviedb.screen.movies.MoviesByCrewActivity;
import com.ptit.btl.moviedb.screen.movies.MoviesByProductionActivity;
import com.ptit.btl.moviedb.screen.youtube.YoutubeActivity;
import com.ptit.btl.moviedb.util.Constant;
import com.ptit.btl.moviedb.util.ImageUtils;
import com.ptit.btl.moviedb.util.NetworkReceiver;

import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class DetailActivity extends BaseActivity implements DetailContract.View,
    View.OnClickListener {
    private DetailContract.Presenter mPresenter;
    private Button mButtonExpand, mButtonPlayTrailer;
    private Button mButtonFavourite;
    private TextView mTextDetailOverview, mTextProduction, mTextCast, mTextCrew;
    private ProgressBar mProgressBarProduction, mProgressBarCast, mProgressBarCrew;
    private boolean mExpanded;
    private ProductionAdapter mProductionAdapter;
    private CastAdapter mCastAdapter;
    private CrewAdapter mCrewAdapter;
    private Movie mMovie;
    private List<Trailer> mTrailers;

    public static Intent getInstance(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Constant.BUNDLE_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mMovie = getIntent().getParcelableExtra(Constant.BUNDLE_MOVIE);
        mPresenter = new DetailPresenter(getMovieRepository());
        mPresenter.setView(this);
        initToolbar();
        initButtonComponents();
        initImageViewComponents();
        initTextViewComponents();
        initDetailAdapters();
        initLayoutProductions();
        initLayoutCasts();
        initLayoutCrews();
        loadDataFromApi();
        mPresenter.checkMovieFavouriteExisting(mMovie.getId());
        initNetworkBroadcast();
    }

    private void loadDataFromApi() {
        mProgressBarCast.setVisibility(View.VISIBLE);
        mProgressBarCrew.setVisibility(View.VISIBLE);
        mProgressBarProduction.setVisibility(View.VISIBLE);
        mPresenter.loadProductionsByMovieId(mMovie.getId());
        mPresenter.loadCreditByMovieId(mMovie.getId());
        mPresenter.loadTrailerByMovieId(mMovie.getId());
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initDetailAdapters() {
        mProductionAdapter = new ProductionAdapter(this,
            new ProductionAdapter.LoadProductionDataCallback() {
                @Override
                public void onItemProductionClicked(Production production) {
                    startActivity(MoviesByProductionActivity.getInstance(
                        getApplicationContext(),
                        production));
                }
            });
        mCastAdapter = new CastAdapter(this,
            new CastAdapter.LoadCastDataCallback() {
                @Override
                public void onItemCastClicked(Cast cast) {
                    startActivity(MoviesByCastActivity.getInstance(
                        getApplicationContext(),
                        cast));
                }
            });
        mCrewAdapter = new CrewAdapter(this,
            new CrewAdapter.LoadCrewDataCallback() {
                @Override
                public void onItemCrewClicked(Crew crew) {
                    startActivity(MoviesByCrewActivity.getInstance(
                        getApplicationContext(),
                        crew));
                }
            });
    }

    private void initLayoutProductions() {
        View include = findViewById(R.id.include_production);
        mProgressBarProduction = include.findViewById(R.id.progressbar_recycler);
        mTextProduction = include.findViewById(R.id.text_recycler_title);
        mTextProduction.setText(R.string.title_production);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mProductionAdapter);
    }

    private void initLayoutCasts() {
        View include = findViewById(R.id.include_cast);
        mProgressBarCast = include.findViewById(R.id.progressbar_recycler);
        mTextCast = include.findViewById(R.id.text_recycler_title);
        mTextCast.setText(R.string.title_cast);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mCastAdapter);
    }

    private void initLayoutCrews() {
        View include = findViewById(R.id.include_crew);
        mProgressBarCrew = include.findViewById(R.id.progressbar_recycler);
        mTextCrew = include.findViewById(R.id.text_recycler_title);
        mTextCrew.setText(R.string.title_crew);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mCrewAdapter);
    }

    private void initButtonComponents() {
        mButtonExpand = findViewById(R.id.button_expand_overview);
        mButtonExpand.setOnClickListener(this);
        mButtonPlayTrailer = findViewById(R.id.button_detail_play_trailer);
        mButtonPlayTrailer.setOnClickListener(this);
        mButtonFavourite = findViewById(R.id.button_favourite);
        mButtonFavourite.setOnClickListener(this);
    }

    private void initImageViewComponents() {
        ImageView imageDetailTrailer = findViewById(R.id.image_detail_trailer);
        ImageUtils.loadImageFromUrl(
            imageDetailTrailer,
            mMovie.getBackdropPath(),
            R.drawable.image_trailer);
        ImageView imageDetailPoster = findViewById(R.id.image_detail_movie);
        ImageUtils.loadImageFromUrl(
            imageDetailPoster,
            mMovie.getPosterPath(),
            R.drawable.image_trailer);
    }

    private void initTextViewComponents() {
        TextView textDetailName = findViewById(R.id.text_detail_name);
        textDetailName.setText(
            mMovie.getTitle()
        );
        TextView textDetailInformation = findViewById(R.id.text_detail_information);
        textDetailInformation.setText(
            String.format(
                getString(R.string.detail_activity_release),
                mMovie.getReleaseDate()
            ));
        TextView textDetailRate = findViewById(R.id.text_detail_rate);
        textDetailRate.setText(
            mMovie.getVoteAverage()
        );
        mTextDetailOverview = findViewById(R.id.text_detail_overview);
        mTextDetailOverview.setText(
            mMovie.getOverview()
        );
        mTextDetailOverview.setMaxLines(Constant.TEXT_OVERVIEW_MIN_LINES);
    }

    private void changeExpandOverview() {
        mExpanded = !mExpanded;
        String title;
        if (mExpanded) {
            mTextDetailOverview.setMaxLines(Constant.TEXT_OVERVIEW_MAX_LINES);
            title = getString(R.string.title_less);
        } else {
            mTextDetailOverview.setMaxLines(Constant.TEXT_OVERVIEW_MIN_LINES);
            title = getString(R.string.title_more);
        }
        mButtonExpand.setText(title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_expand_overview:
                changeExpandOverview();
                break;
            case R.id.button_detail_play_trailer:
                loadTrailerByYoutubeApi();
                break;
            case R.id.button_favourite:
                if (mPresenter.checkMovieFavouriteExisting(mMovie.getId())) {
                    mPresenter.deleteMovieFromFavourite(mMovie);
                } else {
                    mPresenter.addMovieToFavourite(mMovie);
                }
                break;
        }
    }

    private void loadTrailerByYoutubeApi() {
        startActivity(YoutubeActivity
            .getInstance(getApplicationContext(), mTrailers.get(0)));
    }

    @Override
    public void onLoadProductionSuccess(List<Production> productions) {
        mProgressBarProduction.setVisibility(View.GONE);
        mProductionAdapter.updateData(productions);
        mTextProduction.setText(R.string.title_popular);
    }

    @Override
    public void onLoadCreditSuccess(Credit credit) {
        mProgressBarCrew.setVisibility(View.GONE);
        mProgressBarCast.setVisibility(View.GONE);
        mTextCast.setText(R.string.title_cast);
        mTextCrew.setText(R.string.title_crew);
        mCastAdapter.updateData(credit.getCasts());
        mCrewAdapter.updateData(credit.getCrews());
    }

    @Override
    public void onLoadTrailerSuccess(List<Trailer> trailers) {
        mTrailers = trailers;
        mButtonPlayTrailer.setVisibility(View.VISIBLE);
    }

    private void initNetworkBroadcast() {
        initNetworkBroadcastReceiver(new NetworkReceiver.NetworkStateCallback() {
            @Override
            public void onNetworkConnected() {
                mPresenter.loadAfterNetworkChange(mMovie.getId());
            }

            @Override
            public void onNetworkDisconnected() {
                Snackbar.make(findViewById(R.id.detail_parent), R.string.home_check_network,
                    Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onLoadProductionFailed() {
        mTextProduction.setText(R.string.title_production_failed);
    }

    @Override
    public void onLoadCreditFailed() {
        mTextCrew.setText(R.string.title_crew_failed);
        mTextCast.setText(R.string.title_cast_failed);
    }

    @Override
    public void onLoadTrailerFailed() {
        mButtonPlayTrailer.setVisibility(View.GONE);
    }

    @Override
    public void onAddFavouriteSuccess(Movie movie) {
        Toast.makeText(this,
            String.format(getString(R.string.detail_add_favourite_success), movie.getTitle()),
            Toast.LENGTH_SHORT).show();
        mButtonFavourite.setBackgroundResource(R.drawable.ic_love_plus);
    }

    @Override
    public void onAddFavouriteFailed() {
        Toast.makeText(this,
            R.string.detail_add_favourite_failed,
            Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteFavouriteSuccess(Movie movie) {
        Toast.makeText(this,
            String.format(getString(R.string.detail_delete_movie_alert), movie.getTitle()),
            Toast.LENGTH_SHORT).show();
        mButtonFavourite.setBackgroundResource(R.drawable.ic_love_minus);
    }

    @Override
    public void onDeleteFavouriteFailed() {
        Toast.makeText(this,
            R.string.detail_delete_movie_failed,
            Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isFavouriteMovie(boolean isFavourite) {
        if (isFavourite)
            mButtonFavourite.setBackgroundResource(R.drawable.ic_love_plus);
        else
            mButtonFavourite.setBackgroundResource(R.drawable.ic_love_minus);
    }
}
