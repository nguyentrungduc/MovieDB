package com.ptit.btl.moviedb.screen;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;

import com.ptit.btl.moviedb.data.repository.MovieRepository;
import com.ptit.btl.moviedb.data.source.local.MovieLocalDataSource;
import com.ptit.btl.moviedb.data.source.remote.MovieRemoteDataSource;
import com.ptit.btl.moviedb.util.NetworkReceiver;

/**
 * Created by admin on 25/4/18.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private NetworkReceiver mNetworkReceiver;

    @Override
    protected void onStart() {
        super.onStart();
        if (mNetworkReceiver == null) return;
        registerReceiver(mNetworkReceiver,
            new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void initNetworkBroadcastReceiver(NetworkReceiver.NetworkStateCallback callback) {
        mNetworkReceiver = new NetworkReceiver(callback);
    }

    public MovieRepository getMovieRepository() {
        return MovieRepository.getInstance(
            MovieRemoteDataSource.getInstance(),
            MovieLocalDataSource.getInstance(this)
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mNetworkReceiver == null) return;
        unregisterReceiver(mNetworkReceiver);
    }
}
