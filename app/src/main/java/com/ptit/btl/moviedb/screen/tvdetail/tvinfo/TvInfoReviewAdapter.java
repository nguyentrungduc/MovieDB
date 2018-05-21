package com.ptit.btl.moviedb.screen.tvdetail.tvinfo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.tv.Review;

import java.util.List;

/**
 * Created by CuongHQ on 5/22/2018.
 */

public class TvInfoReviewAdapter extends RecyclerView.Adapter<TvInfoReviewAdapter.TvInfoReviewViewHolder>{

    private List<Review> reviews;

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvInfoReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new TvInfoReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvInfoReviewViewHolder holder, int position) {
        holder.bind(reviews.get(position));
    }

    @Override
    public int getItemCount() {
        return (reviews == null) ? 0 : reviews.size();
    }

    class TvInfoReviewViewHolder extends RecyclerView.ViewHolder {

        private Review review;
        private TextView tvAuthor;
        private TextView tvContent;

        public TvInfoReviewViewHolder(View itemView) {
            super(itemView);
            initView();
        }

        private void initView() {
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvContent = itemView.findViewById(R.id.tv_content);
        }

        public void bind(Review review) {
            this.review = review;
            setupUI();
        }

        private void setupUI() {
            tvAuthor.setText(review.getAuthor());
            tvContent.setText(review.getContent());
        }
    }
}
