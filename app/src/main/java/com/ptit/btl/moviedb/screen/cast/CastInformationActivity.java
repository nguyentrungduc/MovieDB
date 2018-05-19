package com.ptit.btl.moviedb.screen.cast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.model.credit.Cast;
import com.ptit.btl.moviedb.data.model.person.Person;
import com.ptit.btl.moviedb.screen.BaseActivity;
import com.ptit.btl.moviedb.screen.detail.DetailActivity;
import com.ptit.btl.moviedb.screen.home.HomeActivity;
import com.ptit.btl.moviedb.screen.movies.MoviesActivity;
import com.ptit.btl.moviedb.util.Constant;
import com.ptit.btl.moviedb.util.ImageUtils;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by CuongHQ on 5/18/2018.
 */

public class CastInformationActivity extends BaseActivity implements CastInformationContract.View{

    private CastInformationPresenter presenter;

    private TextView tvAka;
    private TextView tvDateOfBirth;
    private TextView tvBirthPlace;
    private TextView tvBiography;
    private TextView tvWeight;
    private ImageView ivProfile;

    private RecyclerView rvImages;
    private RecyclerView rvRelatedMovies;
    private CastImageAdapter adapter;
    private CastRelatedMovieAdapter relatedMovieAdapter;

    private ProgressBar pbInfo, pbImages, pbMovie;

    public static Intent getInstance(Context context, Cast cast) {
        Intent intent = new Intent(context, CastInformationActivity.class);
        intent.putExtra(Constant.BUNDLE_PEOPLE_ID, cast.getId());
        intent.putExtra(Constant.BUNDLE_PEOPLE_NAME, cast.getName());
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);
        presenter = new CastInformationPresenter();
        presenter.setView(CastInformationActivity.this);
        initToolbar();
        initView();
        setupUI();
    }

    private void initView() {
        tvAka = findViewById(R.id.tv_aka);
        tvBiography = findViewById(R.id.tv_description);
        tvBirthPlace = findViewById(R.id.tv_born_at);
        tvDateOfBirth = findViewById(R.id.tv_birthday);
        tvWeight = findViewById(R.id.tv_weight);
        ivProfile = findViewById(R.id.iv_profile);

        rvImages = findViewById(R.id.rv_images);
        rvImages.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new CastImageAdapter();
        rvImages.setAdapter(adapter);

        rvRelatedMovies = findViewById(R.id.rv_related_movie);

        CastRelatedMovieAdapter.LoadAdapterDataCallback callback = new CastRelatedMovieAdapter.LoadAdapterDataCallback() {
            @Override
            public void onItemClick(Movie movie) {
                startActivity(
                        DetailActivity.getInstance(getApplicationContext(), movie));
            }
        };
        relatedMovieAdapter = new CastRelatedMovieAdapter(callback);
        rvRelatedMovies.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        rvRelatedMovies.setAdapter(relatedMovieAdapter);

        pbInfo = findViewById(R.id.pb_information);
        pbImages = findViewById(R.id.pb_images);
        pbMovie = findViewById(R.id.pb_related_movie);
    }

    private void setupUI() {
        String personId = getIntent().getStringExtra(Constant.BUNDLE_PEOPLE_ID);
        presenter.loadPersonInformationByPersonId(personId);
        presenter.loadPersonImagesByPersonId(personId);
        presenter.loadPersonRelatedMovieByPersonId(personId);
    }

    private void initToolbar() {
        View includeToolbar = findViewById(R.id.toolbar_result);
        Toolbar toolbar = includeToolbar.findViewById(R.id.toolbar_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
        setTitle(getString(R.string.title_loading));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setTitle(getIntent().getStringExtra(Constant.BUNDLE_PEOPLE_NAME));
    }

    @Override
    public void loadPersonInformationSuccess(Person person) {
        pbInfo.setVisibility(View.GONE);
        tvAka.append((person.getAka().size() > 0) ? person.getAka().get(0) : "");
        tvWeight.append("Unknown");
        tvDateOfBirth.append(person.getBirthDay());
        tvBirthPlace.append(person.getPlaceOfBirth());
        tvBiography.setText(person.getBiography());
        ImageUtils.loadImageFromUrl(ivProfile,  person.getProfilePicturePath(), R.drawable.poster_3);
    }

    @Override
    public void loadPersonImagesSuccess(List<String> images) {
        pbImages.setVisibility(View.GONE);
        adapter.setImages(images);
    }

    @Override
    public void loadPersonRelatedMovieSuccess(List<Movie> movies) {
        pbMovie.setVisibility(View.GONE);
        relatedMovieAdapter.setMovies(movies);
    }

    @Override
    public void loadPersonInformationFailed() {

    }

    @Override
    public void loadPersonImagesFailed() {

    }

    @Override
    public void loadPersonRelatedMovieFailed() {

    }

}
