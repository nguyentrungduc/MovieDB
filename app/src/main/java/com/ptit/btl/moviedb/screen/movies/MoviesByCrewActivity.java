package com.ptit.btl.moviedb.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ptit.btl.moviedb.data.model.credit.Crew;
import com.ptit.btl.moviedb.util.Constant;

/**
 * Created by admin on 25/4/18.
 */
public class MoviesByCrewActivity extends MoviesActivity {
    public static Intent getInstance(Context context, Crew crew) {
        Intent intent = new Intent(context, MoviesByCrewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.BUNDLE_PEOPLE_ID, crew.getId());
        intent.putExtra(Constant.BUNDLE_PEOPLE_NAME, crew.getName());
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.loadMovieFromApi(
            Constant.ApiRequestUrl.API_MOVIES_BY_PEOPLE_REQUEST,
            getIntent().getStringExtra(Constant.BUNDLE_PEOPLE_ID));
    }
}
