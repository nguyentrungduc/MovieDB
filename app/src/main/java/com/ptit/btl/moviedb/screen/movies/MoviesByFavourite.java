package com.ptit.btl.moviedb.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ptit.btl.moviedb.R;

/**
 * Created by admin on 25/4/18.
 */
public class MoviesByFavourite extends MoviesActivity {
    public static Intent getInstance(Context context) {
        return new Intent(context, MoviesByFavourite.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getFavouriteMovie();
        setTitle(getString(R.string.title_favourite_movies));
    }
}
