package com.ptit.btl.moviedb.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ptit.btl.moviedb.data.model.credit.Cast;
import com.ptit.btl.moviedb.util.Constant;

/**
 * Created by admin on 25/4/18.
 */
public class MoviesByCastActivity extends MoviesActivity {
    public static Intent getInstance(Context context, Cast cast) {
        Intent intent = new Intent(context, MoviesByCastActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.BUNDLE_PEOPLE_ID, cast.getId());
        intent.putExtra(Constant.BUNDLE_PEOPLE_NAME, cast.getName());
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
