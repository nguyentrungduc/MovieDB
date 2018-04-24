package com.ptit.btl.moviedb.screen.splash;

import com.ptit.btl.moviedb.screen.BasePresenter;

/**
 * Created by admin on 25/4/18.
 */
public interface SplashContract {
    interface View {
        void onLoadingSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void loadData();
    }
}
