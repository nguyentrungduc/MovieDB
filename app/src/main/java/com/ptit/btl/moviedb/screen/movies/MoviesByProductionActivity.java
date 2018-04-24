package com.ptit.btl.moviedb.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ptit.btl.moviedb.data.model.Production;
import com.ptit.btl.moviedb.util.Constant;

/**
 * Created by admin on 25/4/18.
 */
public class MoviesByProductionActivity extends MoviesActivity {
    public static Intent getInstance(Context context, Production production) {
        Intent intent = new Intent(context, MoviesByProductionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.BUNDLE_PEOPLE_ID, production.getId());
        intent.putExtra(Constant.BUNDLE_PEOPLE_NAME, production.getName());
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.loadMovieFromApi(
            Constant.ApiRequestUrl.API_MOVIES_BY_PRODUCTION_REQUEST,
            getIntent().getStringExtra(Constant.BUNDLE_PEOPLE_ID));
    }
}
