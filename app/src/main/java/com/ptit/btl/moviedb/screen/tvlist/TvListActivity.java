package com.ptit.btl.moviedb.screen.tvlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.screen.BaseActivity;
import com.ptit.btl.moviedb.screen.tv.TvHomeActivity;
import com.ptit.btl.moviedb.screen.tvdetail.TvDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CuongHQ on 5/23/2018.
 */

public class TvListActivity extends BaseActivity implements TvListAdapter.TvSeriesClick{

    private ArrayList<TvSeries> tvSeries;
    private String title;

    private RecyclerView rvTvSeries;
    protected TvListAdapter adapter;

    public static Intent newInstance(Context context, ArrayList<TvSeries> tvSeries, String title) {
        Intent intent = new Intent(context, TvListActivity.class);
        intent.putExtra("TV_SERIES_LIST", tvSeries);
        intent.putExtra("TITLE", title);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_list);
        initView();
        initToolbar();
    }

    private void initView() {
        rvTvSeries = findViewById(R.id.rv_tv);
        adapter = new TvListAdapter(TvListActivity.this);
        rvTvSeries.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvTvSeries.setAdapter(adapter);
    }

    private void initToolbar() {

        tvSeries = (ArrayList<TvSeries>) getIntent().getSerializableExtra("TV_SERIES_LIST");
        title = getIntent().getStringExtra("TITLE");
        adapter.setTvSeries(tvSeries);
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
        toolbar.setTitle((title == null) ? "Tv Series" : title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onTvSeriesClick(TvSeries series) {
        startActivity(TvDetailActivity.getInstance(TvListActivity.this.getApplicationContext(), series));
    }
}
