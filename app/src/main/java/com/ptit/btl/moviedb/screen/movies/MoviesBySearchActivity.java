package com.ptit.btl.moviedb.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.util.Constant;

/**
 * Created by admin on 25/4/18.
 */
public class MoviesBySearchActivity extends MoviesActivity {
    public static Intent getInstance(Context context, String query) {
        Intent intent = new Intent(context, MoviesBySearchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.BUNDLE_SEARCH_QUERY, query);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String query = getIntent().getStringExtra(Constant.BUNDLE_SEARCH_QUERY);
        mPresenter.loadMovieFromApi(
            Constant.ApiRequestUrl.API_MOVIES_BY_SEARCH,
            query);
        setTitle(String.format(getString(R.string.title_results), query));
    }
}
