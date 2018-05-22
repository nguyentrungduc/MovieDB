package com.ptit.btl.moviedb.screen.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.screen.BaseActivity;
import com.ptit.btl.moviedb.screen.home.HomeActivity;
import com.ptit.btl.moviedb.screen.login.LoginActivity;
import com.ptit.btl.moviedb.util.Constant;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 25/4/18.
 */
public class SplashActivity extends BaseActivity implements SplashContract.View {
    private SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mPresenter = new SplashPresenter();
        mPresenter.setView(this);
        mPresenter.loadData();
    }

    @Override
    public void onLoadingSuccess() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(LoginActivity.getInstance(
                    getApplicationContext()));
                finish();
            }
        }, Constant.SPLASH_TIMEOUT);
    }
}
