package com.ptit.btl.moviedb.screen.timeline;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ptit.btl.moviedb.R;


public class TimelineActivity extends AppCompatActivity {

    public static Intent getInstance(Context context) {
        return new Intent(context, TimelineActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
    }
}
