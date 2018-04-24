package com.ptit.btl.moviedb.screen;

/**
 * Created by admin on 25/4/18.
 */
public interface BasePresenter<T> {
    void setView(T view);
    void onStart();
    void onStop();
}
