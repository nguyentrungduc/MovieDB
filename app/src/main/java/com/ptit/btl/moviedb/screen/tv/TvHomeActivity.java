package com.ptit.btl.moviedb.screen.tv;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.screen.BaseActivity;
import com.ptit.btl.moviedb.screen.tv.TvSeriesAdapter.TvSeriesClick;
import com.ptit.btl.moviedb.screen.tvdetail.TvDetailActivity;
import com.ptit.btl.moviedb.screen.tvlist.TvListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public class TvHomeActivity extends BaseActivity implements TvHomeContract.View, TvSeriesClick{
    private static final String TAG = TvHomeActivity.class.toString();

    private ImageView imv;
    private CallbackManager callback;
    private LoginButton loginButton;
    private TvHomePresenter presenter;
    private ProgressBar pbPopular, pbTopRated;

    private TvSeriesAdapter popularAdapter, topRateAdapter;
    private TextView tvViewMorePopular, tvViewMoreTopRated;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);
        presenter = new TvHomePresenter(TvHomeActivity.this);
        initToolbar();
        initPopularTvSeries();
        initTopRateTvSeries();
        loadTvSeries();
    }

    private void initTopRateTvSeries() {
        View include = findViewById(R.id.include_top_rate);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_top_rate);
        pbTopRated = include.findViewById(R.id.progressbar_recycler);
        tvViewMoreTopRated = include.findViewById(R.id.txt_action);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        topRateAdapter = new TvSeriesAdapter();
        topRateAdapter.setCallback(TvHomeActivity.this);
        recyclerView.setAdapter(topRateAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(TvHomeActivity.this.getApplicationContext(), 2));
    }

    private void loadTvSeries() {
        pbPopular.setVisibility(View.VISIBLE);
        presenter.loadPopularTv();

        pbTopRated.setVisibility(View.VISIBLE);
        presenter.loadTopRateTv();
    }

    private void initPopularTvSeries() {
        View include = findViewById(R.id.include_popular);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_popular);
        pbPopular = include.findViewById(R.id.progressbar_recycler);
        tvViewMorePopular = include.findViewById(R.id.txt_action);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        popularAdapter = new TvSeriesAdapter();
        popularAdapter.setCallback(TvHomeActivity.this);
        recyclerView.setAdapter(popularAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(TvHomeActivity.this.getApplicationContext(), 2));
    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
        toolbar.setTitle("TV Series");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onLoadPopularTvSuccess(final List<TvSeries> tvSeries) {
        Log.d(TAG, tvSeries.toString());
        tvViewMorePopular.setText("View more " + tvSeries.size() + " of popular TV series");
        tvViewMorePopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TvListActivity.newInstance(getApplicationContext(), (ArrayList<TvSeries>) tvSeries, "Popular"));
            }
        });
        popularAdapter.setTvSeries(tvSeries.subList(0, 4));
        pbPopular.setVisibility(View.GONE);
    }

    @Override
    public void onLoadPopularTvFailed() {
        pbPopular.setVisibility(View.GONE);
    }

    @Override
    public void onLoadTopRateTvSuccess(final List<TvSeries> tvSeries) {
        tvViewMoreTopRated.setText("View more " + tvSeries.size() + " of top rated TV series");
        tvViewMoreTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TvListActivity.newInstance(getApplicationContext(), (ArrayList<TvSeries>) tvSeries, "Top Rated"));
            }
        });
        topRateAdapter.setTvSeries(tvSeries.subList(0, 4));
        pbTopRated.setVisibility(View.GONE);
    }

    @Override
    public void onLoadTopRateTvFailed() {
        pbTopRated.setVisibility(View.GONE);
    }

    @Override
    public void onTvSeriesClick(TvSeries tvSeries) {
        startActivity(TvDetailActivity.getInstance(TvHomeActivity.this.getApplicationContext(), tvSeries));
    }
}
