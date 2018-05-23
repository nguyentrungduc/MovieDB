package com.ptit.btl.moviedb.screen.tvdetail.tvinfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.credit.Cast;
import com.ptit.btl.moviedb.data.model.credit.Credit;
import com.ptit.btl.moviedb.data.model.credit.Crew;
import com.ptit.btl.moviedb.data.model.tv.Review;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.screen.cast.CastInformationActivity;
import com.ptit.btl.moviedb.screen.detail.CastAdapter;
import com.ptit.btl.moviedb.screen.detail.CrewAdapter;
import com.ptit.btl.moviedb.screen.movies.MoviesByCrewActivity;
import com.ptit.btl.moviedb.util.ImageUtils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieInformationFragment extends Fragment implements TvInfoContract.View{

    private TvSeries tvSeries;
    private TextView tvType;
    private TextView tvLanguage;
    private TextView tvGenre;
    private TextView tvCompanies;
    private ImageView ivBackdrop;
    private TextView tvOverview;
    private RecyclerView rvCreatedBy;

    private TvInfoPresenter presenter;
    private ProgressBar pbInfo, pbReview, pbCast, pbCrew;
    private CreatorAdapter adapter;
    private TvInfoReviewAdapter reviewAdapter;

    private RecyclerView rvReview;
    private TextView tvNoResult;

    private CastAdapter castAdapter;
    private CrewAdapter crewAdapter;

    public static MovieInformationFragment getInstance(TvSeries tvSeries) {
        MovieInformationFragment movieInformationFragment = new MovieInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("TV_SERIES", tvSeries);
        movieInformationFragment.setArguments(bundle);
        return movieInformationFragment;
    }

    public MovieInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie_information, container, false);
        initView(view);
        initTvSeries();
        initLayoutCasts(view);
        initLayoutCrews(view);
        return view;
    }

    private void initTvSeries() {
        pbInfo.setVisibility(View.VISIBLE);
        presenter.loadTvSeries(tvSeries.getId());

        presenter.loadReview(tvSeries.getId());

        presenter.loadCredit(tvSeries.getId());
    }

    private void initView(View view) {
        tvType = view.findViewById(R.id.tv_type);
        tvLanguage = view.findViewById(R.id.tv_language);
        tvGenre = view.findViewById(R.id.tv_genres);
        tvCompanies = view.findViewById(R.id.tv_production_companies);
        ivBackdrop = view.findViewById(R.id.iv_backdrop);
        tvOverview = view.findViewById(R.id.tv_overview);
        pbInfo = view.findViewById(R.id.pb_information);
        rvCreatedBy = view.findViewById(R.id.recycler_created_by);

        rvReview = view.findViewById(R.id.rv_reviews);
        tvNoResult = view.findViewById(R.id.tv_no_result);

        presenter = new TvInfoPresenter(MovieInformationFragment.this);
        tvSeries = (TvSeries) getArguments().getSerializable("TV_SERIES");
        adapter = new CreatorAdapter();

        rvCreatedBy.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCreatedBy.setAdapter(adapter);

        reviewAdapter = new TvInfoReviewAdapter();
        rvReview.setLayoutManager(new LinearLayoutManager(getContext()));
        rvReview.setAdapter(reviewAdapter);

        castAdapter = new CastAdapter(MovieInformationFragment.this.getContext(),
                new CastAdapter.LoadCastDataCallback() {
                    @Override
                    public void onItemCastClicked(Cast cast) {
                        Log.d("onItemCastClicked", cast.toString());
                        startActivity(CastInformationActivity.getInstance(
                                getContext(),
                                cast));
                    }
                });

        crewAdapter = new CrewAdapter(MovieInformationFragment.this.getContext(),
                new CrewAdapter.LoadCrewDataCallback() {
                    @Override
                    public void onItemCrewClicked(Crew crew) {
                        startActivity(MoviesByCrewActivity.getInstance(
                                getContext(),
                                crew));
                    }
                });

        if (tvSeries != null) {
            updateUI();
        }

    }

    private void updateUI() {
        if (tvSeries != null) {
            tvType.setText("Type: " + tvSeries.getType());
            tvLanguage.setText("Released Date: "+ tvSeries.getFirstAirDate());
            ImageUtils.loadImageFromUrlPicasso(ivBackdrop, tvSeries.getBackDropPath(), R.drawable.image_trailer);
            tvOverview.setText(tvSeries.getOverview());
            StringBuilder genre = new StringBuilder();
            if (tvSeries.getGenre() != null && tvSeries.getGenre().size() > 1) {
                for (int i = 0; i < tvSeries.getGenre().size() - 1; i++) {
                    genre.append(tvSeries.getGenre().get(i).getName()).append(", ");
                }
                genre.append(tvSeries.getGenre().get(tvSeries.getGenre().size() - 1).getName());
            }
            tvGenre.setText("Genres: " + genre);

            StringBuilder production = new StringBuilder();
            if (tvSeries.getProductions() != null && tvSeries.getProductions().size() > 1) {
                for (int i = 0; i < tvSeries.getProductions().size() - 1; i++) {
                    production.append(tvSeries.getProductions().get(i).getName() + ", ");
                }
                production.append(tvSeries.getProductions().get(tvSeries.getProductions().size() - 1).getName());
            }
            tvCompanies.setText("Production: " + production);

            adapter.updateData(tvSeries.getCreateBy());
        }
    }

    private void initLayoutCasts(View view) {
        View include = view.findViewById(R.id.include_cast);
        pbCast = include.findViewById(R.id.progressbar_recycler);
        TextView tvCastTitle = include.findViewById(R.id.text_recycler_title);
        tvCastTitle.setText(R.string.title_cast);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(castAdapter);
    }

    private void initLayoutCrews(View view) {
        View include = view.findViewById(R.id.include_crew);
        pbCrew = include.findViewById(R.id.progressbar_recycler);
        TextView tvCastTitle = include.findViewById(R.id.text_recycler_title);
        tvCastTitle.setText(R.string.title_crew);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(crewAdapter);
    }
    @Override
    public void onLoadedTvSeries(TvSeries tvSeries) {
        pbInfo.setVisibility(View.GONE);
        this.tvSeries = tvSeries;
        updateUI();
    }

    @Override
    public void onLoadTvSeriesFailed() {
        pbInfo.setVisibility(View.GONE);
    }

    @Override
    public void onLoadedReviews(List<Review> reviews) {
        reviewAdapter.setReviews(reviews);
        if (reviews.size() == 0) {
            onLoadReviewsFailed();
        }
    }

    @Override
    public void onLoadReviewsFailed() {
        rvReview.setVisibility(View.GONE);
        tvNoResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadedCredit(Credit credit) {
        Log.d("abcde", credit.toString());
        castAdapter.updateData(credit.getCasts());
        crewAdapter.updateData(credit.getCrews());
    }

    @Override
    public void onLoadedCreditFailed() {
        Log.d("abcde", "onLoadedCreditFailed");

    }
}
