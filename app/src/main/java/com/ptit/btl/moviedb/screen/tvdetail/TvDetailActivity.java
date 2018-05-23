package com.ptit.btl.moviedb.screen.tvdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.screen.BaseActivity;
import com.ptit.btl.moviedb.screen.tvdetail.similar.TvSimilarFragment;
import com.ptit.btl.moviedb.screen.tvdetail.tvinfo.MovieInformationFragment;
import com.ptit.btl.moviedb.screen.tvdetail.tvseason.TVSeasonFragment;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public class TvDetailActivity extends BaseActivity {

    public static Intent getInstance(Context context, TvSeries tvSeries) {
        Intent intent = new Intent(context, TvDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("TV_SERIES", tvSeries);
        return intent;
    }

    private FrameLayout flContainer;
    private BottomNavigationView bottomNavigationView;
    private TvSeries tv;

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);
        initToolbar();
        initView();
        initNav();
    }

    private void initNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_favorites:
                        changeFragment(MovieInformationFragment.getInstance(tv));
                        break;

                    case R.id.action_season:
                        changeFragment(TVSeasonFragment.getInstance(tv));
                        break;

                    case R.id.action_related_movie:
                        changeFragment(TvSimilarFragment.newInstance(tv, TvDetailActivity.this));
                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        flContainer = findViewById(R.id.fl_container);
        bottomNavigationView = findViewById(R.id.bottom_nav);

        changeFragment(MovieInformationFragment.getInstance(tv));
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));

        Bundle bundle = getIntent().getExtras();
        tv = (TvSeries) bundle.getSerializable("TV_SERIES");
        toolbar.setTitle(tv.getName());
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void changeActivity(TvSeries series) {
        startActivity(getInstance(getApplicationContext(), series));
    }

}
