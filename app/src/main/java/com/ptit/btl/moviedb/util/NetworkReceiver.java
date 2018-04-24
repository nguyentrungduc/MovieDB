package com.ptit.btl.moviedb.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by admin on 25/4/18.
 */
public class NetworkReceiver extends BroadcastReceiver{
    private NetworkStateCallback mCallback;

    public NetworkReceiver(
        NetworkStateCallback callback) {
        mCallback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getExtras() == null)
            return;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService
            (CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm != null ? cm.getActiveNetworkInfo() : null;
        if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
            mCallback.onNetworkConnected();
        }else {
            mCallback.onNetworkDisconnected();
        }
    }

    public interface NetworkStateCallback{
        void onNetworkConnected();
        void onNetworkDisconnected();
    }
}
