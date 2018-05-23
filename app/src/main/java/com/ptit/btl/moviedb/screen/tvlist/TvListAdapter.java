package com.ptit.btl.moviedb.screen.tvlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.Movie;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.screen.movies.MoviesAdapter;
import com.ptit.btl.moviedb.util.ImageUtils;

import java.util.List;

/**
 * Created by CuongHQ on 5/23/2018.
 */

public class TvListAdapter extends RecyclerView.Adapter<TvListAdapter.TvListViewHolder> {

    private List<TvSeries> tvSeries;

    public void setTvSeries(List<TvSeries> tvSeries) {
        this.tvSeries = tvSeries;
        notifyDataSetChanged();
    }

    private TvSeriesClick tvSeriesClick;

    public TvListAdapter(TvSeriesClick tvSeriesClick) {
        this.tvSeriesClick = tvSeriesClick;
    }

    @NonNull
    @Override
    public TvListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_horizontal, parent, false);
        return new TvListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TvListViewHolder holder, int position) {
        holder.bind(tvSeries.get(position));
    }

    @Override
    public int getItemCount() {
        return (tvSeries == null) ? 0 : tvSeries.size();
    }

    class TvListViewHolder extends RecyclerView.ViewHolder {

        private TvSeries tvSeries;

        private ImageView mImagePoster;
        private TextView mTextTitle, mTextOverview, mTextRate;
        private Button mButtonFavourite;

        public TvListViewHolder(View itemView) {
            super(itemView);
            initView();
            addListener();
        }

        private void addListener() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSeriesClick.onTvSeriesClick(tvSeries);
                }
            });
        }

        private void initView() {
            mImagePoster = itemView.findViewById(R.id.image_card_movie);
            mTextTitle = itemView.findViewById(R.id.text_card_name);
            mTextOverview = itemView.findViewById(R.id.text_card_overview);
            mTextRate = itemView.findViewById(R.id.text_card_rate);
            mButtonFavourite = itemView.findViewById(R.id.button_card_favourite);
            mButtonFavourite.setVisibility(View.INVISIBLE);
        }

        public void bind(TvSeries tvSeries) {
            this.tvSeries = tvSeries;
            setupUI();
        }

        private void setupUI() {
            ImageUtils.loadImageFromUrlPicasso(mImagePoster, tvSeries.getPosterPath(), R.drawable.poster_3);
            mTextTitle.setText(tvSeries.getName());
            mTextOverview.setText(tvSeries.getOverview());
            mTextRate.setText(tvSeries.getVoteAverage()+"");
        }
    }

    interface TvSeriesClick{
        void onTvSeriesClick(TvSeries series);
    }
}
