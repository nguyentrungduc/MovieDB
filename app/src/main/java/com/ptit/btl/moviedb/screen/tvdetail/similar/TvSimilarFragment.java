package com.ptit.btl.moviedb.screen.tvdetail.similar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.screen.tv.TvHomeActivity;
import com.ptit.btl.moviedb.screen.tv.TvSeriesAdapter;

import java.util.List;

/**
 * Created by CuongHQ on 5/22/2018.
 */

public class TvSimilarFragment extends Fragment implements TvSimilarContract.View{

    public static TvSimilarFragment newInstance(TvSeries tvSeries) {
        TvSimilarFragment fragment = new TvSimilarFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("TV_SERIES", tvSeries);
        fragment.setArguments(bundle);
        return fragment;
    }

    public TvSimilarFragment() {
    }

    private RecyclerView rvsimilar;
    private ProgressBar pbSimilar;
    private TvSimilarPresenter presenter;
    private TvSeriesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tv_similar, container, false);
        initView(view);
        initSimilarSeries();
        return view;
    }

    private void initSimilarSeries() {
        presenter.getSimilarTvShow(series.getId());
    }

    private TvSeries series;

    private void initView(View view) {
        rvsimilar = view.findViewById(R.id.rv_similar);
        pbSimilar = view.findViewById(R.id.pb_similar);

        presenter = new TvSimilarPresenter(TvSimilarFragment.this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            series = (TvSeries) bundle.getSerializable("TV_SERIES");
        }

        adapter = new TvSeriesAdapter();
        rvsimilar.setLayoutManager(new GridLayoutManager(TvSimilarFragment.this.getContext(), 2));
        rvsimilar.setAdapter(adapter);
    }

    @Override
    public void onLoadedSimilarTvShows(List<TvSeries> tvSeries) {
        Log.d("onLoadedSimilarTvShows", tvSeries.toString());
        pbSimilar.setVisibility(View.GONE);
        adapter.setTvSeries(tvSeries);
    }

    @Override
    public void onLoadSimilarTvShowsFailed() {
        Log.d("onLoadSimFailed", "abc");
        pbSimilar.setVisibility(View.GONE);
    }
}
