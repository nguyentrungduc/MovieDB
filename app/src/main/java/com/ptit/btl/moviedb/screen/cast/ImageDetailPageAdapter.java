package com.ptit.btl.moviedb.screen.cast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.util.ImageUtils;

import java.util.List;

/**
 * Created by CuongHQ on 5/20/2018.
 */

public class ImageDetailPageAdapter extends PagerAdapter {
    List<String> image;
    Context mContext;

    public ImageDetailPageAdapter(List<String> image, Context mContext) {
        this.image = image;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return image.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image_view, container, false);
        PhotoView img = view.findViewById(R.id.img_detail);
        ImageUtils.loadImageFromUrlPicasso(img, image.get(position), R.drawable.ic_avatar_man);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}