package com.ptit.btl.moviedb.screen.tvdetail.tvseason;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.tv.Season;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;

import java.util.List;

/**
 * Created by CuongHQ on 5/22/2018.
 */

public class TVSeasonFragment extends Fragment implements TvSeasonContract.View{

    public static TVSeasonFragment getInstance(TvSeries tvSeries) {
        TVSeasonFragment fragment = new TVSeasonFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("TV_SERIES", tvSeries);
        fragment.setArguments(bundle);
        return fragment;
    }

    public TVSeasonFragment() {

    }

    private ProgressBar pbSeason;
    private RecyclerView rvSeason;
    private TvSeries series;

    private TvSeasonPresenter presenter;
    private TvSeasonAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tv_season, container, false);
        initView(view);
        initTvSeries();
        return view;
    }

    private void initTvSeries() {
        presenter.getSeason(series.getId());
    }

    private void initView(View view) {
        pbSeason = view.findViewById(R.id.pb_season);
        rvSeason = view.findViewById(R.id.rv_season);

        Bundle bundle = getArguments();
        if (bundle != null) {
            series = (TvSeries) bundle.getSerializable("TV_SERIES");
        }

        presenter = new TvSeasonPresenter(TVSeasonFragment.this);
        adapter = new TvSeasonAdapter();
        rvSeason.setAdapter(adapter);
        rvSeason.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onLoadedSeason(List<Season> seasons) {
        adapter.setSeasons(seasons);
        pbSeason.setVisibility(View.GONE);
    }

    @Override
    public void onLoadSeasonFailed() {
        pbSeason.setVisibility(View.GONE);
    }
}
