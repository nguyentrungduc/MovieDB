package com.ptit.btl.moviedb.screen.tv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.tv.TvSeries;
import com.ptit.btl.moviedb.util.ImageUtils;

import java.util.List;

/**
 * Created by CuongHQ on 5/21/2018.
 */

public class TvSeriesAdapter extends RecyclerView.Adapter<TvSeriesAdapter.TvSeriesViewHolder>{

    private List<TvSeries> tvSeries;
    private TvSeriesClick callback;

    public void setTvSeries(List<TvSeries> tvSeries) {
        this.tvSeries = tvSeries;
        notifyDataSetChanged();
    }

    public void setCallback(TvSeriesClick callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public TvSeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_tv_series, parent, false);
        return new TvSeriesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TvSeriesViewHolder holder, int position) {
        holder.bind(tvSeries.get(position));
    }

    @Override
    public int getItemCount() {
        return (tvSeries == null) ? 0 : tvSeries.size();
    }

    class TvSeriesViewHolder extends RecyclerView.ViewHolder {

        TvSeries tvSeries;
        private TextView tvName;
        private ImageView iv;
        private TextView tvRate;

        TvSeriesViewHolder(View itemView) {
            super(itemView);
            initView();
        }

        private void initView() {
            tvName = itemView.findViewById(R.id.tv_name);
            tvRate = itemView.findViewById(R.id.tv_rate);
            iv = itemView.findViewById(R.id.image_card_movie);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onTvSeriesClick(tvSeries);
                }
            });
        }

        void bind(TvSeries tvSeries) {
            this.tvSeries = tvSeries;
            setupUI();
        }

        private void setupUI() {
            tvName.setText(tvSeries.getName());
            tvRate.setText(tvSeries.getVoteAverage() + "");
            ImageUtils.loadImageFromUrlPicasso(iv, tvSeries.getPosterPath(), R.drawable.poster_3);
        }
    }

    interface TvSeriesClick{
        void onTvSeriesClick(TvSeries tvSeries);
    }
}
