package com.ptit.btl.moviedb.screen.cast;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.util.ImageUtils;

import java.util.List;

/**
 * Created by CuongHQ on 5/19/2018.
 */

public class CastImageAdapter extends RecyclerView.Adapter<CastImageAdapter.CastImageViewHolder> {

    private List<String> images;

    public void setImages(List<String> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CastImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_image, parent, false);
        return new CastImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastImageViewHolder holder, int position) {
        holder.bind(images.get(position));
    }

    @Override
    public int getItemCount() {
        return (images == null) ? 0 : images.size();
    }

    class CastImageViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfile;
        String path;

        CastImageViewHolder(View itemView) {
            super(itemView);
            initView();
        }

        private void initView() {
            ivProfile = itemView.findViewById(R.id.iv_profile);
        }

        void bind(String path) {
            this.path = path;
            setupUI();
        }

        private void setupUI() {
            ImageUtils.loadImageFromUrlPicasso(ivProfile, path, R.drawable.image_trailer);
        }
    }
}
