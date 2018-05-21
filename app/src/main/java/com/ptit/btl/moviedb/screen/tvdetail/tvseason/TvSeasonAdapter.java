package com.ptit.btl.moviedb.screen.tvdetail.tvseason;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.tv.Season;
import com.ptit.btl.moviedb.util.ImageUtils;

import java.util.List;

/**
 * Created by CuongHQ on 5/22/2018.
 */

public class TvSeasonAdapter extends RecyclerView.Adapter<TvSeasonAdapter.TvSeasonViewHolder>{

    private List<Season> seasons;

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvSeasonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_season, parent, false);
        return new TvSeasonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvSeasonViewHolder holder, int position) {
        holder.bind(seasons.get(position));
    }

    @Override
    public int getItemCount() {
        return (seasons == null) ? 0 : seasons.size();
    }

    class TvSeasonViewHolder extends RecyclerView.ViewHolder {

        Season season;
        TextView tvName;
        TextView tvAirDate;
        ImageView ivPoster;
        TextView tvOverview;

        public TvSeasonViewHolder(View itemView) {
            super(itemView);
            initView();
        }

        private void initView() {
            tvName = itemView.findViewById(R.id.tv_name);
            tvAirDate = itemView.findViewById(R.id.tv_air_date);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvOverview = itemView.findViewById(R.id.tv_overview);
        }

        void bind(Season season) {
            this.season = season;
            setupUI();
        }

        private void setupUI() {
            tvName.setText(season.getName() + " (" + season.getEpisodeCount() + "Eps )");
            tvAirDate.setText((season.getAirDate() == null) ? "" : season.getAirDate());
            tvOverview.setText(season.getOverview());
            ImageUtils.loadImageFromUrlPicasso(ivPoster, season.getPosterPath(), R.drawable.poster_3);
        }
    }
}
