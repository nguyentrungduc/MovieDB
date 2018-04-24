package com.ptit.btl.moviedb.screen.youtube;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.Trailer;
import com.ptit.btl.moviedb.util.Constant;

/**
 * Created by admin on 25/4/18.
 */
public class YoutubeActivity extends YouTubeBaseActivity
    implements YouTubePlayer.OnInitializedListener {
    public static Intent getInstance(Context context, Trailer trailer) {
        Intent intent = new Intent(context, YoutubeActivity.class);
        intent.putExtra(Constant.BUNDLE_TRAILER_KEY, trailer.getKey());
        return intent;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_youtube);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player);
       // youTubePlayerView.initialize(Constant.API_KEY_YOUTUBE, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(getIntent()
            .getStringExtra(Constant.BUNDLE_TRAILER_KEY));
        youTubePlayer.setShowFullscreenButton(false);
        youTubePlayer.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        Toast
            .makeText(this, getString(R.string.toast_failed) +
                youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
    }
}
