package com.ptit.btl.moviedb.screen.splash;

/**
 * Created by admin on 25/4/18.
 */
public class SplashPresenter implements SplashContract.Presenter {
    private SplashContract.View mView;

    @Override
    public void setView(SplashContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void loadData() {
        mView.onLoadingSuccess();
    }
}
